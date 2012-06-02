package br.com.scrum.application.util;

import br.com.scrum.infrastructure.dao.exception.BusinessException;


public final class Validate {
	
   private Validate() { }

   /**
    * Checks that object is not null, throws exception if it is.
    * 
    * @param obj The object to check    * 
    * @throws BusinessException Thrown if obj is null 
    */
   public static void notNull (final Object obj) throws BusinessException {
      if ( obj == null )  throw new BusinessException("The object is null");
   }

   /**
    * Checks that the specified String is not null or empty, 
    * throws exception if it is.
    * 
    * @param string The object to check    * 
    * @throws BusinessException Thrown if obj is null 
    */
   public static void notBlank (final String string) throws BusinessException {
      if ( string == null || string.length() == 0 )  throw new BusinessException("The object is null and empty");     
   }
     
}
