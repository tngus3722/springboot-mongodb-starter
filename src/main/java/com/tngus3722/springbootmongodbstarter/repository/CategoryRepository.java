package com.tngus3722.springbootmongodbstarter.repository;

import com.tngus3722.springbootmongodbstarter.document.CategoryDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<CategoryDocument, String> {

}
