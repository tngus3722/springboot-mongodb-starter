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
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.function.TupleUtils;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final TestPublisher testPublisher;
    private final ExecutorService customThreadPool;

    @Override
    public CategoryResponse postCategory(CategoryPostRequest categoryPostRequest) {
        if (this.isCategoryExist(categoryPostRequest.getId())) {
            throw new RuntimeException("category Already Exist");
        }

        CategoryDocument categoryDocument = CategoryDocument.builder()
                .id(categoryPostRequest.getId())
                .name(categoryPostRequest.getName())
                .build();
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

    @Override
    public List<CategoryResponse> getCategoriesAsyncTest() {
        long start = System.currentTimeMillis();

        return Mono.zip(getCategoriesReactiveTest(),
                        getCategoriesReactiveTest(),
                        getCategoriesReactiveTest(),
                        getCategoriesReactiveTest(),
                        getCategoriesReactiveTest(),
                        getCategoriesReactiveTest())
                .map(TupleUtils.function(
                        (a, b, c, d, e, f) -> {
                            a.addAll(b);
                            a.addAll(c);
                            a.addAll(d);
                            a.addAll(e);
                            a.addAll(f);
                            return a;
                        }
                ))
                .doOnSuccess(o -> {
                    System.out.println("Duration : " + (System.currentTimeMillis() - start));
                }).block();

    }

    private Mono<List<CategoryResponse>> getCategoriesReactiveTest() {
        return Mono.fromCallable(() -> {
                    try {
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return categoryRepository.findAll().stream()
                            .map(CategoryMapper.INSTANCE::toCategoryResponse)
                            .collect(Collectors.toList());
                }
        ).subscribeOn(Schedulers.fromExecutorService(customThreadPool));
    }


    private CategoryDocument getCategoryDocument(String categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("document not found"));
    }

    private boolean isCategoryExist(String categoryId) {
        return categoryRepository.findById(categoryId).isPresent();
    }
}
