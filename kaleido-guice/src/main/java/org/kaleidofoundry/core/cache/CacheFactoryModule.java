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
package org.kaleidofoundry.core.cache;

import org.kaleidofoundry.core.cache.annotation.EhCache;
import org.kaleidofoundry.core.cache.annotation.InfinispanCache;
import org.kaleidofoundry.core.cache.annotation.JbossCache;
import org.kaleidofoundry.core.context.AbstractModule;

/**
 * Guice cache factory
 * 
 * @author Jerome RADUGET
 */
public class CacheFactoryModule extends AbstractModule<CacheManager> {

   /*
    * (non-Javadoc)
    * @see org.kaleidofoundry.core.ioc.AbstractModule#getUnnamedImplementation()
    */
   @Override
   public Class<? extends CacheManager> getUnnamedImplementation() {
	return EhCacheManagerImpl.class;
   }

   /*
    * (non-Javadoc)
    * @see org.kaleidofoundry.core.ioc.AbstractModule#configure()
    */
   @Override
   protected void configure() {
	super.configure();

	// default implementation
	bind(CacheManager.class).to(LocalCacheManagerImpl.class);

	// bind custom annotation
	bind(CacheManager.class).annotatedWith(EhCache.class).to(EhCacheManagerImpl.class).in(scope(EhCacheManagerImpl.class));
	bind(CacheManager.class).annotatedWith(JbossCache.class).to(Jboss3xCacheManagerImpl.class).in(scope(Jboss3xCacheManagerImpl.class));
	bind(CacheManager.class).annotatedWith(InfinispanCache.class).to(Infinispan4xCacheManagerImpl.class).in(scope(Infinispan4xCacheManagerImpl.class));
	
   }
}
