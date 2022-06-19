package com.fsb.jwt_authentication.product.api;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Data
@Table(name = "products")
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,length = 28)
    @Length(min =5,max =128)
    private String name;


    private float price;
}
