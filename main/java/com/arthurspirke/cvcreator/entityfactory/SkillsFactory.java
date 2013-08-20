package com.arthurspirke.cvcreator.entityfactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.arthurspirke.cvcreator.entity.domain.Skills;

import static com.arthurspirke.cvcreator.util.Utils.*;

public class SkillsFactory {
                                                                                                              

	public static List<Skills> getSkillsList(int entityCount, int personId, List<Map<String, String>> skillsList){
        List<Skills> returnList = new ArrayList<>();
        if(entityCount != 0){
            for(Map<String, String> map : skillsList){
            	returnList.add(newSkills(personId, map));
            }
        }
        return returnList;
	}

	public static Skills newSkills(int personId, Map<String, String> d){
		return new Skills(getInteger(d.get("skillsId")), personId, d.get("skillsName"), d.get("skillsValue"), d.get("skillsState"));
	}
}
