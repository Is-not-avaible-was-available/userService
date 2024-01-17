package com.scaler.ProductService1.ThirdParty.FakeStore;

import com.scaler.ProductService1.ThirdParty.FakeStore.dtos.FakeStoreProductDTO;
import com.scaler.ProductService1.DTOs.GenericProductDTO;
import com.scaler.ProductService1.Exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class FakeStoreProductClient {
    @Value("${fakestore.api.baseurl}")
    private String fakeStoreBaseUrl;

    @Value("${fakestore.api.product}")
    private String fakeStoreProductPath;
    private String allProductsUrl;
    private final String product = "/products";
    private String singleProductUrl;
    private RestTemplateBuilder restTemplateBuilder;
    @Autowired
    public FakeStoreProductClient(RestTemplateBuilder restTemplateBuilder,
                                  @Value("${fakestore.api.baseurl}")String fakeStoreBaseUrl,
                                  @Value("${fakestore.api.product}")String fakeStoreProductPath){
        this.restTemplateBuilder = restTemplateBuilder;
        this.singleProductUrl = fakeStoreBaseUrl+product+"/{id}";
        this.allProductsUrl = fakeStoreBaseUrl+fakeStoreProductPath;
    }

    public FakeStoreProductDTO getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity =
        restTemplate.getForEntity(singleProductUrl, FakeStoreProductDTO.class, id);

        FakeStoreProductDTO fakeStoreProductDTO= fakeStoreProductDTOResponseEntity.getBody();
        if(fakeStoreProductDTO==null){
            throw  new NotFoundException("product with id:"+id+", is not found!");
        }
        return fakeStoreProductDTO;
    }


    public List<FakeStoreProductDTO> getAllProducts() throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO[]>fakeStoreResponseEntity =
        restTemplate.getForEntity(allProductsUrl, FakeStoreProductDTO[].class);
        FakeStoreProductDTO[] fakeStoreProductDTOS = fakeStoreResponseEntity.getBody();
        List<FakeStoreProductDTO> fakeStoreProductDTOList = new ArrayList<>();
        if(fakeStoreProductDTOS==null){
            throw new NotFoundException("products not found!");
        }
        return Arrays.asList(fakeStoreProductDTOS);
    }

    public FakeStoreProductDTO createProduct(GenericProductDTO genericProductDTO) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity =
                restTemplate.postForEntity(allProductsUrl, genericProductDTO, FakeStoreProductDTO.class);
        return fakeStoreProductDTOResponseEntity.getBody();
    }

    public FakeStoreProductDTO deleteProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity=
        restTemplate.exchange(singleProductUrl, HttpMethod.DELETE, null, FakeStoreProductDTO.class, id);
        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductDTOResponseEntity.getBody();
        if(fakeStoreProductDTO==null){
            throw new NotFoundException("product with id:"+id+", is not found!");
        }
        return fakeStoreProductDTO;
    }

    public FakeStoreProductDTO updateProductById(Long id, GenericProductDTO genericProductDTO) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO>fakeStoreProductDTOResponseEntity=
        restTemplate.exchange(singleProductUrl, HttpMethod.PUT, new HttpEntity<>(genericProductDTO), FakeStoreProductDTO.class, id);
        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductDTOResponseEntity.getBody();
        if(fakeStoreProductDTO==null){
            throw new NotFoundException("product with id:"+id+", is not found!");
        }
        return fakeStoreProductDTO;
    }
}
