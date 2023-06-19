package com.sachin.categorymicroservice.controller;

import java.util.List;

import com.sachin.categorymicroservice.dto.ProductDto;
import com.sachin.categorymicroservice.entity.ChildCategory;
import com.sachin.categorymicroservice.response.GenericResponse;
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
@RequestMapping("/sub-categories")
@RequiredArgsConstructor
public class SubCategoryController {
	
	private final SubCategoryService subCategoryService;

	
	@PostMapping()
	public GenericResponse<SubCategory> saveSubCategory(@RequestBody SubCategory subCategory) {
		return GenericResponse.<SubCategory>builder()
				.code(201)
				.status(HttpStatus.CREATED)
				.data(subCategoryService.saveSubCategory(subCategory))
				.build();

	}
	@PostMapping("/saveAllSubCategory")
	public GenericResponse<List<SubCategory>> saveAllSubCategory(@RequestBody List<SubCategory> subCategories){
		return GenericResponse.<List<SubCategory>>builder()
				.code(201)
				.status(HttpStatus.CREATED)
				.data(subCategoryService.saveAllSubCategory(subCategories))
				.build();
	}
	@GetMapping()
	public GenericResponse<List<SubCategory>> getAllSubCategory(){
		return GenericResponse.<List<SubCategory>>builder()
				.code(201)
				.status(HttpStatus.CREATED)
				.data(subCategoryService.getAllSubCategory())
				.build();
	}
	@GetMapping("/{id}")
	public GenericResponse<SubCategory> getSubCategoryById(@PathVariable Long id) {
		return GenericResponse.<SubCategory>builder()
				.code(201)
				.status(HttpStatus.CREATED)
				.data(subCategoryService.getSubCategoryById(id))
				.build();
	}

	@GetMapping("/subCategory/{name}")
	public GenericResponse<SubCategory> getSubCategoryByName(@PathVariable String name) {
		return GenericResponse.<SubCategory>builder()
				.code(201)
				.status(HttpStatus.CREATED)
				.data(subCategoryService.getSubCategoryByName(name))
				.build();
	}

	@PutMapping("/{id}")
	public GenericResponse<SubCategory> updateSubCategory(@RequestBody SubCategory subCategory , @PathVariable("id") Long id) {
		return GenericResponse.<SubCategory>builder()
				.code(201)
				.status(HttpStatus.CREATED)
				.data(subCategoryService.updateSubCategory(subCategory,id))
				.build();
	}

	@DeleteMapping("/{id}")
	public GenericResponse<?> deleteSubCategory(@PathVariable Long id) {
		subCategoryService.deleteSubCategory(id);
		return GenericResponse.builder()
				.code(201)
				.status(HttpStatus.CREATED)
				.build();
	}
	
	@GetMapping("/getAllSubCategoriesFromCategoryId/{id}")
	public GenericResponse<List<SubCategory>> getAllSubCategoriesFromCategoryId(@PathVariable Long id){
		return GenericResponse.<List<SubCategory>>builder()
				.code(201)
				.status(HttpStatus.CREATED)
				.data(subCategoryService.getAllSubCategoriesFromCategoryId(id))
				.build();
	}

	@PostMapping("/{id}")
	public GenericResponse<SubCategory> saveChildCategoryForSubcategory(@RequestBody ChildCategory childCategory, @PathVariable Long id){
		return GenericResponse.<SubCategory>builder()
				.code(201)
				.status(HttpStatus.CREATED)
				.data(subCategoryService.saveChildCategoryForSubcategory(childCategory,id))
				.build();
	}

	@GetMapping("/products/{id}")
	public GenericResponse<List<ProductDto>> getAllProductsFromSubCategoryId(@PathVariable("id") Long id){
		return GenericResponse.<List<ProductDto>>builder()
				.code(201)
				.status(HttpStatus.CREATED)
				.data(subCategoryService.getAllProductsFromSubCategoryId(id))
				.build();
	}
}
