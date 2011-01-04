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
package org.kaleidofoundry.core.lang;

/**
 * This class is used to represent a runtime service exception (coded exception). <br/>
 * It has an unique code parameter, represented precisely type of exception.<br/>
 * RuntimeException is normally not handle by programmer, and have to be propagated through the layers.<br/>
 * 
 * @author Jerome RADUGET
 */
public class CodedRuntimeException extends RuntimeException {

   private static final long serialVersionUID = 4684568762315563117L;

   private final String code;

   /**
    * @param code error code
    */
   public CodedRuntimeException(final String code) {
	super(code);
	this.code = code;
   }

   /**
    * @param code error code
    * @param message error message
    */
   public CodedRuntimeException(final String code, final String message) {
	super(message != null ? message : code);
	this.code = code;
   }

   /**
    * @param code error code
    * @param message error message
    * @param cause
    */
   public CodedRuntimeException(final String code, final String message, final Throwable cause) {
	super(message != null ? message : code, cause);
	this.code = code;
   }

   /**
    * @return Error code of the RuntimeException
    */
   public String getCode() {
	return code;
   }

   /*
    * (non-Javadoc)
    * @see java.lang.Throwable#toString()
    */
   @Override
   public String toString() {
	return super.toString() + " [" + getCode() + "]";
   }

}
