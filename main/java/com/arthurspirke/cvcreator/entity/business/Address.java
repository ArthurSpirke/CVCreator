package com.arthurspirke.cvcreator.entity.business;

import com.arthurspirke.cvcreator.entity.enums.Language;
import com.arthurspirke.cvcreator.service.PlacesService;

public class Address extends Component{

	private final String hostId;
	private final int countryId;
	private final int regionId;
	private final int cityId;
	private final int postalCode;
	private final String street;
	private final PlacesService placesService;


	public Address(String id, String hostId, String personId, int countryId, int regionId, int cityId, Language prefLang, String state){
		this(id, hostId, personId, countryId, regionId, cityId, 0, "", prefLang, state);
	}
	
	public Address(String id, String hostId, String personId, int countryId, int regionId, int cityId, int postalCode, String street, Language prefLang, String state){
		super(id, personId, state);
		this.hostId = hostId;
		this.countryId = countryId;
		this.regionId = regionId;
		this.cityId = cityId;
		this.postalCode = postalCode;
		this.street = street;
		this.placesService = new PlacesService(prefLang);
	}

	
    public String getHostId(){
    	return hostId;
    }
	
	public int getCountryId() {
		return countryId;
	}

	public int getRegionId() {
		return regionId;
	}

	public int getCityId() {
		return cityId;
	}
			
	public int getPostalCode() {
		return postalCode;
	}

	public String getStreet() {
		return street;
	}

	public String getStringCountry(){
		return placesService.getCountryNameByPrefLang(countryId);
	}
	
	public String getStringRegion(){
		return placesService.getRegionNameByPrefLang(regionId);
	}
	
	public String getStringCity(){
		return placesService.getCityNameByPrefLang(cityId);
	}
	
	@Override
	public boolean equals(Object o){
		if(o == null || o.getClass() != this.getClass()) return false;
		Address address = (Address) o;
		
		return countryId == address.getCountryId() && regionId == address.getRegionId() && cityId == address.getCityId() && postalCode == address.getPostalCode() && (street == address.street || (street != null && street.equals(address.getStreet())));
	}
	
	@Override
	public int hashCode(){
		int hash = 17;
		hash = hash * 31 + countryId;
		hash = hash * 31 + regionId;
		hash = hash * 31 + cityId;
		hash = hash * 31 + postalCode;
		hash = hash * 31 + (street == null ? 0 : street.hashCode());
		return hash;
	}
}
