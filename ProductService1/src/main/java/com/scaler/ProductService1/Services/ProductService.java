package com.scaler.ProductService1.Services;

import com.scaler.ProductService1.DTOs.GenericProductDTO;
import com.scaler.ProductService1.Exceptions.NotFoundException;

import java.io.NotActiveException;
import java.util.List;

public interface ProductService {
    public GenericProductDTO getProductById(Long id) throws NotFoundException, NotFoundException;
    public List<GenericProductDTO> getAllProduct();
    public GenericProductDTO createProduct(GenericProductDTO genericProductDTO);
    public GenericProductDTO deleteProductById(Long id) throws NotFoundException;
    public GenericProductDTO updateProductById(Long id, GenericProductDTO genericProductDTO);
}
