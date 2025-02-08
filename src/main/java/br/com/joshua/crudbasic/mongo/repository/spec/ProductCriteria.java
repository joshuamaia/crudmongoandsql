package br.com.joshua.crudbasic.mongo.repository.spec;

import br.com.joshua.crudbasic.mongo.domain.dto.ProductRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class ProductCriteria {

    public static Query filterByCriteria(ProductRequest productRequest) {
        Query query = new Query();

        if (productRequest.getName() != null && !productRequest.getName().isEmpty()) {
            query.addCriteria(Criteria.where("name").regex(productRequest.getName(), "i"));
        }

        if (productRequest.getDescription() != null && !productRequest.getDescription().isEmpty()) {
            query.addCriteria(Criteria.where("description").regex(productRequest.getDescription(), "i"));
        }

        if (productRequest.getPrice() != null) {
            query.addCriteria(Criteria.where("price").is(productRequest.getPrice()));
        }

        if (productRequest.getStockQuantity() != null) {
            query.addCriteria(Criteria.where("stockQuantity").is(productRequest.getStockQuantity()));
        }

        if (productRequest.getCreatedAt() != null) {
            query.addCriteria(Criteria.where("createdAt").is(productRequest.getCreatedAt()));
        }

        return query;
    }
}
