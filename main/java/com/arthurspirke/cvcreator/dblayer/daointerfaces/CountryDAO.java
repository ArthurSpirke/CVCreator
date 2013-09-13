package com.arthurspirke.cvcreator.dblayer.daointerfaces;

import java.util.List;

import com.arthurspirke.cvcreator.entity.support.Places;

public interface CountryDAO {

	public Places getById(int id);
	public List<Places> getPlacesList();
}
