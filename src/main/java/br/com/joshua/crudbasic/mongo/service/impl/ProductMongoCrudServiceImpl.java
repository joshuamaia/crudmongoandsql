package br.com.joshua.crudbasic.mongo.service.impl;

import br.com.joshua.crudbasic.mongo.domain.Product;
import br.com.joshua.crudbasic.mongo.domain.dto.ProductRequest;
import br.com.joshua.crudbasic.mongo.domain.dto.ProductResponse;
import br.com.joshua.crudbasic.mongo.mapper.ProductMongoMapper;
import br.com.joshua.crudbasic.mongo.repository.ProductMongoRepository;
import br.com.joshua.crudbasic.mongo.repository.spec.ProductCriteria;
import br.com.joshua.crudbasic.mongo.service.ProductCrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductMongoCrudServiceImpl implements ProductCrudService {

    private final ProductMongoRepository productMongoRepository;

    private final MongoTemplate mongoTemplate;

    private final ProductMongoMapper productMapper;

    public ProductMongoCrudServiceImpl(ProductMongoRepository productMongoRepository, MongoTemplate mongoTemplate, ProductMongoMapper productMapper) {
        this.productMongoRepository = productMongoRepository;
        this.mongoTemplate = mongoTemplate;
        this.productMapper = productMapper;
    }

    @Override
    public Page<ProductResponse> findAll(ProductRequest productRequest, Pageable pageable) {
        Query query = ProductCriteria.filterByCriteria(productRequest);

        long total = mongoTemplate.count(query, Product.class);
        query.with(pageable); // Aplica paginação

        List<Product> products = mongoTemplate.find(query, Product.class);

        return productMapper.toPageResponse(new PageImpl<>(products, pageable, total), pageable);
    }

    @Override
    public ProductResponse create(ProductRequest product) {
        return productMapper.toResponse(productMongoRepository.save(productMapper.toEntity(product)));
    }

    @Override
    public List<ProductResponse> searchByDescription(String keyword) {
        return productMapper.toDTOList(productMongoRepository.searchByDescription(keyword));
    }
}
