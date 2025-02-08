package br.com.joshua.crudbasic.mongo.controller;

import br.com.joshua.crudbasic.mongo.domain.dto.ProductRequest;
import br.com.joshua.crudbasic.mongo.service.ProductCrudService;
import br.com.joshua.crudbasic.mongo.domain.dto.ProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mongo/products")
@AllArgsConstructor
public class ProductMongoCrudController {

    private final ProductCrudService productCrudService;

    @PostMapping("/all")
    public ResponseEntity<Page<ProductResponse>> findAll(@RequestBody ProductRequest product, Pageable pageable) {
        return ResponseEntity.of(Optional.ofNullable(productCrudService.findAll(product, pageable)));
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
