package com.arthurspirke.cvcreator.dblayer;

import java.util.List;

import com.arthurspirke.cvcreator.entity.support.Places;

public interface PlacesDAO {

	public Places getById(int id);
	public List<Places> getListByMainPlaceId(int mainPlaceId);
	public List<Places> getPlacesList();
}
