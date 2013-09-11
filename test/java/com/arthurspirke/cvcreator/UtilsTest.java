package com.arthurspirke.cvcreator;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import com.arthurspirke.cvcreator.util.Utils;


public class UtilsTest {

	
	@Test
	public void testGetIntegerValidReturnType(){
		String stringInteger = "245";
		int integer = Utils.getInteger(stringInteger);
		
		assertThat(integer, instanceOf(int.class));
	}
	
	@Test
	public void testGetIntegetValidReturnValue(){
		String stringInteger = "245";
		int integer = Utils.getInteger(stringInteger);
		
		assertThat(245, is(equalTo(integer)));
	}
	
	@Test
	public void testGetIntegerList(){
		List<String> stringIntegerList = new ArrayList<>();
		stringIntegerList.add("352");
		stringIntegerList.add("125");
		stringIntegerList.add("111");
		
		List<Integer> integers = Utils.getListOfIntegers(stringIntegerList);
		
		assertThat(integers.get(0), is(equalTo(352)));
		assertThat(integers.get(1), is(equalTo(125)));
		assertThat(integers.get(2), is(equalTo(111)));
		
	}
	
	@Test
	public void testUUIDValidReturnType(){
		String id = Utils.getUniqueId();
		
		assertThat(id, instanceOf(String.class));
	}
	
	@Test
	public void testUUIDOnUniqueValues(){
        Set<String> ids = new HashSet<>(300);
		
		for(int i = 0; i < 300; i++){
			ids.add(Utils.getUniqueId());
		}
		
		int sizeOfSet = ids.size();
		
		assertThat(300, is(equalTo(sizeOfSet)));
	}
	
	
	@Test
	public void testReplaceDollarSignToSharp(){
		String[] testArray = {"$firstTest", "$secondTest", "$thirdTest"};
		String[] newArray = Utils.replaceDollarSignToSharp(testArray);
		
		assertThat(newArray[0].startsWith("#"), is(equalTo(true)));
		assertThat(newArray[1].startsWith("#"), is(equalTo(true)));
		assertThat(newArray[2].startsWith("#"), is(equalTo(true)));
	}
	
	@Test
	public void testReplaceSharpToDollarSign(){
		String test = "$firstTest";
		String newString = Utils.replaceSharpToDollarSign(test);
		
		assertThat(newString.startsWith("$"), is(equalTo(true)));

	}
	
	
}
