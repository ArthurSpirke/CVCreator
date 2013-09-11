package com.arthurspirke.cvcreator.entity.business;

import com.arthurspirke.cvcreator.entity.support.City;
import com.arthurspirke.cvcreator.entity.support.Country;
import com.arthurspirke.cvcreator.entity.support.Places;
import com.arthurspirke.cvcreator.entity.support.Region;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;



public class SupportEntityEqualityTest {
      private static Places city1 = null;
      private static Places city2 = null;
      
      private static Places region1 = null;
      private static Places region2 = null;
      
      private static Places country1 = null;
      private static Places country2 = null;
      
      
      @BeforeClass
      public static void init(){
    	  city1 = new City(35, 11, 105, "Лос Анджелес", "Los Angeles");
    	  city2 = new City(35, 11, 105, "Лос Анджелес", "Los Angeles");
    	  
    	  region1 = new Region(11, 105, "Калифорния", "California");
    	  region2 = new Region(11, 105, "Калифорния", "California");
    	  
    	  country1 = new Country(105, "США", "United States");
    	  country2 = new Country(105, "США", "United States");
      }
      
      
      @Test
      public void testCityEquals(){
    	  assertThat(city1.equals(city2), is(equalTo(true)));
      }
      
      @Test
      public void testCityHashCode(){
    	  assertThat(city1.hashCode() == city2.hashCode(), is(equalTo(true)));
      }
      
      @Test
      public void testRegionEquals(){
    	  assertThat(region1.equals(region2), is(equalTo(true)));
      }
      
      @Test
      public void testRegionHashCode(){
    	  assertThat(region1.hashCode() == region2.hashCode(), is(equalTo(true)));
      }
      
      @Test
      public void testCountryEquals(){
    	  assertThat(country1.equals(country2), is(equalTo(true)));
      }
      
      @Test
      public void testCountryHashCode(){
    	  assertThat(country1.hashCode() == country2.hashCode(), is(equalTo(true)));
      }
	
}
