package br.com.joshua.crudbasic.sql.service.impl;


import br.com.joshua.crudbasic.sql.domain.Product;
import br.com.joshua.crudbasic.sql.repository.ProductSqlRepository;
import br.com.joshua.crudbasic.sql.service.ProductCrudService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductSqlCrudServiceImpl implements ProductCrudService {

    private final ProductSqlRepository productSqlRepository;

    @Override
    public List<Product> findAll() {
        return productSqlRepository.findAll();
    }

    @Override
    public Product create(Product product) {
        return productSqlRepository.save(product);
    }
}
