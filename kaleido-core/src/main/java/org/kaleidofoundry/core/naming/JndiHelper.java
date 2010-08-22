/*  
 * Copyright 2008-2010 the original author or authors 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kaleidofoundry.core.naming;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import org.kaleidofoundry.core.lang.annotation.NotNull;
import org.kaleidofoundry.core.lang.annotation.Review;
import org.kaleidofoundry.core.lang.annotation.ReviewCategoryEnum;
import org.kaleidofoundry.core.lang.annotation.Reviews;
import org.kaleidofoundry.core.util.StringHelper;

/**
 * Jndi lookup helper <br/>
 * <b>An InitialContext instance is not synchronized against concurrent access by multiple threads</b>
 * 
 * @see Context
 * @see InitialContext
 * @author Jerome RADUGET
 */
public abstract class JndiHelper {

   /**
    * @param context if null, a new local InitialContext will be return, otherwise {@link JndiContext} context
    *           information will be
    *           used to create the InitialContext
    * @return Context build from {@link JndiContext} data information
    * @throws JndiResourceException i18n exception, initial cause will be a {@link NamingException}
    */
   public static Context createInitialContext(final JndiContext<?> context) throws JndiResourceException {
	try {
	   Context initialContext = null;

	   if (context == null) {
		initialContext = new InitialContext();
	   } else {
		final Properties props = new Properties();

		if (!StringHelper.isEmpty(context.getInitialContextFactory())) {
		   props.put(Context.INITIAL_CONTEXT_FACTORY, context.getInitialContextFactory());
		}
		if (!StringHelper.isEmpty(context.getDnsUrl())) {
		   props.put(Context.DNS_URL, context.getDnsUrl());
		}
		if (!StringHelper.isEmpty(context.getProviderUrl())) {
		   props.put(Context.PROVIDER_URL, context.getProviderUrl());
		}
		if (!StringHelper.isEmpty(context.getUrlPkgPrefixes())) {
		   props.put(Context.URL_PKG_PREFIXES, context.getUrlPkgPrefixes());
		}

		initialContext = new InitialContext(props);
	   }

	   return initialContext;
	} catch (final NamingException nae) {
	   throw new JndiResourceException("jndi.error.initialContext.create", nae);
	}
   }

   /**
    * @param initialContext InitialContext � utiliser pour se connecter en Jndi
    * @param jndiName Nom jndi de l'objet distant
    * @param jndiRessourceClass
    * @return instance of the distant resource
    * @param <T>
    * @throws JndiResourceException
    */
   @SuppressWarnings("unchecked")
   @Reviews(reviews = { @Review(comment = "rename lookupRemote ! and replace Context by JndiContext", category = ReviewCategoryEnum.Todo),
	   @Review(comment = "create new method lookup local ?", category = ReviewCategoryEnum.Todo) })
   public static <T> T lookup(final @NotNull Context initialContext, final @NotNull String jndiName, final @NotNull Class<T> jndiRessourceClass)
	   throws JndiResourceException {
	Object obj = null;

	try {
	   obj = initialContext.lookup(jndiName);
	} catch (final NameNotFoundException nnfe) {
	   throw new JndiResourceNotFoundException(jndiName);
	} catch (final NamingException nae) {
	   throw new JndiResourceException("jndi.error.initialContext.lookup", nae, jndiName);
	}

	try {
	   return (T) PortableRemoteObject.narrow(obj, jndiRessourceClass);
	} catch (final ClassCastException cce) {
	   throw new JndiResourceException("jndi.error.initialContext.lookup.classcast", new String[] { jndiName, jndiRessourceClass.getName(),
		   obj.getClass().getName() });
	}
   }

}
