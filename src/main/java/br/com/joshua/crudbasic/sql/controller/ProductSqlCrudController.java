package br.com.joshua.crudbasic.sql.controller;

import br.com.joshua.crudbasic.sql.domain.dto.ProductRequest;
import br.com.joshua.crudbasic.sql.domain.dto.ProductResponse;
import br.com.joshua.crudbasic.sql.service.ProductCrudService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sql/products")
@AllArgsConstructor
public class ProductSqlCrudController {

    private final ProductCrudService productCrudService;

    @PostMapping("/all")
    public ResponseEntity<Page<ProductResponse>> findAll(@RequestBody ProductRequest product) {
        Pageable pageable = PageRequest.of(product.getPage(), product.getSize());
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
