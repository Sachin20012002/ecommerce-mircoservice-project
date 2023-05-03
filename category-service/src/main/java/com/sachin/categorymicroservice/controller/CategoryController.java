package com.sachin.categorymicroservice.controller;

import com.sachin.categorymicroservice.dto.ProductDto;
import com.sachin.categorymicroservice.entity.Category;
import com.sachin.categorymicroservice.entity.SubCategory;
import com.sachin.categorymicroservice.response.ApiResponse;
import com.sachin.categorymicroservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryService categoryService;
	


	@PostMapping()
	public ApiResponse<Category> saveCategory(@RequestBody Category category) {
		return ApiResponse.<Category>builder()
				.data(categoryService.saveCategory(category))
				.build();
	}

	@PostMapping("/saveAllCategory")
	public ApiResponse<List<Category>> saveAllCategory(@RequestBody List<Category> categories){
		return ApiResponse.<List<Category>>builder()
				.code(201)
				.status(HttpStatus.CREATED)
				.data(categoryService.saveAllCategory(categories))
				.build();
	}

	@GetMapping("")
	public ApiResponse<List<Category>> getAllCategory(){
		return ApiResponse.<List<Category>>builder()
				.code(200)
				.status(HttpStatus.OK)
				.data(categoryService.getAllCategories())
				.build();
	}
	
	@GetMapping("/{id}")
	public ApiResponse<Category> getCategoryById(@PathVariable Long id) {
		return ApiResponse.<Category>builder()
				.code(200)
				.status(HttpStatus.OK)
				.data(categoryService.getCategoryById(id))
				.build();
		}
	
	@GetMapping("/category/{name}")
	public ApiResponse<Category> getCategoryByName(@PathVariable String name) {
		return ApiResponse.<Category>builder()
				.code(200)
				.status(HttpStatus.OK)
				.data(categoryService.getCategoryByName(name))
				.build();
	}
	
	
	@PutMapping("/{id}")
	public ApiResponse<Category> updateCategory(@RequestBody Category category,@PathVariable("id")Long id) {
		return ApiResponse.<Category>builder()
				.code(200)
				.status(HttpStatus.OK)
				.data(categoryService.UpdateCategory(category,id))
				.build();
	}
	@DeleteMapping("/{id}")
	public ApiResponse<?> deleteCategory(@PathVariable Long id)
	{
		categoryService.deleteCategory(id);
		return ApiResponse.builder()
				.code(200)
				.status(HttpStatus.OK)
				.build();
	}

	@PostMapping("/{id}")
	public ApiResponse<Category> saveSubCategoryForCategory(@RequestBody SubCategory subCategory,@PathVariable Long id){
		return ApiResponse.<Category>builder()
				.code(200)
				.status(HttpStatus.OK)
				.data(categoryService.saveSubCategoryForCategory(subCategory,id))
				.build();
	}


	@GetMapping("/products/{id}")
	public ApiResponse<List<ProductDto>> getAllProductsFromCategoryId(@PathVariable("id") Long id){
		return ApiResponse.<List<ProductDto>>builder()
				.code(200)
				.status(HttpStatus.OK)
				.data(categoryService.getAllProductsFromCategoryId(id))
				.build();
	}

}
