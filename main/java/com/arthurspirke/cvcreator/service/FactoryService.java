package com.arthurspirke.cvcreator.service;

import java.util.List;
import java.util.Map;

public interface FactoryService<T> {
	
    public List<T> getEntitiesList(int entitiesCount, String personId, List<Map<String, String>> list);
    public T getEntity(String personId, Map<String, String> map);
    
}
