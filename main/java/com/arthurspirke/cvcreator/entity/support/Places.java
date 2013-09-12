package com.arthurspirke.cvcreator.entity.support;

public class Places{
    protected final int id;
    protected final String ruName;
    protected final String enName;
    
    public Places(int id, String ruName, String enName){
    	this.id = id;
    	this.ruName = ruName;
    	this.enName = enName;
    }
    
	public int getId() {
		return id;
	}
	public String getRuName() {
		return ruName;
	}
	public String getEnName() {
		return enName;
	}
    

}
