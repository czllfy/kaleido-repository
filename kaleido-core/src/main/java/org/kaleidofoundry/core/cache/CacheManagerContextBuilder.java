/*
 *  Copyright 2008-2010 the original author or authors.
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
package org.kaleidofoundry.core.cache;

import org.kaleidofoundry.core.cache.CacheConstants.DefaultCacheProviderEnum;
import org.kaleidofoundry.core.config.Configuration;
import org.kaleidofoundry.core.context.AbstractRuntimeContextBuilder;
import org.kaleidofoundry.core.context.RuntimeContext;

/**
 * {@link CacheManager} base {@link RuntimeContext} builder & properties
 * <p>
 * <b>{@link CacheManager} commons context properties</b> : <br/>
 * <table border="1">
 * <tr>
 * <th>Property name</th>
 * <th>Property description</th>
 * </tr>
 * <tr>
 * <td>providerCode</td>
 * <td>cache provider code to use (see {@link DefaultCacheProviderEnum}):
 * <code>ehCache1x|jbossCache3x|infinispan4x|coherence3x|gigaspace7x|local</code></td>
 * </tr>
 * <tr>
 * <td>classloader</td>
 * <td>full class name, which will give the class loader to use</td>
 * </tr>
 * <tr>
 * <td>resourceUri</td>
 * <td>uri of the configuration file to use</td>
 * </tr>
 * <tr>
 * <td>resourceStoreRef</td>
 * <td>name of the resource store context to use, in order to load configuration</td>
 * </tr>
 * </table>
 * </p>
 * <p>
 * 
 * @author Jerome RADUGET
 */
public class CacheManagerContextBuilder extends AbstractRuntimeContextBuilder<CacheManager> {

   /**
    * cache provider code to use (see {@link DefaultCacheProviderEnum}):
    * <code>ehCache1x|jbossCache3x|infinispan4x|coherence3x|gigaspace7x|local</code>
    */
   public static final String ProviderCode = "providerCode";
   /** full class name, which will give the class loader to use */
   public static final String Classloader = "classloader";
   /** uri of the configuration file to use */
   public static final String ResourceUri = "resourceUri";
   /** name of the resource store context to use, in order to load configuration */
   public static final String ResourceStoreRef = "resourceStoreRef";

   /**
    * 
    */
   public CacheManagerContextBuilder() {
	super();
   }

   /**
    * @param pluginInterface
    * @param configurations
    */
   public CacheManagerContextBuilder(final Class<CacheManager> pluginInterface, final Configuration... configurations) {
	super(pluginInterface, configurations);
   }

   /**
    * @param pluginInterface
    */
   public CacheManagerContextBuilder(final Class<CacheManager> pluginInterface) {
	super(pluginInterface);
   }

   /**
    * @param configurations
    */
   public CacheManagerContextBuilder(final Configuration... configurations) {
	super(configurations);
   }

   /**
    * @param name
    * @param pluginInterface
    * @param configurations
    */
   public CacheManagerContextBuilder(final String name, final Class<CacheManager> pluginInterface, final Configuration... configurations) {
	super(name, pluginInterface, configurations);
   }

   /**
    * @param name
    * @param configurations
    */
   public CacheManagerContextBuilder(final String name, final Configuration... configurations) {
	super(name, configurations);
   }

   /**
    * @param name
    * @param prefixProperty
    * @param configurations
    */
   public CacheManagerContextBuilder(final String name, final String prefixProperty, final Configuration... configurations) {
	super(name, prefixProperty, configurations);
   }

   /**
    * @param name
    * @param prefix
    */
   public CacheManagerContextBuilder(final String name, final String prefix) {
	super(name, prefix);
   }

   /**
    * @param name
    */
   public CacheManagerContextBuilder(final String name) {
	super(name);
   }

   /**
    * @param providerCode
    * @return current builder instance
    */
   public CacheManagerContextBuilder withProviderCode(final String providerCode) {
	getContextParameters().put(ProviderCode, providerCode);
	return this;
   }

   /**
    * @param classloader
    * @return current builder instance
    */
   public CacheManagerContextBuilder withClassloader(final String classloader) {
	getContextParameters().put(Classloader, classloader);
	return this;
   }

   /**
    * @param resourceUri
    * @return current builder instance
    */
   public CacheManagerContextBuilder withResourceUri(final String resourceUri) {
	getContextParameters().put(ResourceUri, resourceUri);
	return this;
   }

   /**
    * @param resourceStoreRef
    * @return current builder instance
    */
   public CacheManagerContextBuilder withResourceStoreRef(final String resourceStoreRef) {
	getContextParameters().put(ResourceStoreRef, resourceStoreRef);
	return this;
   }
}
