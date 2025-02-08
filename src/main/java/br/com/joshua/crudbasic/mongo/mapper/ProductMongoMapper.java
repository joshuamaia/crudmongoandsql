package br.com.joshua.crudbasic.mongo.mapper;

import br.com.joshua.crudbasic.mongo.domain.Product;
import br.com.joshua.crudbasic.mongo.domain.dto.ProductRequest;
import br.com.joshua.crudbasic.mongo.domain.dto.ProductResponse;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMongoMapper {

    ProductResponse toResponse(Product product);

    Product toEntity(ProductRequest productRequest);

    List<ProductResponse> toDTOList(List<Product> products);

    default Page<ProductResponse> toPageResponse(Page<Product> productsPage, Pageable pageable) {
        List<ProductResponse> responseList = toDTOList(productsPage.getContent());
        return new PageImpl<>(responseList, pageable, productsPage.getTotalElements());
    }
}
