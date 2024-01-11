package com.scaler.ProductService1.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDTO {
    private Long id;
    private String title;
    private String description;
    private String category;
    private double price;
    private String image;
}
