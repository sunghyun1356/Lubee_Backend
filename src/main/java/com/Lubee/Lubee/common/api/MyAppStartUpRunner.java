package com.Lubee.Lubee.common.api;

import com.Lubee.Lubee.location.LocationApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyAppStartUpRunner implements CommandLineRunner {

    @Autowired
    private LocationApiClient locationApiClient;        // openapi에서 데이터 불러오는 역할

    @Override
    public void run(String... args) throws Exception {
        System.out.println("MyAppStartUpRunner...");
        System.out.println("loadCultureLocation...");
        //locationApiClient.loadCultureLocation();
        //System.out.println("loadRestaurantLocation...");
        //locationApiClient.loadRestaurantLocation();
        System.out.println("MyAppStartUpRunner - FINISHED");
    }

}
