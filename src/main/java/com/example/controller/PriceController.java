// this file is used to handle the frontend request

package com.example.controller;
import com.example.model.FetchedData;
import com.example.repository.PriceRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/prices")
public class PriceController {

    private final PriceRepository repository;

    public PriceController(PriceRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/all")
    public List<FetchedData> getAllPrices() {
        return repository.findAll();
    }
}