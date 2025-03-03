package br.com.joshua.crudbasic.sql.service.impl;

import br.com.joshua.crudbasic.sql.domain.Product;
import br.com.joshua.crudbasic.sql.domain.dto.ProductRequest;
import br.com.joshua.crudbasic.sql.domain.dto.ProductResponse;
import br.com.joshua.crudbasic.sql.mapper.ProductSqlMapper;
import br.com.joshua.crudbasic.sql.repository.ProductSqlRepository;
import br.com.joshua.crudbasic.sql.repository.spec.ProductSpecification;
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
import org.springframework.data.jpa.domain.Specification;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductSqlCrudServiceImplTest {

    @Mock
    private ProductSqlRepository productSqlRepository;

    @Mock
    private ProductSqlMapper productMapper;

    @InjectMocks
    private ProductSqlCrudServiceImpl productSqlCrudService;

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
        Page<Product> productPage = new PageImpl<>(Collections.singletonList(product), pageable, 1);
        Specification<Product> specificationMock = mock(Specification.class);

        try (var mockedProductSpecification = Mockito.mockStatic(ProductSpecification.class)) {
            mockedProductSpecification.when(() -> ProductSpecification.filterByCriteria(productRequest))
                    .thenReturn(specificationMock);

            when(productSqlRepository.findAll(eq(specificationMock), eq(pageable))).thenReturn(productPage);
            when(productMapper.toPageResponse(productPage, pageable)).thenReturn(new PageImpl<>(Collections.singletonList(productResponse)));

            Page<ProductResponse> result = productSqlCrudService.findAll(productRequest, pageable);

            assertNotNull(result);
            assertEquals(1, result.getTotalElements());
            verify(productSqlRepository, times(1)).findAll(eq(specificationMock), eq(pageable));
        }
    }

    @Test
    public void testCreate() {
        when(productMapper.toEntity(productRequest)).thenReturn(product);
        when(productSqlRepository.save(product)).thenReturn(product);
        when(productMapper.toResponse(product)).thenReturn(productResponse);

        ProductResponse result = productSqlCrudService.create(productRequest);

        assertNotNull(result);
        verify(productSqlRepository, times(1)).save(product);
        verify(productMapper, times(1)).toResponse(product);
    }

    @Test
    public void testSearchByDescription() {
        String keyword = "test";
        when(productSqlRepository.searchByDescription(keyword)).thenReturn(Collections.singletonList(product));
        when(productMapper.toDTOList(Collections.singletonList(product))).thenReturn(Collections.singletonList(productResponse));

        List<ProductResponse> result = productSqlCrudService.searchByDescription(keyword);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(productSqlRepository, times(1)).searchByDescription(keyword);
        verify(productMapper, times(1)).toDTOList(Collections.singletonList(product));
    }
}