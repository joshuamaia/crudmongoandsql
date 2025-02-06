package br.com.joshua.crudbasic.sql.service;

import br.com.joshua.crudbasic.sql.domain.Product;

import java.util.List;

public interface ProductCrudService {

    List<Product> findAll();
    Product create(Product product);
    List<Product> searchByDescription(String keyword);
}
