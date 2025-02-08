package br.com.joshua.crudbasic.sql.service;

import br.com.joshua.crudbasic.sql.domain.dto.ProductRequest;
import br.com.joshua.crudbasic.sql.domain.dto.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductCrudService {

    Page<ProductResponse> findAll(ProductRequest productRequest, Pageable pageable);
    ProductResponse create(ProductRequest product);
    List<ProductResponse> searchByDescription(String keyword);
}
