package com.sachin.categorymicroservice.service;


import com.sachin.categorymicroservice.dto.ProductDto;
import com.sachin.categorymicroservice.entity.ChildCategory;
import com.sachin.categorymicroservice.entity.SubCategory;
import com.sachin.categorymicroservice.exception.BadRequestException;
import com.sachin.categorymicroservice.exception.NotFoundException;
import com.sachin.categorymicroservice.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubCategoryService
{

	private final SubCategoryRepository subCategoryRepo;
	private final ChildCategoryService childCategoryService;

	
	public SubCategory saveSubCategory(SubCategory subCategory) {
		if(subCategoryRepo.existsByName(subCategory.getName())) throw new BadRequestException("subCategory already present");
		return subCategoryRepo.save(subCategory);
	}
	
	public List<SubCategory> saveAllSubCategory(List<SubCategory> subCategories){
		return subCategories.stream().map(this::saveSubCategory).collect(Collectors.toList());
	}
	public List<SubCategory> getAllSubCategory() {
		return subCategoryRepo.findAll();
	}
	public SubCategory getSubCategoryById(Long id) {
		return subCategoryRepo.findById(id).orElseThrow(()-> new NotFoundException("SubCategory Id not Found"));
	}
	public SubCategory getSubCategoryByName(String name) {
		return subCategoryRepo.findByName(name).orElseThrow(()-> new NotFoundException("SubCategory Id not Found"));
	}
	public SubCategory updateSubCategory(SubCategory subCategory,Long id) {
		if(!subCategoryRepo.existsById(id)) throw new NotFoundException("Sub Category not Found");
		return saveSubCategory(subCategory);
	}
	public void deleteSubCategory(Long id) {
		if(!subCategoryRepo.existsById(id)) throw new NotFoundException("Sub Category not Found");
		subCategoryRepo.deleteById(id);
	}
	

	public List<SubCategory> getAllSubCategoriesFromCategoryId(Long id){
		return subCategoryRepo.findAllByCategoryId(id);
	}

	public SubCategory saveChildCategoryForSubcategory(ChildCategory childCategory, Long id) {
		SubCategory subCategory=getSubCategoryById(id);
		subCategory.getChildCategory().add(childCategoryService.saveChildCategory(childCategory));
		return subCategoryRepo.save(subCategory);
	}

	public List<ProductDto> getAllProductsFromSubCategoryId(Long id) {
		return childCategoryService.getAllChildCategoriesFromSubCategoryId(id)
				.stream()
				.flatMap(childCategory -> childCategoryService.getAllProductsByChildCategoryId(childCategory.getId())
						.stream())
				.collect(Collectors.toList());
	}
}
