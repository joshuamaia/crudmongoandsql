package br.com.joshua.crudbasic.sql.repository;

import br.com.joshua.crudbasic.sql.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSqlRepository extends JpaRepository<Product, Long> {
}
