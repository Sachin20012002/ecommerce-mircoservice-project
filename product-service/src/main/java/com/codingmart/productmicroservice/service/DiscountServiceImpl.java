package com.codingmart.productmicroservice.service;

import com.codingmart.productmicroservice.entity.Discount;
import com.codingmart.productmicroservice.enums.Response;
import com.codingmart.productmicroservice.exception.NotFoundException;
import com.codingmart.productmicroservice.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService{

    private final DiscountRepository discountRepository;

    @Autowired
    public DiscountServiceImpl(DiscountRepository discountRepository){
        this.discountRepository=discountRepository;
    }
    @Override
    public Discount addDiscount(Discount discount) {
        return discountRepository.save(discount);
    }

    @Override
    public List<Discount> getAllDiscounts() {
        return discountRepository.findAll();
    }

    @Override
    public Discount getDiscountById(Long id) {
        if(discountRepository.findById(id).isEmpty()){
            throw new NotFoundException("Discount Id not Found");
        }
        return discountRepository.findById(id).get();
    }

    @Override
    public Response deleteDiscount(Long id) {
        if(discountRepository.findById(id).isEmpty()){
            throw new NotFoundException("Discount Id not Found");
        }
        discountRepository.deleteById(id);
        return Response.DELETED;
    }

    @Override
    public Discount updateDiscount(Discount discount,Long id) {
        if(discountRepository.findById(id).isEmpty()){
            throw new NotFoundException("Discount Id not Found");
        }
        Discount existingDiscount=discountRepository.findById(id).get();
        existingDiscount.setName(discount.getName());
        existingDiscount.setActive(discount.getActive());
        existingDiscount.setPercent(discount.getPercent());
        return discountRepository.save(existingDiscount);

    }

}
