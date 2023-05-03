package com.sachin.categorymicroservice.controller;

import java.util.List;

import com.sachin.categorymicroservice.dto.ProductDto;
import com.sachin.categorymicroservice.entity.ChildCategory;
import com.sachin.categorymicroservice.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sachin.categorymicroservice.entity.SubCategory;
import com.sachin.categorymicroservice.service.SubCategoryService;

@RestController
@RequestMapping("/SubCategories")
@RequiredArgsConstructor
public class SubCategoryController {
	
	private final SubCategoryService subCategoryService;

	
	@PostMapping()
	public ApiResponse<SubCategory> saveSubCategory(@RequestBody SubCategory subCategory) {
		return ApiResponse.<SubCategory>builder()
				.code(201)
				.status(HttpStatus.CREATED)
				.data(subCategoryService.saveSubCategory(subCategory))
				.build();

	}
	@PostMapping("/saveAllSubCategory")
	public ApiResponse<List<SubCategory>> saveAllSubCategory(@RequestBody List<SubCategory> subCategories){
		return ApiResponse.<List<SubCategory>>builder()
				.code(201)
				.status(HttpStatus.CREATED)
				.data(subCategoryService.saveAllSubCategory(subCategories))
				.build();
	}
	@GetMapping()
	public ApiResponse<List<SubCategory>> getAllSubCategory(){
		return ApiResponse.<List<SubCategory>>builder()
				.code(201)
				.status(HttpStatus.CREATED)
				.data(subCategoryService.getAllSubCategory())
				.build();
	}
	@GetMapping("/{id}")
	public ApiResponse<SubCategory> getSubCategoryById(@PathVariable Long id) {
		return ApiResponse.<SubCategory>builder()
				.code(201)
				.status(HttpStatus.CREATED)
				.data(subCategoryService.getSubCategoryById(id))
				.build();
	}

	@GetMapping("/subCategory/{name}")
	public ApiResponse<SubCategory> getSubCategoryByName(@PathVariable String name) {
		return ApiResponse.<SubCategory>builder()
				.code(201)
				.status(HttpStatus.CREATED)
				.data(subCategoryService.getSubCategoryByName(name))
				.build();
	}

	@PutMapping("/{id}")
	public ApiResponse<SubCategory> updateSubCategory(@RequestBody SubCategory subCategory ,@PathVariable("id") Long id) {
		return ApiResponse.<SubCategory>builder()
				.code(201)
				.status(HttpStatus.CREATED)
				.data(subCategoryService.updateSubCategory(subCategory,id))
				.build();
	}

	@DeleteMapping("/{id}")
	public ApiResponse<?> deleteSubCategory(@PathVariable Long id) {
		subCategoryService.deleteSubCategory(id);
		return ApiResponse.builder()
				.code(201)
				.status(HttpStatus.CREATED)
				.build();
	}
	
	@GetMapping("/getAllSubCategoriesFromCategoryId/{id}")
	public ApiResponse<List<SubCategory>> getAllSubCategoriesFromCategoryId(@PathVariable Long id){
		return ApiResponse.<List<SubCategory>>builder()
				.code(201)
				.status(HttpStatus.CREATED)
				.data(subCategoryService.getAllSubCategoriesFromCategoryId(id))
				.build();
	}

	@PostMapping("/{id}")
	public ApiResponse<SubCategory> saveChildCategoryForSubcategory(@RequestBody ChildCategory childCategory, @PathVariable Long id){
		return ApiResponse.<SubCategory>builder()
				.code(201)
				.status(HttpStatus.CREATED)
				.data(subCategoryService.saveChildCategoryForSubcategory(childCategory,id))
				.build();
	}

	@GetMapping("/products/{id}")
	public ApiResponse<List<ProductDto>> getAllProductsFromSubCategoryId(@PathVariable("id") Long id){
		return ApiResponse.<List<ProductDto>>builder()
				.code(201)
				.status(HttpStatus.CREATED)
				.data(subCategoryService.getAllProductsFromSubCategoryId(id))
				.build();
	}
}
