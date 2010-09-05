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

import java.net.URI;

import org.junit.Before;
import org.kaleidofoundry.core.context.RuntimeContext;

/**
 * @author Jerome RADUGET
 */
public class HttpResourceStoreTest extends AbstractResourceStoreTest {

   @Before
   @Override
   public void setup() throws Throwable {
	super.setup();
	// no-proxy settings for this test ;-)
	resourceStore = new HttpResourceStore(new RuntimeContext<ResourceStore>(ResourceStore.class));

	existingResources.put(new URI("http://localhost/kaleidofoundry/it/store/foo.txt"), DEFAULT_RESOURCE_MOCK_TEST);
	nonExistingResources.add(new URI("http://localhost/kaleidofoundry/it/store/foo"));
   }

}
