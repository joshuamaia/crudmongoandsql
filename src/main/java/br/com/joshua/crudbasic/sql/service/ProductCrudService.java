package br.com.joshua.crudbasic.sql.service;

import br.com.joshua.crudbasic.sql.domain.dto.ProductRequest;
import br.com.joshua.crudbasic.sql.domain.dto.ProductResponse;

import java.util.List;

public interface ProductCrudService {

    List<ProductResponse> findAll();
    ProductResponse create(ProductRequest product);
    List<ProductResponse> searchByDescription(String keyword);
}
