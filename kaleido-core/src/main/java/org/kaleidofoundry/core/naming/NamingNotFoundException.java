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
package org.kaleidofoundry.core.naming;

import org.kaleidofoundry.core.context.RuntimeContext;

/**
 * @author Jerome RADUGET
 */
public class NamingNotFoundException extends NamingServiceException {

   private static final long serialVersionUID = 3986757398779544518L;

   /**
    * @param resourceName jndi resource name
    */
   public <R> NamingNotFoundException(final String resourceName, final RuntimeContext<NamingService<R>> context) {
	super("naming.jndi.error.initialContext.lookup.notfound", resourceName, context.toString("\n"));
   }

}