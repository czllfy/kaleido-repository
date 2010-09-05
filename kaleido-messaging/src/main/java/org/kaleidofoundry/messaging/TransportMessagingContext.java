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
package org.kaleidofoundry.messaging;

import static org.kaleidofoundry.messaging.MessageTypeEnum.Binary;
import static org.kaleidofoundry.messaging.MessageTypeEnum.JavaBean;
import static org.kaleidofoundry.messaging.MessageTypeEnum.Xml;

import org.kaleidofoundry.core.config.Configuration;
import org.kaleidofoundry.core.context.RuntimeContext;

/**
 * Context Connection for TransportMessaging
 * 
 * @author Jerome RADUGET
 */
public class TransportMessagingContext extends RuntimeContext<TransportMessaging> {

   private static final long serialVersionUID = 9167563020291632514L;

   public TransportMessagingContext(final String name, final Configuration... defaults) {
	super(name, TransportMessagingConstants.PREFIX_Transport_Property, defaults);
   }

   public TransportMessagingContext(final String name, final RuntimeContext<?> context) {
	super(name, TransportMessagingConstants.PREFIX_Transport_Property, context);
   }

   public TransportMessagingContext(final String name) {
	super(name, TransportMessagingConstants.PREFIX_Transport_Property);
   }

   /**
    * @return Type de message
    */
   public boolean isXmlMessage() {
	return getProperty(TransportMessagingConstants.SUFFIX_MessageType_Property).equalsIgnoreCase(Xml.getCode());
   }

   /**
    * @return Type de message
    */
   public boolean isBinaryMessage() {
	return getProperty(TransportMessagingConstants.SUFFIX_MessageType_Property).equalsIgnoreCase(Binary.getCode());
   }

   /**
    * @return Type de message
    */
   public boolean isJavaBeanMessage() {
	return getProperty(TransportMessagingConstants.SUFFIX_MessageType_Property).equalsIgnoreCase(JavaBean.getCode());
   }
}