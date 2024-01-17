package com.scaler.ProductService1.Repositories;

import com.scaler.ProductService1.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    public Product findByNameAndPrice_Currency(String name, String currency);

    @Query(value = SQLQueries.productQuery, nativeQuery = true)
    public Product findByName(String name);
    @Query(value = "select products from products where products.name=:name", nativeQuery = false)
    public Product finalByName2(String name);
}
