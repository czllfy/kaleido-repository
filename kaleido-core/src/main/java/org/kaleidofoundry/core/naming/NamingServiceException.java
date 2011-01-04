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

import java.util.Locale;

import org.kaleidofoundry.core.i18n.I18nRuntimeException;
import org.kaleidofoundry.core.i18n.InternalBundleEnum;

/**
 * @author Jerome RADUGET
 */
public class NamingServiceException extends I18nRuntimeException {

   private static final long serialVersionUID = -7872864470141062829L;

   /**
    * @param code
    */
   public NamingServiceException(final String code) {
	super(code);
   }

   /**
    * @param code
    * @param args
    */
   public NamingServiceException(final String code, final String... args) {
	super(code, args);
   }

   /**
    * @param code
    * @param cause
    */
   public NamingServiceException(final String code, final Throwable cause) {
	super(code, cause);
   }

   /**
    * @param code
    * @param cause
    * @param args
    */
   public NamingServiceException(final String code, final Throwable cause, final String... args) {
	super(code, cause, args);
   }

   /**
    * @param code
    * @param locale
    */
   public NamingServiceException(final String code, final Locale locale) {
	super(code, locale);
   }

   /**
    * @param code
    * @param locale
    * @param args
    */
   public NamingServiceException(final String code, final Locale locale, final String... args) {
	super(code, locale, args);
   }

   /**
    * @param code
    * @param cause
    * @param locale
    */
   public NamingServiceException(final String code, final Throwable cause, final Locale locale) {
	super(code, cause, locale);
   }

   /**
    * @param code
    * @param cause
    * @param locale
    * @param args
    */
   public NamingServiceException(final String code, final Throwable cause, final Locale locale, final String... args) {
	super(code, cause, locale, args);
   }

   /*
    * (non-Javadoc)
    * @see org.kaleidofoundry.core.i18n.I18nRuntimeException#getI18nBundleName()
    */
   @Override
   public String getI18nBundleName() {
	return InternalBundleEnum.NAMING.getResourceName();
   }

}
