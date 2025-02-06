package br.com.joshua.crudbasic.mongo.controller;

import br.com.joshua.crudbasic.mongo.domain.Product;
import br.com.joshua.crudbasic.mongo.service.ProductCrudService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mongo/products")
@AllArgsConstructor
public class ProductMongoCrudController {

    private final ProductCrudService productCrudService;

    @GetMapping("/all")
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.of(Optional.ofNullable(productCrudService.findAll()));
    }

    @PostMapping("/create")
    public ResponseEntity<Product> create(@RequestBody Product product) {
        return ResponseEntity.of(Optional.ofNullable(productCrudService.create(product)));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchByDescription(@RequestParam String keyword) {
        return ResponseEntity.of(Optional.ofNullable(productCrudService.searchByDescription(keyword)));
    }

}
