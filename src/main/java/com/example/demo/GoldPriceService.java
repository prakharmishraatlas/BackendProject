package com.example.demo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoldPriceService {
    private static final String API_URL = "https://goldbroker.com/api/spot-price?metal=XAG&currency=PKR&weight_unit=g";

    public void fetchPrice() {
        // Create a "Browser" (RestTemplate)
        RestTemplate restTemplate = new RestTemplate();

        // make the API call
        try {
            String response = restTemplate.getForObject(API_URL, String.class);
            System.out.println("success");
            System.out.println(response);
        } catch (Exception e) {
            System.out.println("error:" + e.getMessage());
        }
    }
}