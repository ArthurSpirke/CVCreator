package com.arthurspirke.cvcreator.service;

import org.junit.Test;

import com.arthurspirke.cvcreator.entity.enums.Language;
import com.arthurspirke.cvcreator.entity.support.Places;

import org.junit.Before;
import org.junit.Test;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class PlacesServiceTest {

	/*
	 * Places IDs:
	 * 
	 * Country - 372 (United States)
	 * Region - 3248 (California)
	 * City - 35132 (Los Angeles)
	 */
	
	//@Test
	public void testGetPlaceCountry(){
		PlacesService service = new PlacesService(Language.ENGLISH);
		Places place =  service.getPlace(372, "country");
		
		assertThat(place, instanceOf(Places.class));
		assertThat(place.getEnName(), is(equalTo("United States")));
	}
	
	
}
