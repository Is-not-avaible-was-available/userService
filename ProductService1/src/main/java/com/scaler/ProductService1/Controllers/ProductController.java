package com.scaler.ProductService1.Controllers;

import com.scaler.ProductService1.DTOs.GenericProductDTO;
import com.scaler.ProductService1.Exceptions.NotFoundException;
import com.scaler.ProductService1.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    @Autowired
    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService){
        this.productService = productService;
    }
    @GetMapping("/{id}")
    public GenericProductDTO findProductById(@PathVariable("id") Long id) throws NotFoundException {
        return productService.findProductById(id);
    }

    @GetMapping
    public List<GenericProductDTO> findAllProducts() throws NotFoundException {
        return productService.findAllProducts();
    }

    @PostMapping
    public GenericProductDTO createNewProduct(@RequestBody GenericProductDTO newProduct){
        return productService.createProduct(newProduct);
    }
    @DeleteMapping("/{id}")
    public GenericProductDTO deleteProductById(@PathVariable("id") Long id) throws NotFoundException {
        return productService.deleteProductById(id);
    }

    @PutMapping("/{id}")
    public GenericProductDTO updateProductById(@PathVariable("id") Long id,
                                               @RequestBody GenericProductDTO updatedProduct) throws NotFoundException {
        return productService.updateProductById(id, updatedProduct);
    }
    //This is specific to controller
//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<ExceptionDTO> handleNotFoundException(NotFoundException notFoundException){
//        return new ResponseEntity<>(new ExceptionDTO(HttpStatus.NOT_FOUND,
//                notFoundException.getMessage()), HttpStatus.NOT_FOUND);
//    }
}
