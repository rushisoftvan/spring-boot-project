package com.productmanagmentsystem.repository;

import com.productmanagmentsystem.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Integer> {


}
