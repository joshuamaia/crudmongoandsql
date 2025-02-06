package br.com.joshua.crudbasic.sql.mapper;

import br.com.joshua.crudbasic.sql.domain.Product;
import br.com.joshua.crudbasic.sql.domain.dto.ProductRequest;
import br.com.joshua.crudbasic.sql.domain.dto.ProductResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductSqlMapper {

    ProductResponse toResponse(Product product);

    Product toEntity(ProductRequest productRequest);

    List<ProductResponse> toDTOList(List<Product> products);
}
