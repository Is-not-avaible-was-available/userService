package com.scaler.ProductService1.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "products")
public class Product extends BaseModel{
    private String name;
    private String description;
    private String image;
    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;
    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @JoinColumn(name = "price")
    private Price price;
}
