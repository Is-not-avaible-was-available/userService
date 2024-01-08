package com.scaler.ProductServicePractice.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDTO {
    private String title;
    private Long id;
    private String description;
    private String image;
    private String category;
    private double price;
}
