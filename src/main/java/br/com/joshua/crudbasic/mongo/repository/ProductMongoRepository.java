package br.com.joshua.crudbasic.mongo.repository;

import br.com.joshua.crudbasic.mongo.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductMongoRepository extends MongoRepository<Product, String> {
}
