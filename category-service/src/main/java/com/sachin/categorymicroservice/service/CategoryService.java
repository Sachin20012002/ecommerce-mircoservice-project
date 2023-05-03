package com.sachin.categorymicroservice.service;

import com.sachin.categorymicroservice.dto.ProductDto;
import com.sachin.categorymicroservice.entity.Category;
import com.sachin.categorymicroservice.entity.SubCategory;
import com.sachin.categorymicroservice.exception.BadRequestException;
import com.sachin.categorymicroservice.exception.NotFoundException;
import com.sachin.categorymicroservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

	private final CategoryRepository categoryRepo;
	private final SubCategoryService subCategoryService;

	

	public Category saveCategory(Category category) {
		if(categoryRepo.existsByName(category.getName())) throw new BadRequestException("Category Already Exists");
		return categoryRepo.save(category);
	}

	
	public List<Category> saveAllCategory(List<Category> categories){
		return categories.stream().map(this::saveCategory).collect(Collectors.toList());
	}

	public List<Category> getAllCategories() {
		return categoryRepo.findAll();
	}
	
	public Category getCategoryById(Long id) {
		return categoryRepo.findById(id).orElseThrow(()->new NotFoundException("Category Id not Found"));
	}
	
	public Category getCategoryByName(String name) {
		return categoryRepo.findByName(name).orElseThrow(()->new NotFoundException("Category not Found"));
	}

	public Category UpdateCategory(Category category,Long id) {
		if(!categoryRepo.existsById(id)) throw new NotFoundException("Category Id not found");
		return saveCategory(category);
	}

	public void deleteCategory(Long id) {
		if(!categoryRepo.existsById(id)) throw new NotFoundException("Category Id not found");
		categoryRepo.deleteById(id);
	}


	public Category saveSubCategoryForCategory(SubCategory subCategory, Long id) {
		Category category=getCategoryById(id);
		category.getSubCategory().add(subCategoryService.saveSubCategory(subCategory));
		return category;
	}

    public List<ProductDto> getAllProductsFromCategoryId(Long id) {
		return subCategoryService.getAllSubCategoriesFromCategoryId(id)
				.stream()
				.flatMap(subCategory -> subCategoryService.getAllProductsFromSubCategoryId(subCategory.getId())
						.stream())
				.collect(Collectors.toList());
    }
}
