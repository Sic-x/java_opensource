package com.xmh.aisell.repository;

import com.xmh.aisell.domain.ProductType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ProductTypeRepository extends BaseRepository<ProductType,Long> {

   /* @Override
    @Query( value = "SELECT * FROM producttype p WHERE p.parent_id IS NOT NULL ORDER BY ?#{#pageable}",
            nativeQuery = true)
    Page<ProductType> findAll(Specification spec, Pageable pageable);*/

    @Query("select o from ProductType o where o.parent is not null ")
    List<ProductType> findChildren();

    @Query("select o from ProductType o where o.parent is null ")
    List<ProductType> findParent();
}
