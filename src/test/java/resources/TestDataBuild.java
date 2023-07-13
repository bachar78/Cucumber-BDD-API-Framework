package resources;

import pojo.AddPlace;
import pojo.Location;
import pojo.Types;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {
    public AddPlace addPlacePayload(String name, String language, String address) {
        List<String> myList = new ArrayList<>();
        myList.add("shoe park");
        myList.add("shop");
        Types types = new Types(myList);
        Location location = new Location(-38.383494, 33.427362);
        AddPlace p = new AddPlace(50, name, "(+91) 983 893 3937", address, "https://rahulshettyacademy.com", language, location, types);
        return p;
    }
}
