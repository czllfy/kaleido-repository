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
package org.kaleidofoundry.core.store;

import static org.kaleidofoundry.core.store.ResourceStoreConstants.MemoryStorePluginName;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.kaleidofoundry.core.context.RuntimeContext;
import org.kaleidofoundry.core.plugin.Declare;

/**
 * Memory internal resource store. <b>Avoid using it</b>, or only for small binary data, without multi-threading<br/>
 * Be careful {@link ResourceHandler} is not thread safe. Internally, you can create {@link ResourceHandlerBean} giving an
 * {@link ByteArrayInputStream} in argument to use it. By this way, your binary data will be kept in memory
 * 
 * @author Jerome RADUGET
 */
@Declare(MemoryStorePluginName)
public class MemoryResourceStore extends AbstractResourceStore {

   private final ConcurrentMap<URI, ResourceHandler> memoryResources = new ConcurrentHashMap<URI, ResourceHandler>();

   /**
    * @param context
    */
   public MemoryResourceStore(final RuntimeContext<ResourceStore> context) {
	super(context);
   }

   @Override
   protected ResourceHandler doGet(final URI resourceUri) throws ResourceException {
	ResourceHandler rh = memoryResources.get(resourceUri);
	if (rh == null) {
	   rh = new ResourceHandlerBean(new ByteArrayInputStream(new byte[0]));
	   memoryResources.put(resourceUri, rh);
	}
	return rh;

   }

   @Override
   protected void doRemove(final URI resourceUri) throws ResourceException {
	memoryResources.remove(resourceUri);
   }

   @Override
   protected void doStore(final URI resourceUri, final ResourceHandler resource) throws ResourceException {
	memoryResources.put(resourceUri, resource);

   }

   @Override
   public ResourceStoreType[] getStoreType() {
	return new ResourceStoreType[] { ResourceStoreTypeEnum.memory };
   }

}
