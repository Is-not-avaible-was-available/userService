package com.scaler.ProductServicePractice.Controller;

import com.scaler.ProductServicePractice.DTO.ExceptionDto;
import com.scaler.ProductServicePractice.DTO.GenericProductDTO;
import com.scaler.ProductServicePractice.Exceptions.NotFoundException;
import com.scaler.ProductServicePractice.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    private ProductService productService;
    @Autowired
    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService){
        this.productService = productService;
    }
    @GetMapping("{id}")
    public GenericProductDTO getProductById(@PathVariable("id") Long id) throws NotFoundException {
        return productService.getProductById(id);
    }

    @GetMapping
    public List<GenericProductDTO> getAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping
    public GenericProductDTO createProduct(@RequestBody GenericProductDTO genericProductDTO){
        return productService.createProduct(genericProductDTO);
    }

    @DeleteMapping("{id}")
    public GenericProductDTO deleteProductById(@PathVariable("id") Long id){
        return productService.deleteProductById(id);
    }

    @PutMapping("{id}")
    public GenericProductDTO updateProductById(@PathVariable("id") Long id
            ,@RequestBody GenericProductDTO genericProductDTO){
        return productService.updateProductById(id, genericProductDTO);
    }
//Specific to controller
//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<ExceptionDto> handleNotFoundException(NotFoundException notFoundException){
//        return new ResponseEntity<>(new ExceptionDto(HttpStatus.NOT_FOUND
//                , notFoundException.getMessage()), HttpStatus.NOT_FOUND);
//    }
}
