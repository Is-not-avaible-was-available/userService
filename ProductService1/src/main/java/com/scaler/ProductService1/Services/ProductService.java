package com.scaler.ProductService1.Services;

import com.scaler.ProductService1.DTOs.GenericProductDTO;
import com.scaler.ProductService1.Exceptions.NotFoundException;

import java.util.List;

public interface ProductService {
    public GenericProductDTO findProductById(Long id) throws NotFoundException;
    public List<GenericProductDTO> findAllProducts() throws NotFoundException;
    public GenericProductDTO createProduct(GenericProductDTO genericProductDTO);
    public GenericProductDTO updateProductById(Long id, GenericProductDTO newProduct) throws NotFoundException;
    public GenericProductDTO deleteProductById(long id) throws NotFoundException;
}
