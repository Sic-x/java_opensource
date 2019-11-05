package com.xmh.aisell.service.impl;

import com.xmh.aisell.domain.ProductType;
import com.xmh.aisell.query.ProductTypeQuery;
import com.xmh.aisell.repository.ProductTypeRepository;
import com.xmh.aisell.service.IProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeServiceImpl extends BaseServiceImpl<ProductType,Long>
        implements IProductTypeService {

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Override
    public List<ProductType> findChildren() {
        return productTypeRepository.findChildren();
    }

    @Override
    public List<ProductType> findParent() {
        return productTypeRepository.findParent();
    }
}
