package com.arthurspirke.cvcreator.dblayer.enums;

import static com.arthurspirke.cvcreator.util.AppProperties.*;

public enum DataStorageType {
   JDBC, EMBEDDED_DB, HIBERNATE;
   
   private static String currentStorageType = "";
   
   static {
	   currentStorageType = getStorageType();
   }
   
   public static DataStorageType getCurrentStorage(){
	   if(currentStorageType.equalsIgnoreCase("jdbc")){
		   return JDBC;
	   } else if(currentStorageType.equalsIgnoreCase("embedded_db")){
		   return EMBEDDED_DB;
	   } else if(currentStorageType.equalsIgnoreCase("hibernate")){
		   return HIBERNATE;
	   } else {
		   throw new IllegalArgumentException();
	   }
   }
}
