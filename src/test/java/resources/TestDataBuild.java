package resources;

import pojo.AddPlace;
import pojo.Location;
import pojo.Types;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {
    public AddPlace addPlacePayload() {
        List<String> myList = new ArrayList<>();
        myList.add("shoe park");
        myList.add("shop");
        Types types = new Types(myList);
        Location location = new Location(-38.383494, 33.427362);
        AddPlace p = new AddPlace(50, "Frontline house", "(+91) 983 893 3937", "29, side layout, cohen 09", "https://rahulshettyacademy.com", "French-IN", location, types);
        return p;
    }
}
