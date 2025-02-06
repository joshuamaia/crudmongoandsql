package br.com.joshua.crudbasic.mongo.repository;

import br.com.joshua.crudbasic.mongo.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductMongoRepository extends MongoRepository<Product, String> {

    @Query("{ 'description': { $regex: ?0, $options: 'i' } }")
    List<Product> searchByDescription(String keyword);

}
