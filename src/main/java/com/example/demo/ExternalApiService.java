// this file is used to fetch data from external API at regular intervals.
// also stores the data into the DB

package com.example.demo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExternalApiService {
    private static final List<String> METALS = List.of("XAU", "XAG", "XPT", "XPD");
    private static final String CURRENCY = "PKR";

    private final manager repository; // import our manager (ORM)
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();

    // constructor
    public ExternalApiService(manager repository) {
        this.repository = repository;
    }

    @Scheduled(fixedRate = 10000)
    public void fetchAllMetals() {
        for (String metal : METALS) {
            fetchAndSavePrice(metal);
        }
    }

    private void fetchAndSavePrice(String metalSymbol) {
        try {
            String url = String.format("https://goldbroker.com/api/spot-price?metal=%s&currency=%s&weight_unit=g", metalSymbol, CURRENCY);
            String response = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(response);

            if (root.has("value"))
            {
                FetchedData row = new FetchedData();
                row.setMetal(metalSymbol);
                row.setCurrency(CURRENCY);
                row.setPrice(root.path("value").asDouble());
                row.setBid(root.path("bid").asDouble());
                row.setAsk(root.path("ask").asDouble());
                row.setTimestamp(LocalDateTime.now());

                repository.save(row);
                System.out.println("Saved:" + metalSymbol + ": " + row.getPrice());
            }
        } catch (Exception e) {
            System.out.println("Error:" + metalSymbol + " - " + e.getMessage());
        }
    }
}