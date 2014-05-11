package com.example.listviewsample;

public class Globals {
	private static Globals instance;
	private String LOCATIONS_ARRAY = "locations_array";
	private String PERSONS_ARRAY = "persons_array";
	private String LOCATION_OBJECT = "location_object";
	private String PERSON_OBJECT = "person_object";
	private String IS_LOCATION = "isLocation";
	private String IS_ARRAY = "isArray";
	
	private Globals(){}
	
	public String getLocations_Array_Key() {
		return LOCATIONS_ARRAY;
	}
	
	public String getPersons_Array_Key() {
		return PERSONS_ARRAY;
	}
	
	public String getLocation_Object_Key() {
		return LOCATION_OBJECT;
	}
	
	public String getPerson_Object_Key() {
		return PERSON_OBJECT;
	}
	
	public String getIsLocation() {
		return IS_LOCATION;
	}
	
	public String getIsArray() {
		return IS_ARRAY;
	}
	
	public static synchronized Globals getInstance() {
		if(instance == null) {
			instance = new Globals();
		}
		return instance;
	}
}
