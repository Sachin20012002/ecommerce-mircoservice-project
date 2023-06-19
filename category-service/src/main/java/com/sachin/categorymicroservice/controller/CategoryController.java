package com.sachin.categorymicroservice.controller;

import com.sachin.categorymicroservice.dto.ProductDto;
import com.sachin.categorymicroservice.entity.Category;
import com.sachin.categorymicroservice.entity.SubCategory;
import com.sachin.categorymicroservice.response.GenericResponse;
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
	public GenericResponse<Category> saveCategory(@RequestBody Category category) {
		return GenericResponse.<Category>builder()
				.data(categoryService.saveCategory(category))
				.build();
	}

	@PostMapping("/saveAllCategory")
	public GenericResponse<List<Category>> saveAllCategory(@RequestBody List<Category> categories){
		return GenericResponse.<List<Category>>builder()
				.code(201)
				.status(HttpStatus.CREATED)
				.data(categoryService.saveAllCategory(categories))
				.build();
	}

	@GetMapping("")
	public GenericResponse<List<Category>> getAllCategory(){
		return GenericResponse.<List<Category>>builder()
				.code(200)
				.status(HttpStatus.OK)
				.data(categoryService.getAllCategories())
				.build();
	}
	
	@GetMapping("/{id}")
	public GenericResponse<Category> getCategoryById(@PathVariable Long id) {
		return GenericResponse.<Category>builder()
				.code(200)
				.status(HttpStatus.OK)
				.data(categoryService.getCategoryById(id))
				.build();
		}
	
	@GetMapping("/category/{name}")
	public GenericResponse<Category> getCategoryByName(@PathVariable String name) {
		return GenericResponse.<Category>builder()
				.code(200)
				.status(HttpStatus.OK)
				.data(categoryService.getCategoryByName(name))
				.build();
	}
	
	
	@PutMapping("/{id}")
	public GenericResponse<Category> updateCategory(@RequestBody Category category, @PathVariable("id")Long id) {
		return GenericResponse.<Category>builder()
				.code(200)
				.status(HttpStatus.OK)
				.data(categoryService.UpdateCategory(category,id))
				.build();
	}
	@DeleteMapping("/{id}")
	public GenericResponse<?> deleteCategory(@PathVariable Long id)
	{
		categoryService.deleteCategory(id);
		return GenericResponse.builder()
				.code(200)
				.status(HttpStatus.OK)
				.build();
	}

	@PostMapping("/{id}")
	public GenericResponse<Category> saveSubCategoryForCategory(@RequestBody SubCategory subCategory, @PathVariable Long id){
		return GenericResponse.<Category>builder()
				.code(200)
				.status(HttpStatus.OK)
				.data(categoryService.saveSubCategoryForCategory(subCategory,id))
				.build();
	}


	@GetMapping("/products/{id}")
	public GenericResponse<List<ProductDto>> getAllProductsFromCategoryId(@PathVariable("id") Long id){
		return GenericResponse.<List<ProductDto>>builder()
				.code(200)
				.status(HttpStatus.OK)
				.data(categoryService.getAllProductsFromCategoryId(id))
				.build();
	}

}
