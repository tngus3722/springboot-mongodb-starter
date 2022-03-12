package com.tngus3722.springbootmongodbstarter.controller;

import com.tngus3722.springbootmongodbstarter.dto.request.CategoryPostRequest;
import com.tngus3722.springbootmongodbstarter.dto.request.CategoryPutRequest;
import com.tngus3722.springbootmongodbstarter.dto.response.CategoryResponse;
import com.tngus3722.springbootmongodbstarter.service.CategoryService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController()
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("")
    public ResponseEntity<CategoryResponse> postCategory(@RequestBody @Valid CategoryPostRequest categoryPostRequest) {
        return new ResponseEntity<>(categoryService.postCategory(categoryPostRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable String categoryId) {
        return new ResponseEntity<>(categoryService.getCategory(categoryId), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<CategoryResponse>> getCategories() {
        return new ResponseEntity<>(categoryService.getCategories(), HttpStatus.OK);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> putCategory(@PathVariable String categoryId, @RequestBody CategoryPutRequest categoryPutRequest) {
        return new ResponseEntity<>(categoryService.putCategory(categoryId, categoryPutRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
