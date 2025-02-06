package br.com.joshua.crudbasic.mongo.service;

import br.com.joshua.crudbasic.mongo.domain.dto.ProductRequest;
import br.com.joshua.crudbasic.mongo.domain.dto.ProductResponse;

import java.util.List;

public interface ProductCrudService {

    List<ProductResponse> findAll();
    ProductResponse create(ProductRequest product);
    List<ProductResponse> searchByDescription(String keyword);
}
