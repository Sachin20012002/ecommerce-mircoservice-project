package com.sachin.categorymicroservice.service;


import com.sachin.categorymicroservice.dto.ProductDto;
import com.sachin.categorymicroservice.entity.ChildCategory;
import com.sachin.categorymicroservice.exception.BadRequestException;
import com.sachin.categorymicroservice.exception.NotFoundException;
import com.sachin.categorymicroservice.repository.ChildCategoryRepository;
import com.sachin.ecommerce_microservice_project.product.ProductServiceGrpc;
import com.sachin.ecommerce_microservice_project.product.ProductServiceRequest;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChildCategoryService {
	

		private final ChildCategoryRepository childCategoryRepo;
		private final GrpcService grpcService;




		
		public ChildCategory saveChildCategory(ChildCategory childCategory) {
			if(childCategoryRepo.existsByName(childCategory.getName())) throw new BadRequestException("Child Category already exists");
			return childCategoryRepo.save(childCategory);
		}

		@Transactional
		public List<ChildCategory> saveAllChildCategory(List<ChildCategory> childCategories){
			return childCategories.stream().map(this::saveChildCategory).collect(Collectors.toList());
		}
		public List<ChildCategory> getAllChildCategory() {
			return childCategoryRepo.findAll();
		}
		
		public ChildCategory getChildCategoryById(Long id) {
			return childCategoryRepo.findById(id).orElseThrow(()-> new NotFoundException("Child Category Id not found"));
		}

		public ChildCategory getChildCategoryByName(String name) {
			return childCategoryRepo.findByName(name).orElseThrow(()-> new NotFoundException("Child Category Id not found"));
		}
		public ChildCategory updateChildCategory(ChildCategory childCategory,Long id) {
			 if(childCategoryRepo.existsById(id)) throw new NotFoundException("Child Category Id not found");
			return childCategoryRepo.save(childCategory);
		}
		public void deleteChildCategory(Long id) {
			if(!childCategoryRepo.existsById(id)) throw new NotFoundException("Child Category Id not found");
			childCategoryRepo.deleteById(id);
		}


	public List<ChildCategory> getAllChildCategoriesFromSubCategoryId(Long id){
			return childCategoryRepo.findAllBySubCategoryId(id);
	}


	public List<ProductDto> getAllProductsByChildCategoryId(Long id){
		return grpcService.getAllProductsByChildCategoryId(id);
	}






}


