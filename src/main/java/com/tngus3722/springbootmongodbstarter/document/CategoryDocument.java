package com.tngus3722.springbootmongodbstarter.document;

import com.tngus3722.springbootmongodbstarter.dto.request.CategoryPutRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Setter
@Getter
@Document(collection = "category")
public class CategoryDocument {

    private String id;
    private String name;

    public void putCategoryDocument(CategoryPutRequest categoryPutRequest) {
        this.name = categoryPutRequest.getName();
    }
}
