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
package org.kaleidofoundry.core.config;

import static org.kaleidofoundry.core.config.ConfigurationConstants.KeyPropertiesRoot;
import static org.kaleidofoundry.core.config.ConfigurationConstants.KeyRoot;

import java.io.Serializable;
import java.util.Properties;

import org.kaleidofoundry.core.cache.Cache;
import org.kaleidofoundry.core.context.RuntimeContext;
import org.kaleidofoundry.core.lang.annotation.NotNull;
import org.kaleidofoundry.core.plugin.Declare;
import org.kaleidofoundry.core.store.StoreException;
import org.kaleidofoundry.core.store.FileHandler;
import org.kaleidofoundry.core.store.SingleFileStore;
import org.kaleidofoundry.core.util.StringHelper;

/**
 * Java environment variable configuration implementation (read only)<br/>
 * <br/>
 * It will reference all java user variable defined like :
 * 
 * <pre>
 * -Dlog4j.configuration=file:/... -Dapp.config.file=...
 * </pre>
 * 
 * @author Jerome RADUGET
 */
@Declare(ConfigurationConstants.JavaSystemConfigurationPluginName)
public class JavaSystemConfiguration extends AbstractConfiguration implements Configuration {

   /**
    * @param name
    * @param resourceUri
    * @param context
    * @throws StoreException
    */
   public JavaSystemConfiguration(final String name, final String resourceUri, final RuntimeContext<Configuration> context) throws StoreException {
	super(name, "memory:/internal/" + name + ".javasystem", context);
   }

   /**
    * @param name
    * @param context
    * @throws StoreException
    */
   public JavaSystemConfiguration(final String name, final RuntimeContext<Configuration> context) throws StoreException {
	this(name, (String) null, context);
   }

   /*
    * (non-Javadoc)
    * @see org.kaleidofoundry.core.config.AbstractConfiguration#loadProperties(org.kaleidofoundry.core.store.ResourceHandler,
    * org.kaleidofoundry.core.cache.Cache)
    */
   @Override
   protected Cache<String, Serializable> loadProperties(final FileHandler resourceHandler, final Cache<String, Serializable> cacheProperties)
	   throws StoreException, ConfigurationException {

	final Properties javaEnvVariables = System.getProperties();

	for (final String key : javaEnvVariables.stringPropertyNames()) {
	   cacheProperties.put(normalizeKey(key), javaEnvVariables.getProperty(key));
	}

	return cacheProperties;
   }

   /*
    * (non-Javadoc)
    * @see org.kaleidofoundry.core.config.AbstractConfiguration#setProperty(java.lang.String, java.io.Serializable)
    */
   @Override
   public void setProperty(@NotNull final String key, final Serializable value) {
	super.setProperty(key, value);
	System.getProperties().put(StringHelper.replaceAll(key, KeyRoot, KeyPropertiesRoot), value);
   }

   /*
    * (non-Javadoc)
    * @see org.kaleidofoundry.core.config.AbstractConfiguration#storeProperties(org.kaleidofoundry.core.cache.Cache,
    * org.kaleidofoundry.core.store.SingleFileStore)
    */
   @Override
   protected Cache<String, Serializable> storeProperties(final Cache<String, Serializable> properties, final SingleFileStore fileStore)
	   throws StoreException, ConfigurationException {
	return properties; // never called
   }

   /*
    * (non-Javadoc)
    * @see org.kaleidofoundry.core.config.AbstractConfiguration#isStorageAllowed()
    */
   @Override
   public boolean isStorageAllowed() {
	return false;
   }

}