package com.sachin.categorymicroservice.controller;

import com.sachin.categorymicroservice.dto.ProductDto;
import com.sachin.categorymicroservice.entity.ChildCategory;
import com.sachin.categorymicroservice.response.ApiResponse;
import com.sachin.categorymicroservice.service.ChildCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ChildCategories")
public class ChildCategoryController {

		private final ChildCategoryService childCategoryService;
		
		@PostMapping()
		public ApiResponse<ChildCategory> saveChildCategory(@RequestBody ChildCategory childCategory) {
			return ApiResponse.<ChildCategory>builder()
					.code(201)
					.status(HttpStatus.CREATED)
					.data(childCategoryService.saveChildCategory(childCategory))
					.build();
		}
		@PostMapping("/saveAllChildCategory")
		public ApiResponse<List<ChildCategory>> saveAllChildCategory(@RequestBody List<ChildCategory> childCategories){
			return ApiResponse.<List<ChildCategory>>builder()
					.code(201)
					.status(HttpStatus.CREATED)
					.data(childCategoryService.saveAllChildCategory(childCategories))
					.build();
		}
		@GetMapping()
		public ApiResponse<List<ChildCategory>> getAllChildCategory(){
			return ApiResponse.<List<ChildCategory>>builder()
					.code(201)
					.status(HttpStatus.CREATED)
					.data(childCategoryService.getAllChildCategory())
					.build();
		}
		@GetMapping("/get/{id}")
		public ApiResponse<ChildCategory> getChildCategoryById(@PathVariable Long id) {
			return ApiResponse.<ChildCategory>builder()
					.code(201)
					.status(HttpStatus.CREATED)
					.data(childCategoryService.getChildCategoryById(id))
					.build();
		}
		@GetMapping("/ChildCategory/{name}")
		public ApiResponse<ChildCategory> getChildCategoryByName(@PathVariable String name) {
			return ApiResponse.<ChildCategory>builder()
					.code(201)
					.status(HttpStatus.CREATED)
					.data(childCategoryService.getChildCategoryByName(name))
					.build();
		}
		@PutMapping("/{id}")
		public ApiResponse<ChildCategory> updateChildCategory(@RequestBody ChildCategory childCategory,@PathVariable Long id) {
			return ApiResponse.<ChildCategory>builder()
					.code(201)
					.status(HttpStatus.CREATED)
					.data(childCategoryService.updateChildCategory(childCategory,id))
					.build();
		}
		@DeleteMapping("/{id}")
		public ApiResponse<?> deleteChildCategory(@PathVariable Long id) {
			childCategoryService.deleteChildCategory(id);
			return ApiResponse.builder()
					.code(201)
					.status(HttpStatus.CREATED)
					.build();
		}

		@GetMapping("/getAllChildCategoriesFromSubCategoryId/{id}")
		public ApiResponse<List<ChildCategory>> getAllChildCategoriesFromSubCategoryId(@PathVariable Long id){
			return ApiResponse.<List<ChildCategory>>builder()
					.code(201)
					.status(HttpStatus.CREATED)
					.data(childCategoryService.getAllChildCategoriesFromSubCategoryId(id))
					.build();
		}


		@GetMapping("/products/{id}")
		public ApiResponse<List<ProductDto>> getAllProductsByChildCategoryId(@PathVariable("id") Long id){
			return ApiResponse.<List<ProductDto>>builder()
					.code(201)
					.status(HttpStatus.CREATED)
					.data(childCategoryService.getAllProductsByChildCategoryId(id))
					.build();
		}
	}
        