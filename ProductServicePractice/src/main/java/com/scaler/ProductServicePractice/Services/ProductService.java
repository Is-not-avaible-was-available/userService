package com.scaler.ProductServicePractice.Services;

import com.scaler.ProductServicePractice.DTO.GenericProductDTO;
import com.scaler.ProductServicePractice.Exceptions.NotFoundException;

import java.util.List;

public interface ProductService {
    public GenericProductDTO getProductById(Long id) throws NotFoundException;
    public List<GenericProductDTO> getAllProducts();
    public GenericProductDTO createProduct(GenericProductDTO genericProductDTO);
    public GenericProductDTO deleteProductById(Long id);
    public GenericProductDTO updateProductById(Long id, GenericProductDTO genericProductDTO);
}
