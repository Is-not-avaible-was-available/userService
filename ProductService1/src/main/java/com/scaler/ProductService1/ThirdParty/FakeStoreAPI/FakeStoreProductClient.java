package com.scaler.ProductService1.ThirdParty.FakeStoreAPI;

import com.scaler.ProductService1.ThirdParty.FakeStoreAPI.dtos.FakeStoreProductDTO;
import com.scaler.ProductService1.DTOs.GenericProductDTO;
import com.scaler.ProductService1.Exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class FakeStoreProductClient {
    @Value("${fakestore.api.baseurl}")
    private String fakeStoreApiBaseUrl;

    @Value("${fakestore.api.product}")
    private String fakeStoreProductPath;
    private final String productPath = "/products";
    private String productUrl = fakeStoreApiBaseUrl + fakeStoreProductPath ;
    private String singleProductUrl = fakeStoreApiBaseUrl+productPath+"/{id}";
    private RestTemplateBuilder restTemplateBuilder;
    @Autowired
    public FakeStoreProductClient(RestTemplateBuilder restTemplateBuilder,
                                  @Value("${fakestore.api.baseurl}")String fakeStoreApiBaseUrl,
                                  @Value("${fakestore.api.product}")String fakeStoreProductPath){
        this.restTemplateBuilder = restTemplateBuilder;
        this.productUrl = fakeStoreApiBaseUrl+fakeStoreProductPath;
        this.singleProductUrl=fakeStoreApiBaseUrl+productPath+"/{id}";

    }



    public FakeStoreProductDTO getProductById(Long id) throws NotFoundException {
        System.out.println(fakeStoreApiBaseUrl);
        System.out.println(singleProductUrl);
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity =
                restTemplate.getForEntity(singleProductUrl, FakeStoreProductDTO.class, id);
        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductDTOResponseEntity.getBody();
        if(fakeStoreProductDTO == null){
            throw new NotFoundException("Product with id: "+id+" , is not found!");

        }
        return fakeStoreProductDTO;
    }


    public List<FakeStoreProductDTO> getAllProduct() {

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO[]> fakeStoreProductDTOResponseEntity =
                restTemplate.getForEntity(productUrl,FakeStoreProductDTO[].class);
        FakeStoreProductDTO[] fakeStoreProductDTOS = fakeStoreProductDTOResponseEntity.getBody();

        return Arrays.asList(fakeStoreProductDTOS);
    }


    public FakeStoreProductDTO createProduct(GenericProductDTO product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity =
                restTemplate.postForEntity(productUrl, product
                        , FakeStoreProductDTO.class);
        return fakeStoreProductDTOResponseEntity.getBody();
    }


    public FakeStoreProductDTO deleteProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity =
                restTemplate.exchange(singleProductUrl, HttpMethod.DELETE, null, FakeStoreProductDTO.class, id);
        FakeStoreProductDTO fakeStoreProductDTO =  fakeStoreProductDTOResponseEntity.getBody();
        if(fakeStoreProductDTO==null){
            throw new NotFoundException("Id is not found.");
        }

        return fakeStoreProductDTOResponseEntity.getBody();
    }


    public FakeStoreProductDTO updateProductById(Long id, GenericProductDTO genericProductDTO) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity =
                restTemplate.exchange(singleProductUrl, HttpMethod.PUT
                        , new HttpEntity<>(genericProductDTO), FakeStoreProductDTO.class, id);
        return fakeStoreProductDTOResponseEntity.getBody();
    }
}
