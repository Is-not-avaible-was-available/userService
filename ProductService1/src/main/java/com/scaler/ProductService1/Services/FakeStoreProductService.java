package com.scaler.ProductService1.Services;

import com.scaler.ProductService1.ThirdParty.FakeStore.dtos.FakeStoreProductDTO;
import com.scaler.ProductService1.DTOs.GenericProductDTO;
import com.scaler.ProductService1.Exceptions.NotFoundException;
import com.scaler.ProductService1.ThirdParty.FakeStore.FakeStoreProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
@Primary
public class FakeStoreProductService implements ProductService{
    private FakeStoreProductClient fakeStoreProductClient;
    @Autowired
    public FakeStoreProductService(FakeStoreProductClient fakeStoreProductClient){
        this.fakeStoreProductClient = fakeStoreProductClient;
    }

    public GenericProductDTO fakeStoreToGenericProductDTO(FakeStoreProductDTO fakeStoreProductDTO){
        GenericProductDTO genericProductDTO = new GenericProductDTO();
        genericProductDTO.setId(fakeStoreProductDTO.getId());
        genericProductDTO.setTitle(fakeStoreProductDTO.getTitle());
        genericProductDTO.setDescription(fakeStoreProductDTO.getDescription());
        genericProductDTO.setImage(fakeStoreProductDTO.getImage());
        genericProductDTO.setCategory(fakeStoreProductDTO.getCategory());
        genericProductDTO.setPrice(fakeStoreProductDTO.getPrice());
        return genericProductDTO;
    }
    @Override
    public GenericProductDTO updateProductById(Long id, GenericProductDTO newProduct) throws NotFoundException {
        return  fakeStoreToGenericProductDTO(fakeStoreProductClient.updateProductById(id,newProduct));
    }


    @Override
    public List<GenericProductDTO> findAllProducts() throws NotFoundException {
        List<FakeStoreProductDTO> fakeStoreProductDTOS =fakeStoreProductClient.getAllProducts();
        List<GenericProductDTO>genericProductDTOS = new ArrayList<>();
        for(FakeStoreProductDTO fakeStoreProductDTO:fakeStoreProductDTOS){
            genericProductDTOS.add(fakeStoreToGenericProductDTO(fakeStoreProductDTO));
        }
        return genericProductDTOS;
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO genericProductDTO) {
        return fakeStoreToGenericProductDTO(fakeStoreProductClient.createProduct(genericProductDTO));
    }

    @Override
    public GenericProductDTO deleteProductById(long id) throws NotFoundException {
        return fakeStoreToGenericProductDTO(fakeStoreProductClient.deleteProductById(id));
    }

    @Override
    public GenericProductDTO findProductById(Long id) throws NotFoundException {
        return fakeStoreToGenericProductDTO(fakeStoreProductClient.getProductById(id));
    }
}
