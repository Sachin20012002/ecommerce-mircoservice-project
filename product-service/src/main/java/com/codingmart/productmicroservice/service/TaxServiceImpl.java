package com.codingmart.productmicroservice.service;

import com.codingmart.productmicroservice.entity.Product;
import com.codingmart.productmicroservice.entity.Tax;
import com.codingmart.productmicroservice.enums.Response;
import com.codingmart.productmicroservice.exception.NotFoundException;
import com.codingmart.productmicroservice.repository.ProductRepository;
import com.codingmart.productmicroservice.repository.TaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TaxServiceImpl implements TaxService{

    private final TaxRepository taxRepository;
    private final ProductRepository productRepository;

    @Autowired
    public TaxServiceImpl(TaxRepository taxRepository, @Lazy ProductRepository productRepository){
        this.taxRepository=taxRepository;
        this.productRepository = productRepository;
    }
    @Override
    public Tax addTax(Tax tax) {
        return taxRepository.save(tax);
    }

    @Override
    public Tax getTaxByName(String name) {
        if(Objects.isNull(taxRepository.findByName(name))){
            throw new NotFoundException("Tax Name not Found");
        }
        return taxRepository.findByName(name);
    }

    @Override
    public Tax updateTax(Tax tax,Long id) {
        if(taxRepository.findById(id).isEmpty()){
            throw new NotFoundException("Tax Id not Found");
        }
        Tax existingTax=taxRepository.findById(id).get();
        existingTax.setName(tax.getName());
        existingTax.setPercent(tax.getPercent());
        existingTax.setActive(tax.getActive());
        return taxRepository.save(existingTax);
    }

    @Override
    public Tax getTax(Long id) {
        if(taxRepository.findById(id).isEmpty()){
            throw new NotFoundException("Tax Id not Found");
        }
        return taxRepository.findById(id).get();
    }

    @Override
    public Response deleteTax(Long id) {
        if(taxRepository.findById(id).isEmpty()){
            throw new NotFoundException("Tax Id not Found");
        }

        List<Product> products=productRepository.findAll();
        for(Product p:products){
            List<Tax> taxes=p.getTaxes();
            taxes.removeIf(t -> Objects.equals(t.getId(), id));
            p.setTaxes(taxes);
            productRepository.save(p);
        }
        taxRepository.deleteById(id);
        return Response.DELETED;
    }

    @Override
    public List<Tax> getAllTaxes()
    {
        List<Tax> taxes =taxRepository.findAll();
        if(taxes.isEmpty()){
            throw new NotFoundException("Taxes are not found");
        }
        return taxes;
    }
}
