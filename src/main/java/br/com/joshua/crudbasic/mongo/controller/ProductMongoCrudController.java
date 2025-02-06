package br.com.joshua.crudbasic.mongo.controller;

import br.com.joshua.crudbasic.mongo.domain.dto.ProductRequest;
import br.com.joshua.crudbasic.mongo.service.ProductCrudService;
import br.com.joshua.crudbasic.mongo.domain.dto.ProductResponse;
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
    public ResponseEntity<List<ProductResponse>> findAll() {
        return ResponseEntity.of(Optional.ofNullable(productCrudService.findAll()));
    }

    @PostMapping("/create")
    public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest product) {
        return ResponseEntity.of(Optional.ofNullable(productCrudService.create(product)));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchByDescription(@RequestParam String keyword) {
        return ResponseEntity.of(Optional.ofNullable(productCrudService.searchByDescription(keyword)));
    }

}
