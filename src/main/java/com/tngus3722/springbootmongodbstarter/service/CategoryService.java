package com.tngus3722.springbootmongodbstarter.service;

import com.tngus3722.springbootmongodbstarter.dto.request.CategoryPostRequest;
import com.tngus3722.springbootmongodbstarter.dto.request.CategoryPutRequest;
import com.tngus3722.springbootmongodbstarter.dto.response.CategoryResponse;
import java.util.List;

public interface CategoryService {

    CategoryResponse postCategory(CategoryPostRequest categoryPostRequest);

    CategoryResponse getCategory(String categoryId);

    List<CategoryResponse> getCategories();

    CategoryResponse putCategory(String categoryId, CategoryPutRequest categoryPutRequest);

    void deleteCategory(String categoryId);

    List<CategoryResponse> getCategoriesAsyncTest();
}
