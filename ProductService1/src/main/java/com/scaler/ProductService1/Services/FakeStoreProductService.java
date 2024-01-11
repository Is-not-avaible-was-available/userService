package com.scaler.ProductService1.Services;

import com.scaler.ProductService1.ThirdParty.FakeStoreAPI.dtos.FakeStoreProductDTO;
import com.scaler.ProductService1.DTOs.GenericProductDTO;
import com.scaler.ProductService1.Exceptions.NotFoundException;
import com.scaler.ProductService1.ThirdParty.FakeStoreAPI.FakeStoreProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Primary
@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    private FakeStoreProductClient fakeStoreProductClient;
    @Autowired
    public FakeStoreProductService(FakeStoreProductClient fakeStoreProductClient){
        this.fakeStoreProductClient = fakeStoreProductClient;
    }

    public GenericProductDTO fakeToGenericProductDTO(FakeStoreProductDTO fakeStoreProductDTO){
        GenericProductDTO  genericProductDTO = new GenericProductDTO();
        genericProductDTO.setId(fakeStoreProductDTO.getId());
        genericProductDTO.setCategory(fakeStoreProductDTO.getCategory());
        genericProductDTO.setDescription(fakeStoreProductDTO.getDescription());
        genericProductDTO.setImage(fakeStoreProductDTO.getImage());
        genericProductDTO.setPrice(fakeStoreProductDTO.getPrice());
        genericProductDTO.setTitle(fakeStoreProductDTO.getTitle());
        return genericProductDTO;
    }
    @Override
    public GenericProductDTO getProductById(Long id) throws NotFoundException {

        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductClient.getProductById(id);
        if(fakeStoreProductDTO == null){
            throw new NotFoundException("Product with id: "+id+" , is not found!");

        }
        return fakeToGenericProductDTO(fakeStoreProductDTO);
    }

    @Override
    public List<GenericProductDTO> getAllProduct() {

        List<FakeStoreProductDTO> fakeStoreProductDTOS = fakeStoreProductClient.getAllProduct();
        List<GenericProductDTO> genericProductDTOS = new ArrayList<>();
        for(FakeStoreProductDTO fakeStoreProductDTO: fakeStoreProductDTOS){
            GenericProductDTO genericProductDTO = fakeToGenericProductDTO(fakeStoreProductDTO);
            genericProductDTOS.add(genericProductDTO);
        }
        return genericProductDTOS;
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO product) {

        return fakeToGenericProductDTO(fakeStoreProductClient.createProduct(product));
    }

    @Override
    public GenericProductDTO deleteProductById(Long id) throws NotFoundException {

        return fakeToGenericProductDTO(fakeStoreProductClient.deleteProductById(id));
    }

    @Override
    public GenericProductDTO updateProductById(Long id, GenericProductDTO genericProductDTO) {

        return fakeToGenericProductDTO(fakeStoreProductClient.updateProductById(id, genericProductDTO));
    }
}
