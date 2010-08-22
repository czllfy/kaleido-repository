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

import java.util.GregorianCalendar;

import org.kaleidofoundry.core.context.InjectContext;

/**
 * <p>
 * <h3>Simple cache manager usage</h3>
 * Inject {@link CacheManager} context and instance using {@link InjectContext} annotation without parameters, but using external configuration
 * </p>
 * <br/>
 * <b>Precondition :</b> The following java env. variable have been set
 * 
 * <pre>
 * -Dkaleido.configurations=classpath:/cache/myConfig.properties
 * </pre>
 * 
 * Resource file : "classpath:/cache/myConfig.properties" contains :
 * 
 * <pre>
 * cacheManager.myCacheManager.providerCode=ehCache1x
 * cacheManager.myCacheManager.resourceUri=classpath:/cache/ehcache.xml
 * # if your cache configuration is accessible from an external resource store like ftp, http... :
 * #cacheManager.myCacheManager.resourceUri=http://yourhost/cache/ehcache.xml
 * #cacheManager.myCacheManager.resourceStoreRef=myHttpCtx
 * # if you need proxy settings, uncomment and configure followings :
 * #resourceStore.myHttp.proxySet=true
 * #resourceStore.myHttp.proxyHost=yourProxyHost
 * #resourceStore.myHttp.proxyUser=yourProxyUser
 * #resourceStore.myHttp.proxyPassword=proxyUserPassword

 * </pre>

 * 
 * @author Jerome RADUGET
 */
public class CacheManagerSample02 {

   @InjectContext("myCacheManagerCtx")
   protected CacheManager myCacheManager;

   protected Cache<String, YourBean> myCache;

   public CacheManagerSample02() {

	myCache = myCacheManager.getCache(YourBean.class);

	// feed cache with somes bean entries
	myCache.put("bean1", new YourBean("name1", GregorianCalendar.getInstance(), true, 2));
	myCache.put("bean2", new YourBean("name2", GregorianCalendar.getInstance(), false, 15));
   }

   /**
    * method example that use the injected cache
    */
   public void echo() {
	System.out.printf("cache name: %s\n", myCache.getName());
	System.out.printf("cache size: %s\n", myCache.size());
	System.out.printf("cache entry[%s]: %s\n", "bean1", myCache.get("bean1").toString());
	System.out.printf("cache entry[%s]: %s\n", "bean2", myCache.get("bean2").toString());
   }
}