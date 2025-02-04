package br.com.joshua.crudbasic.mongo.service;

import br.com.joshua.crudbasic.mongo.domain.Product;

import java.util.List;

public interface ProductCrudService {

    List<Product> findAll();
    Product create(Product product);
}
