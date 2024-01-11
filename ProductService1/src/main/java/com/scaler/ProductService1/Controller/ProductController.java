package com.scaler.ProductService1.Controller;

import com.scaler.ProductService1.DTOs.GenericProductDTO;
import com.scaler.ProductService1.DTOs.ExceptionDTO;
import com.scaler.ProductService1.Exceptions.NotFoundException;
import com.scaler.ProductService1.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")

public class ProductController {
    private ProductService productService;
    @Autowired
    public ProductController( ProductService productService){
        this.productService = productService;
    }
    @GetMapping("/{id}")
    public GenericProductDTO getProductById(@PathVariable("id") Long id) throws NotFoundException {
        return productService.getProductById(id);
    }
    @GetMapping
    public List<GenericProductDTO> getAllProducts(){

        return productService.getAllProduct();
    }

    @PostMapping
    public GenericProductDTO createProduct(@RequestBody GenericProductDTO genericProductDTO){

        return productService.createProduct(genericProductDTO);
    }
    @PutMapping("/{id}")
    public GenericProductDTO updateProductById(@PathVariable("id") Long id,@RequestBody GenericProductDTO genericProductDTO){

        return productService.updateProductById(id, genericProductDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericProductDTO> deleteProductById(@PathVariable("id") Long id) throws NotFoundException {

        return new ResponseEntity<>( productService.deleteProductById(id), HttpStatus.OK);
    }

 // This is specific to controller
//    @ExceptionHandler(NotActiveException.class)
//    public ResponseEntity<ExceptionDTO> handleNotFoundException(NotActiveException notActiveException){
//        return new ResponseEntity<>(new ExceptionDTO(HttpStatus.NOT_FOUND, notActiveException.getMessage())
//                , HttpStatus.NOT_FOUND);
//    }
}
