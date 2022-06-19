package com.fsb.jwt_authentication.product.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductApi {

    @Autowired
    ProductRepository repo;

    @GetMapping
    @RolesAllowed({"ROLE_CUSTOMER","ROLE_EDITOR"})
    public List<Product> List(){
        return repo.findAll();
    }

    @PostMapping
    @RolesAllowed({"ROLE_EDITOR"})
    public ResponseEntity<Product> create(@RequestBody @Valid Product product){
        Product savedProduct = repo.save(product);
        URI productURI=URI.create("/products/"+savedProduct.getId());
        return ResponseEntity.created(productURI).body(savedProduct);
    }
}
