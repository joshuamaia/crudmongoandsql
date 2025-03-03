package br.com.joshua.crudbasic.mongo.service;

import br.com.joshua.crudbasic.mongo.domain.Product;
import br.com.joshua.crudbasic.mongo.domain.dto.ProductRequest;
import br.com.joshua.crudbasic.mongo.domain.dto.ProductResponse;
import br.com.joshua.crudbasic.mongo.mapper.ProductMongoMapper;
import br.com.joshua.crudbasic.mongo.repository.ProductMongoRepository;
import br.com.joshua.crudbasic.mongo.repository.spec.ProductCriteria;
import br.com.joshua.crudbasic.mongo.service.impl.ProductMongoCrudServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductMongoCrudServiceImplTest {

    @Mock
    private ProductMongoRepository productMongoRepository;

    @Mock
    private MongoTemplate mongoTemplate;

    @Mock
    private ProductMongoMapper productMapper;

    @InjectMocks
    private ProductMongoCrudServiceImpl productMongoCrudService;

    private ProductRequest productRequest;
    private ProductResponse productResponse;
    private Product product;
    private Pageable pageable;

    @BeforeEach
    public void setUp() {
        productRequest = new ProductRequest();
        productResponse = new ProductResponse();
        product = new Product();
        pageable = PageRequest.of(0, 10);
    }

    @Test
    public void testFindAll() {
        Query query = new Query();
        try (var mockedProductCriteria = Mockito.mockStatic(ProductCriteria.class)) {
            mockedProductCriteria.when(() -> ProductCriteria.filterByCriteria(productRequest))
                    .thenReturn(query);

            when(mongoTemplate.count(query, Product.class)).thenReturn(1L);
            when(mongoTemplate.find(query.with(pageable), Product.class))
                    .thenReturn(Collections.singletonList(product));

            Page<Product> productPage = new PageImpl<>(Collections.singletonList(product), pageable, 1);
            when(productMapper.toPageResponse(productPage, pageable))
                    .thenReturn(new PageImpl<>(Collections.singletonList(productResponse)));

            Page<ProductResponse> result = productMongoCrudService.findAll(productRequest, pageable);

            assertNotNull(result);
            assertEquals(1, result.getTotalElements());
            verify(mongoTemplate, times(1)).count(query, Product.class);
            verify(mongoTemplate, times(1)).find(query.with(pageable), Product.class);
        }

        when(mongoTemplate.count(any(Query.class), eq(Product.class))).thenReturn(1L);
        when(mongoTemplate.find(query.with(pageable), Product.class)).thenReturn(Collections.singletonList(product));
        Page<Product> productPage = new PageImpl<>(Collections.singletonList(product), pageable, 1);
        when(productMapper.toPageResponse(productPage, pageable)).thenReturn(new PageImpl<>(Collections.singletonList(productResponse)));

        Page<ProductResponse> result = productMongoCrudService.findAll(productRequest, pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(mongoTemplate, times(2)).count(query, Product.class);
        verify(mongoTemplate, times(2)).find(query.with(pageable), Product.class);
    }

    @Test
    public void testCreate() {
        when(productMapper.toEntity(productRequest)).thenReturn(product);
        when(productMongoRepository.save(product)).thenReturn(product);
        when(productMapper.toResponse(product)).thenReturn(productResponse);

        ProductResponse result = productMongoCrudService.create(productRequest);

        assertNotNull(result);
        verify(productMongoRepository, times(1)).save(product);
        verify(productMapper, times(1)).toResponse(product);
    }

    @Test
    public void testSearchByDescription() {
        String keyword = "test";
        when(productMongoRepository.searchByDescription(keyword)).thenReturn(Collections.singletonList(product));
        when(productMapper.toDTOList(Collections.singletonList(product))).thenReturn(Collections.singletonList(productResponse));

        List<ProductResponse> result = productMongoCrudService.searchByDescription(keyword);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(productMongoRepository, times(1)).searchByDescription(keyword);
        verify(productMapper, times(1)).toDTOList(Collections.singletonList(product));
    }
}
