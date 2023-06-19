package com.codingmart.productmicroservice.service;

import com.codingmart.productmicroservice.entity.*;
import com.codingmart.productmicroservice.enums.Response;
import com.codingmart.productmicroservice.exception.NotFoundException;
import com.codingmart.productmicroservice.repository.*;
import com.codingmart.productmicroservice.service.kafka.KafkaService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final KafkaService kafkaService;
    private final ProductRepository productRepository;
    private final TaxRepository taxRepository;
    private final BrandRepository brandRepository;
    private final TypeRepository typeRepository;
    private final DiscountRepository discountRepository;
    private final ProductDetailRepository productDetailRepository;
    private final ImageService imageService;
    private final SizeService sizeService;
    private final ProductCodeRepository productCodeRepository;
    private final TaxService taxService;
    private final TypeService typeService;
    private final BrandService brandService;
    private final DiscountService discountService;



    @Override
    public Product getProductById(Long id) {
        if(productRepository.findById(id).isEmpty()){
            throw new NotFoundException("Product Id not Found");
        }
        return productRepository.findById(id).get();
    }


    @Override
    @Transactional
    public Product addProduct(Product product) {
        updateAndSaveTransientObjects(product,product);
        product.setCode(generateProductCode(product));
        Product savedProduct=productRepository.save(product);
        System.out.println(savedProduct);
        kafkaService.addProductEvent(savedProduct);
        return savedProduct;
    }




    @Override
    public Response deleteProduct(Long id) {
        if(productRepository.findById(id).isEmpty()){
            throw new NotFoundException("Product Id not Found");
        }
        productRepository.deleteById(id);
        //enum
        return Response.DELETED;
    }


    @Override
    @Transactional
    public Product updateProduct(Long id, Product product) {
        if(productRepository.findById(id).isEmpty()){
            throw new NotFoundException("Product Id not Found");
        }
        Product existingProduct=productRepository.findById(id).get();
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setSupplierId(product.getSupplierId());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setChildCategoryId(product.getChildCategoryId());
        updateAndSaveTransientObjects(product,existingProduct);
        existingProduct.setProductDetails(updatedProductDetails(product.getProductDetails(),existingProduct.getProductDetails()));
        existingProduct.setAvailableSizes(updatedAvailableSizes(product.getAvailableSizes(),existingProduct.getAvailableSizes()));
        existingProduct.setImages(updatedImages(product.getImages(),existingProduct.getImages()));
        return productRepository.save(existingProduct);

    }

    /**
     *
     * @param images - the new set of images provided for update
     * @param existingImages - set of images which were already assigned to the product
     * @return - returns a new set of updated images to assign it to the product after
     *           performing the below operations.
     *           OPERATIONS DONE -> 1) adding new images,
     *                               2) deleting mapped images which is not present,
     *                               3) updating existing images
     */
    private List<Image> updatedImages(List<Image> images, List<Image> existingImages) {

        List<Image> updatedImages=new ArrayList<>();
        HashMap<String,Image> existingImageNames=new HashMap<>();
        Set<String> newImageNames=images.stream().map(Image::getName).collect(Collectors.toSet());
        for(Image image:existingImages){
            if(!newImageNames.contains(image.getName())){
                imageService.deleteImage(image.getId());  // Operation 2
            }
            else{
                existingImageNames.put(image.getName(),image);
            }
        }
        for(Image image:images){
            if(!existingImageNames.containsKey(image.getName())){
                updatedImages.add(imageService.addImage(image));  // Operation 1
            }
            else{    // Operation 3
                updatedImages.add(imageService.updateImage(image,existingImageNames.get(image.getName()).getId()));
            }
        }
        return updatedImages;
    }


    /**
     *
     * @param availableSizes - the new set of sizes provided for update
     * @param existingAvailableSizes - set of sizes which were already assigned to the product
     * @return - returns a new set of updated sizes to assign it to the product after
     *           performing the below operations.
     *           OPERATIONS DONE -> 1) adding new sizes,
     *                               2) deleting mapped sizes which is not present,
     *                               3) updating existing sizes
     */
    private List<Size> updatedAvailableSizes(List<Size> availableSizes, List<Size> existingAvailableSizes) {

        List<Size> updatedAvailableSizes=new ArrayList<>();
        HashMap<String,Size> existingSizesNames=new HashMap<>();
        HashSet<String> newSizesNames=new HashSet<>();
        for(Size size:availableSizes)
            newSizesNames.add(size.getName());
        for(Size size:existingAvailableSizes){
            if(!newSizesNames.contains(size.getName())){
                sizeService.deleteSize(size.getId());  // OPERATION 2
            }
            else{
                existingSizesNames.put(size.getName(),size);
            }
        }
        for(Size size:availableSizes){
            if(!existingSizesNames.containsKey(size.getName())){
                updatedAvailableSizes.add(sizeService.addSize(size)); // OPERATION 1
            }
            else{  // OPERATION 3
                updatedAvailableSizes.add(sizeService.updateSize(size,existingSizesNames.get(size.getName()).getId()));
            }
        }
        return updatedAvailableSizes;
    }

    /**
     *
     * @param newProductDetails - the new set of ProductDetails provided for update
     * @param existingProductDetails - set of ProductDetails which were already assigned to the product
     * @return - returns a new set of updated sizes to assign it to the product after
     *           performing the below operations.
     *           OPERATIONS DONE -> 1) adding new ProductDetail,
     *                               2) deleting mapped ProductDetail which is not present,
     *                               3) updating existing ProductDetail
     */
    private List<ProductDetail> updatedProductDetails(List<ProductDetail> newProductDetails, List<ProductDetail> existingProductDetails) {

        List<ProductDetail> updatedProductDetails=new ArrayList<>();
        HashMap<String,ProductDetail> existingProductDetailNames=new HashMap<>();
        HashMap<String,ProductDetail> newProductDetailNames=new HashMap<>();
        for(ProductDetail productDetail:newProductDetails)
            newProductDetailNames.put(productDetail.getName(),productDetail);
        for(ProductDetail productDetail:existingProductDetails){
            if(!newProductDetailNames.containsKey(productDetail.getName())){
                productDetailRepository.deleteById(productDetail.getId());  // OPERATION 2
            }
            else{
                existingProductDetailNames.put(productDetail.getName(),productDetail);
            }
        }
        for(ProductDetail productDetail:newProductDetails){
            if(!existingProductDetailNames.containsKey(productDetail.getName())){
                updatedProductDetails.add(productDetailRepository.save(productDetail)); // OPERATION 1
            }
            else{
                ProductDetail oldProductDetail=existingProductDetailNames.get(productDetail.getName());
                // OPERATION 3
                if(!oldProductDetail.getValue().equals(productDetail.getValue())){
                    oldProductDetail.setValue(productDetail.getValue());
                }
                updatedProductDetails.add(oldProductDetail);
            }
        }
        return updatedProductDetails;
    }

    @Override
    public List<Product> getAllProductsByChildCategoryId(Long id) {
        return productRepository.findAllByChildCategoryId(id);
    }

    @Override
    public List<Product> getAllProductsByTypeId(Long id) {
        return productRepository.findAllByTypeId(id);
    }

    @Override
    public List<Product> getAllProductsByBrandId(Long id) {
        if(Objects.isNull(productRepository.findAllByBrandId(id)))
            throw new NotFoundException("Products not found");
        return productRepository.findAllByBrandId(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllProductsByColor(String color) {

        return productRepository.findAllByColor(color);
    }

    @Override
    public List<Product> getAllActiveProducts() {
        return productRepository.findAllByActive(true);
    }

    @Override
    public List<Product> getAllActiveProductsByChildCategoryId(Long id) {
        return productRepository.findAllByActiveAndChildCategoryId(true,id);
    }

    @Override
    public List<Product> findByIdIn(List<Long> productIdList) {
        return productRepository.findByIdIn(productIdList);
    }

    /**
     * This method calculates total quantity of the product by the summation
     *          of the quantities of each size.
     * @param availableSizes - details of the sizes assigned to the product
     * @return quantity of the product.
     */
    private Long calculateTotalQuantityFromSizes(List<Size> availableSizes) {
        Long quantity=0L;
        for(Size i:availableSizes){
            if(i.getActive()) quantity+=i.getQuantity();
        }
        return quantity;
    }

    /**
     * This method calculates the MRP of the product by adding the TAX to the product price
     *
     * @param taxes - tax details of the product
     * @param price - price of the product
     * @return MRP of the product
     */
    private Double calculateMaximumRetailPrice(List<Tax> taxes, Double price) {
        Double maximumRetailPrice=price;
        for(Tax i:taxes) {
            if(i.getActive()) maximumRetailPrice += ((i.getPercent() / 100.0) * price);
        }
        return maximumRetailPrice;
    }


    /**
     * This method calculates the final discounted Price of the product by
     *   reducing the discount price in the product price
     *
     * @param discount - discount details of the product
     * @param price - price of the product
     * @return final discounted Price of the product
     */
    private Double calculateFinalDiscountedPrice(Discount discount, Double price) {
        if(Objects.isNull(discount) || !discount.getActive()){
            return price;
        }
        return price-(price*discount.getPercent()/100.0);
    }

    /**
     *  This method saves the new Tax and updates the existing Tax
     * @return List of updated Taxes
     */

    private List<Tax> updateAndSaveTaxes(List<Tax> taxes) {
        List<Tax> productTaxes=new ArrayList<>();
        for(Tax tax:taxes){
            if(Objects.isNull(taxRepository.findByName(tax.getName())))
                taxService.addTax(tax);
            else
                taxService.updateTax(tax,taxRepository.findByName(tax.getName()).getId());
            productTaxes.add(taxRepository.findByName(tax.getName()));
        }
        return productTaxes;
    }

    /**
     *  This method saves the new Type and updates the existing Type
     * @return updated Type
     */
    private Type updateAndSaveType(Type type) {
        if(Objects.isNull(typeRepository.findByName(type.getName()))) {
            typeService.addType(type);
        }
        else {
            typeService.updateType(type, typeRepository.findByName(type.getName()).getId());
        }
        return typeRepository.findByName(type.getName());
    }


    /**
     *  This method saves the new brand and updates the existing brand
     * @return updated Brand
     */
    public Brand updateAndSaveBrand(Brand brand) {
        if (Objects.isNull(brandRepository.findByName(brand.getName()))) {
            brandService.addBrand(brand);
        }
        else{
            brandService.updateBrand(brand,brandRepository.findByName(brand.getName()).getId());
        }
        return brandRepository.findByName(brand.getName());
    }



    /**
     *  This method saves the new Discount and updates the existing Discount
     * @return  updated Discount
     */
    private Discount updateAndSaveDiscount(Discount discount) {
        if (Objects.nonNull(discount)) {
            if (Objects.isNull(discountRepository.findByName(discount.getName())))
                discountService.addDiscount(discount);
            else
                discountService.updateDiscount(discount,discountRepository.findByName(discount.getName()).getId());
            return discountRepository.findByName(discount.getName());
        }
        return null;
    }


    /**
     * This method generates the unique product code
     *  ALGORITHM -> 1) get the brand code
     *               2) get the type code
     *               3) check whether the combination of brand and type already exists
     *               4) if exists increment the value of that combination and
     *                   assign it as Product code
     *               5) else add that combination and initialise a new value
     *                  and assign it as Product code
     *
     * EXAMPLE -> 1) Product1 with brand "AAA" and Type "CCC" = product code ( B50T50-1 )
     *                       where brandCode-50 and TypeCode-50
     *            2) Product2 with same brand and same type = product code ( B50T50-2 )
     *            3) Product3 with same brand and different type = product code ( B50T51-1 )
     *            4) Product4 with different brand and same type = product code ( B51T50-1 )
     *            5) Product5 with different brand and different type = product code ( B51T51-1 )
     * @return - Product code
     */

    private String generateProductCode(Product product) {
        String brandCode= String.valueOf(product.getBrand().getCode().getId());
        String typeCode=String.valueOf(product.getType().getCode().getId());
        String productCode="B"+brandCode+"-T"+typeCode+"-";
        ProductCode existingProductCode=productCodeRepository.findByName(productCode);
        if(Objects.isNull(existingProductCode)){
            productCodeRepository.save(ProductCode.builder().name(productCode).value(1L).build());
            productCode += "1";
        }
        else{
            Long value=existingProductCode.getValue()+1;
            productCode += value;
            existingProductCode.setValue(value);
        }
        return productCode;
    }


    /**
     * This method saves all the transient aggregated objects of the Product
     * @param product - the product which was sent by from the front end
     * @param resultantProduct - the product which will be saved to the DB
     */
    private void updateAndSaveTransientObjects(Product product,Product resultantProduct) {
         /* Regarding builder : did not use it, as we are only setting some attributes which need
                                to be saved before saving the product. If we use builder we have
                                set all the attributes of the product.
        */

        resultantProduct.setTaxes(updateAndSaveTaxes(product.getTaxes()));
        resultantProduct.setType(updateAndSaveType(product.getType()));
        resultantProduct.setBrand(updateAndSaveBrand(product.getBrand()));
        resultantProduct.setDiscount(updateAndSaveDiscount(product.getDiscount()));

        resultantProduct.setQuantity(calculateTotalQuantityFromSizes(product.getAvailableSizes()));
        resultantProduct.setMaximumRetailPrice(calculateMaximumRetailPrice(product.getTaxes(),product.getPrice()));
        resultantProduct.setFinalDiscountedPrice(calculateFinalDiscountedPrice(product.getDiscount(),product.getPrice()));
    }

}
