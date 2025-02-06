package br.com.joshua.crudbasic.sql.service.impl;


import br.com.joshua.crudbasic.sql.domain.dto.ProductRequest;
import br.com.joshua.crudbasic.sql.domain.dto.ProductResponse;
import br.com.joshua.crudbasic.sql.mapper.ProductSqlMapper;
import br.com.joshua.crudbasic.sql.repository.ProductSqlRepository;
import br.com.joshua.crudbasic.sql.service.ProductCrudService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSqlCrudServiceImpl implements ProductCrudService {

    private final ProductSqlRepository productSqlRepository;
    private final ProductSqlMapper productMapper;

    public ProductSqlCrudServiceImpl(ProductSqlRepository productSqlRepository, ProductSqlMapper productMapper) {
        this.productSqlRepository = productSqlRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductResponse> findAll() {
        return productMapper.toDTOList(productSqlRepository.findAll());
    }

    @Override
    public ProductResponse create(ProductRequest product) {
        return productMapper.toResponse(productSqlRepository.save(productMapper.toEntity(product)));
    }

    @Override
    public List<ProductResponse> searchByDescription(String keyword) {
        return productMapper.toDTOList(productSqlRepository.searchByDescription(keyword));
    }
}
