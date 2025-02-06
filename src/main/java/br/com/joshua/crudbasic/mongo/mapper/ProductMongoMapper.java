package br.com.joshua.crudbasic.mongo.mapper;

import br.com.joshua.crudbasic.mongo.domain.Product;
import br.com.joshua.crudbasic.mongo.domain.dto.ProductRequest;
import br.com.joshua.crudbasic.mongo.domain.dto.ProductResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMongoMapper {

    ProductResponse toResponse(Product product);

    Product toEntity(ProductRequest productRequest);

    List<ProductResponse> toDTOList(List<Product> products);
}
