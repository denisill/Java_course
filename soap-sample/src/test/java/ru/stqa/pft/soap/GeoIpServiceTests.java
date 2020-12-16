package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GeoIpServiceTests {

    @Test
    public void testGeo() {
        String country = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("188.255.76.187");
        Assert.assertTrue(country.contains("<Country>RU</Country>"));
    }

}
