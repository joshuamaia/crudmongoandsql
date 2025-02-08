package br.com.joshua.crudbasic.sql.repository.spec;

import br.com.joshua.crudbasic.sql.domain.dto.ProductRequest;
import org.springframework.data.jpa.domain.Specification;
import br.com.joshua.crudbasic.sql.domain.Product;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {

    public static Specification<Product> filterByCriteria(ProductRequest productRequest) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (productRequest.getName() != null && !productRequest.getName().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + productRequest.getName().toLowerCase() + "%"));
            }

            if (productRequest.getDescription() != null && !productRequest.getDescription().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%" + productRequest.getDescription().toLowerCase() + "%"));
            }

            if (productRequest.getPrice() != null) {
                predicates.add(criteriaBuilder.equal(root.get("price"), productRequest.getPrice()));
            }

            if (productRequest.getStockQuantity() != null) {
                predicates.add(criteriaBuilder.equal(root.get("stockQuantity"), productRequest.getStockQuantity()));
            }

            if (productRequest.getCreatedAt() != null) {
                predicates.add(criteriaBuilder.equal(root.get("createdAt"), productRequest.getCreatedAt()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
