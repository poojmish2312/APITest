package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddApiReq;
import pojo.Location;

public class TestDataBuild {
	public AddApiReq addPlacePayload(String name, String language, String address) {
		AddApiReq ap = new AddApiReq();
		ap.setAccuracy(50);
		ap.setAddress(address);
		ap.setLanguage(language);
		ap.setName(name);
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setWebsites("http://google.com");
		List<String> l = new ArrayList<String>();
		l.add("shoe park");
		l.add("shop");
		ap.setTypes(l);
		Location lo = new Location();
		lo.setLat(-38.383494);
		lo.setLng(33.427362);
		ap.setLocation(lo);
		return ap;
	}
public String deletePlacePayload(String placeId)
{
	return "{\r\n\"place_id\":\""+placeId+"\"\r\n}";
}
}
