package br.com.joshua.crudbasic.mongo.service.impl;

import br.com.joshua.crudbasic.mongo.domain.Product;
import br.com.joshua.crudbasic.mongo.repository.ProductMongoRepository;
import br.com.joshua.crudbasic.mongo.service.ProductCrudService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductMongoCrudServiceImpl implements ProductCrudService {

    private final ProductMongoRepository productMongoRepository;

    @Override
    public List<Product> findAll() {
        return productMongoRepository.findAll();
    }

    @Override
    public Product create(Product product) {
        return productMongoRepository.save(product);
    }

    @Override
    public List<Product> searchByDescription(String keyword) {
        return productMongoRepository.searchByDescription(keyword);
    }
}
