package com.scaler.ProductServicePractice.Services;

import com.scaler.ProductServicePractice.DTO.FakeStoreProductDTO;
import com.scaler.ProductServicePractice.DTO.GenericProductDTO;
import com.scaler.ProductServicePractice.Exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    private RestTemplateBuilder restTemplateBuilder;
    @Autowired
    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }
    private String productUrl = "https://fakestoreapi.com/products";
    private String singleProductUrl = "https://fakestoreapi.com/products/{id}";

    public GenericProductDTO fakeToGenericProductDTO(FakeStoreProductDTO fakeStoreProductDTO){
        GenericProductDTO genericProductDTO = new GenericProductDTO();
        genericProductDTO.setId(fakeStoreProductDTO.getId());
        genericProductDTO.setCategory(fakeStoreProductDTO.getCategory());
        genericProductDTO.setDescription(fakeStoreProductDTO.getDescription());
        genericProductDTO.setImage(fakeStoreProductDTO.getImage());
        genericProductDTO.setTitle(fakeStoreProductDTO.getTitle());
        genericProductDTO.setPrice(fakeStoreProductDTO.getPrice());
        return genericProductDTO;
    }
    @Override
    public GenericProductDTO getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity =
        restTemplate.getForEntity(singleProductUrl, FakeStoreProductDTO.class, id);
        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductDTOResponseEntity.getBody();
        if(fakeStoreProductDTO==null){
            throw new NotFoundException("Product with id: " +id+" , is not found!");
        }
        return fakeToGenericProductDTO(fakeStoreProductDTO);
    }

    @Override
    public List<GenericProductDTO> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO[]> fakeStoreProductResponseEntity =
        restTemplate.getForEntity(productUrl,FakeStoreProductDTO[].class);
        FakeStoreProductDTO[] fakeStoreProductDTOS = fakeStoreProductResponseEntity.getBody();
        List<GenericProductDTO> genericProductDTOS = new ArrayList<>();
        for(FakeStoreProductDTO fakeStoreProductDTO: fakeStoreProductDTOS){
            GenericProductDTO genericProductDTO = fakeToGenericProductDTO(fakeStoreProductDTO);
            genericProductDTOS.add(genericProductDTO);
        }
        return genericProductDTOS;
    }

    @Override
    public GenericProductDTO updateProductById(Long id, GenericProductDTO genericProductDTO) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity =
        restTemplate.exchange(singleProductUrl, HttpMethod.PUT
                , new HttpEntity<>(genericProductDTO), FakeStoreProductDTO.class, id);
        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductDTOResponseEntity.getBody();

        return fakeToGenericProductDTO(fakeStoreProductDTO);
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO genericProductDTO) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity =
        restTemplate.postForEntity(productUrl, genericProductDTO, FakeStoreProductDTO.class);
        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductDTOResponseEntity.getBody();
        return fakeToGenericProductDTO(fakeStoreProductDTO);
    }

    @Override
    public GenericProductDTO deleteProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity=
        restTemplate.exchange(singleProductUrl,HttpMethod.DELETE, null, FakeStoreProductDTO.class, id);
        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductDTOResponseEntity.getBody();
        return fakeToGenericProductDTO(fakeStoreProductDTO);
    }
}
