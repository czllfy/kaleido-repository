/*
 *  Copyright 2008-2011 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.kaleidofoundry.core.context.spring;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

import javax.inject.Inject;

import org.kaleidofoundry.core.context.Context;
import org.kaleidofoundry.core.context.Provider;
import org.kaleidofoundry.core.context.ProviderService;
import org.kaleidofoundry.core.context.RuntimeContext;
import org.kaleidofoundry.core.util.ReflectionHelper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

/**
 * BeanPostProcessor used to inject to a spring bean, its class field annotated @{@link Context} <br/>
 * <b>Be careful, if the class field is ever annotated with @{@link Autowired} or @ {@link Inject}, the classic spring bean injection will
 * be
 * used </b><br/>
 * Spring bean, could be injected :
 * <ul>
 * <li>using annotations {@link Autowired}, Spring injection,</li>
 * <li>using annotations {@link Inject}, CDI Injection,</li>
 * <li>using a spring application context, with a bean declared with the spring xml descriptor</li>
 * </ul>
 * 
 * @author Jerome RADUGET
 */
@Component
public class BeanContextPostProcessor implements MergedBeanDefinitionPostProcessor {

   @Override
   public Object postProcessAfterInitialization(final Object beanInstance, final String beanName) throws BeansException {
	return beanInstance;
   }

   @Override
   public Object postProcessBeforeInitialization(final Object beanInstance, final String beanName) throws BeansException {

	// Field[] fields = beanInstance.getClass().getDeclaredFields();
	Set<Field> fields = ReflectionHelper.getAllDeclaredFields(beanInstance.getClass());

	for (Field field : fields) {
	   // @Autowired is injected using spring bean
	   if (!field.isAnnotationPresent(Autowired.class) && !field.isAnnotationPresent(Inject.class)) {
		final Context context = field.getAnnotation(Context.class);
		if (context != null) {
		   // do field is a runtime context class
		   if (field.getType().isAssignableFrom(RuntimeContext.class)) {
			ReflectionUtils.makeAccessible(field);
			ReflectionUtils.setField(field, beanInstance, RuntimeContext.createFrom(context, field.getName(), field.getDeclaringClass()));
		   }
		   // does the plugin interface have a provider specify
		   else if (field.getType().isAnnotationPresent(Provider.class)) {

			try {
			   ReflectionUtils.makeAccessible(field);
			   Object fieldValue = field.get(beanInstance);

			   if (fieldValue == null) {
				// create provider using annotation meta-information
				final Provider provideInfo = field.getType().getAnnotation(Provider.class);
				final Constructor<? extends ProviderService<?>> providerConstructor = provideInfo.value().getConstructor(Class.class);
				final ProviderService<?> fieldProviderInstance = providerConstructor.newInstance(field.getType());

				// invoke provides method with Context annotation meta-informations
				final Method providesMethod = ReflectionUtils.findMethod(provideInfo.value(), ProviderService.PROVIDES_METHOD, Context.class,
					String.class, Class.class);
				// get the provider result
				fieldValue = ReflectionUtils.invokeMethod(providesMethod, fieldProviderInstance, context, field.getName(), field.getType());
				// set the field that was not yet injected
				ReflectionUtils.setField(field, beanInstance, fieldValue);
			   }

			} catch (IllegalArgumentException e) {
			   throw new BeanCreationException("Unexpected error", e);
			} catch (IllegalAccessException e) {
			   throw new BeanCreationException("Unexpected error", e);
			} catch (SecurityException e) {
			   throw new BeanCreationException("Unexpected error", e);
			} catch (NoSuchMethodException e) {
			   throw new BeanCreationException("Unexpected error", e);
			} catch (InstantiationException e) {
			   throw new BeanCreationException("Unexpected error", e);
			} catch (InvocationTargetException ite) {
			   throw new BeanCreationException("", ite.getCause() != null ? ite.getCause() : (ite.getTargetException() != null ? ite.getTargetException()
				   : ite));
			} finally {

			}
		   }

		}
	   }
	}

	return beanInstance;
   }

   @Override
   @SuppressWarnings("rawtypes")
   public void postProcessMergedBeanDefinition(final RootBeanDefinition beanDefinition, final Class beanClass, final String beanName) {
   }

}
