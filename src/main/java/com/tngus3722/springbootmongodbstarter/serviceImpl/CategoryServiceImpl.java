package com.tngus3722.springbootmongodbstarter.serviceImpl;

import com.tngus3722.springbootmongodbstarter.document.CategoryDocument;
import com.tngus3722.springbootmongodbstarter.dto.request.CategoryPostRequest;
import com.tngus3722.springbootmongodbstarter.dto.request.CategoryPutRequest;
import com.tngus3722.springbootmongodbstarter.dto.response.CategoryResponse;
import com.tngus3722.springbootmongodbstarter.event.TestPublisher;
import com.tngus3722.springbootmongodbstarter.mapper.CategoryMapper;
import com.tngus3722.springbootmongodbstarter.repository.CategoryRepository;
import com.tngus3722.springbootmongodbstarter.service.CategoryService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final TestPublisher testPublisher;

    @Override
    public CategoryResponse postCategory(CategoryPostRequest categoryPostRequest) {
        if (this.isCategoryExist(categoryPostRequest.getId())) {
            throw new RuntimeException("category Already Exist");
        }

        CategoryDocument categoryDocument = CategoryDocument.builder()
                .id(categoryPostRequest.getId())
                .name(categoryPostRequest.getName())
                .build();
        System.out.println("postCategory " + Thread.currentThread());
        categoryRepository.save(categoryDocument);
        testPublisher.publishEvent(categoryDocument.getId());
        return this.getCategory(categoryDocument.getId());
    }

    @Override
    public List<CategoryResponse> getCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryMapper.INSTANCE::toCategoryResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponse getCategory(String categoryId) {
        return CategoryMapper.INSTANCE.toCategoryResponse(this.getCategoryDocument(categoryId));
    }

    @Override
    public CategoryResponse putCategory(String categoryId, CategoryPutRequest categoryPutRequest) {
        CategoryDocument categoryDocument = this.getCategoryDocument(categoryId);
        categoryDocument.putCategoryDocument(categoryPutRequest);
        categoryRepository.save(categoryDocument);

        return this.getCategory(categoryId);
    }

    @Override
    public void deleteCategory(String categoryId) {
        categoryRepository.delete(this.getCategoryDocument(categoryId));
    }

    private CategoryDocument getCategoryDocument(String categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("document not found"));
    }

    private boolean isCategoryExist(String categoryId) {
        return categoryRepository.findById(categoryId).isPresent();
    }
}
