package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlace addPlacePayLoad() {
		AddPlace ap = new AddPlace();
		ap.setAccuracy(50);
		ap.setAddress("Asara Colony Akot, India");
		ap.setLanguage("English-EN");
		ap.setName("Automated RESTAPI COurse Rahul");
		ap.setPhone_number("+91-8887744152");
		ap.setWebsite("http://google.com");
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		ap.setLocation(l);
		List<String> item = new ArrayList<String>();
		item.add("AWS");
		item.add("AZUR");
		item.add("GCP");
		ap.setTypes(item);
		return ap;
	}
	
	public AddPlace addPlacePayLoadDynamic(String name, String language, String address) {
		AddPlace ap = new AddPlace();
		ap.setAccuracy(50);
		ap.setAddress(address);
		ap.setLanguage(language);
		ap.setName(name);
		ap.setPhone_number("+91-8887744152");
		ap.setWebsite("http://google.com");
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		ap.setLocation(l);
		List<String> item = new ArrayList<String>();
		item.add("AWS");
		item.add("AZUR");
		item.add("GCP");
		ap.setTypes(item);
		return ap;
	}

	public  String getDeletePayload(String place_ID) {
		return "{\"place_id\":\""+place_ID+"\"}";
	}

}
