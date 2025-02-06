package br.com.joshua.crudbasic.mongo.service.impl;

import br.com.joshua.crudbasic.mongo.domain.dto.ProductRequest;
import br.com.joshua.crudbasic.mongo.domain.dto.ProductResponse;
import br.com.joshua.crudbasic.mongo.mapper.ProductMongoMapper;
import br.com.joshua.crudbasic.mongo.repository.ProductMongoRepository;
import br.com.joshua.crudbasic.mongo.service.ProductCrudService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductMongoCrudServiceImpl implements ProductCrudService {

    private final ProductMongoRepository productMongoRepository;

    private final ProductMongoMapper productMapper;

    public ProductMongoCrudServiceImpl(ProductMongoRepository productMongoRepository, ProductMongoMapper productMapper) {
        this.productMongoRepository = productMongoRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductResponse> findAll() {
        return productMapper.toDTOList(productMongoRepository.findAll());
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
