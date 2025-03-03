package br.com.joshua.crudbasic.mongo.controller;

import br.com.joshua.crudbasic.mongo.domain.dto.ProductRequest;
import br.com.joshua.crudbasic.mongo.domain.dto.ProductResponse;
import br.com.joshua.crudbasic.mongo.service.ProductCrudService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class ProductMongoCrudControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductCrudService productCrudService;

    @InjectMocks
    private ProductMongoCrudController productMongoCrudController;

    private ProductRequest productRequest;
    private ProductResponse productResponse;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productMongoCrudController).build();
        objectMapper = new ObjectMapper();
        productRequest = new ProductRequest();
        productRequest.setPage(0);
        productRequest.setSize(10);
        productResponse = new ProductResponse();
    }

    @Test
    public void testFindAll() throws Exception {
        Page<ProductResponse> productPage = new PageImpl<>(Collections.singletonList(productResponse),
                PageRequest.of(0, 10), 1);
        when(productCrudService.findAll(any(ProductRequest.class), any(Pageable.class))).thenReturn(productPage);

        mockMvc.perform(post("/mongo/products/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }

    @Test
    public void testCreate() throws Exception {
        when(productCrudService.create(any(ProductRequest.class))).thenReturn(productResponse);

        mockMvc.perform(post("/mongo/products/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productRequest)))
                .andExpect(status().isOk());
    }

    @Test
    public void testSearchByDescription() throws Exception {
        List<ProductResponse> responseList = Collections.singletonList(productResponse);
        when(productCrudService.searchByDescription(anyString())).thenReturn(responseList);

        mockMvc.perform(get("/mongo/products/search").param("keyword", "test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}