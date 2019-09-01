package net.guides.springboot2.springboot2crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.guides.springboot2.springboot2crud.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String>{

}
