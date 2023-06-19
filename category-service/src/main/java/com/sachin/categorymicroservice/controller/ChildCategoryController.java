package com.sachin.categorymicroservice.controller;

import com.sachin.categorymicroservice.dto.ProductDto;
import com.sachin.categorymicroservice.entity.ChildCategory;
import com.sachin.categorymicroservice.response.GenericResponse;
import com.sachin.categorymicroservice.service.ChildCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/child-categories")
public class ChildCategoryController {

		private final ChildCategoryService childCategoryService;
		
		@PostMapping()
		public GenericResponse<ChildCategory> saveChildCategory(@RequestBody ChildCategory childCategory) {
			return GenericResponse.<ChildCategory>builder()
					.code(201)
					.status(HttpStatus.CREATED)
					.data(childCategoryService.saveChildCategory(childCategory))
					.build();
		}
		@PostMapping("/saveAllChildCategory")
		public GenericResponse<List<ChildCategory>> saveAllChildCategory(@RequestBody List<ChildCategory> childCategories){
			return GenericResponse.<List<ChildCategory>>builder()
					.code(201)
					.status(HttpStatus.CREATED)
					.data(childCategoryService.saveAllChildCategory(childCategories))
					.build();
		}
		@GetMapping()
		public GenericResponse<List<ChildCategory>> getAllChildCategory(){
			return GenericResponse.<List<ChildCategory>>builder()
					.code(201)
					.status(HttpStatus.CREATED)
					.data(childCategoryService.getAllChildCategory())
					.build();
		}
		@GetMapping("/get/{id}")
		public GenericResponse<ChildCategory> getChildCategoryById(@PathVariable Long id) {
			return GenericResponse.<ChildCategory>builder()
					.code(201)
					.status(HttpStatus.CREATED)
					.data(childCategoryService.getChildCategoryById(id))
					.build();
		}
		@GetMapping("/ChildCategory/{name}")
		public GenericResponse<ChildCategory> getChildCategoryByName(@PathVariable String name) {
			return GenericResponse.<ChildCategory>builder()
					.code(201)
					.status(HttpStatus.CREATED)
					.data(childCategoryService.getChildCategoryByName(name))
					.build();
		}
		@PutMapping("/{id}")
		public GenericResponse<ChildCategory> updateChildCategory(@RequestBody ChildCategory childCategory, @PathVariable Long id) {
			return GenericResponse.<ChildCategory>builder()
					.code(201)
					.status(HttpStatus.CREATED)
					.data(childCategoryService.updateChildCategory(childCategory,id))
					.build();
		}
		@DeleteMapping("/{id}")
		public GenericResponse<?> deleteChildCategory(@PathVariable Long id) {
			childCategoryService.deleteChildCategory(id);
			return GenericResponse.builder()
					.code(201)
					.status(HttpStatus.CREATED)
					.build();
		}

		@GetMapping("/getAllChildCategoriesFromSubCategoryId/{id}")
		public GenericResponse<List<ChildCategory>> getAllChildCategoriesFromSubCategoryId(@PathVariable Long id){
			return GenericResponse.<List<ChildCategory>>builder()
					.code(201)
					.status(HttpStatus.CREATED)
					.data(childCategoryService.getAllChildCategoriesFromSubCategoryId(id))
					.build();
		}


		@GetMapping("/products/{id}")
		public GenericResponse<List<ProductDto>> getAllProductsByChildCategoryId(@PathVariable("id") Long id){
			return GenericResponse.<List<ProductDto>>builder()
					.code(201)
					.status(HttpStatus.CREATED)
					.data(childCategoryService.getAllProductsByChildCategoryId(id))
					.build();
		}
	}
        