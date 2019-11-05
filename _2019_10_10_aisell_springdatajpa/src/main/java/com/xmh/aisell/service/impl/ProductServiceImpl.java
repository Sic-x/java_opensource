package com.xmh.aisell.service.impl;

import com.xmh.aisell.domain.Product;
import com.xmh.aisell.repository.ProductRepository;
import com.xmh.aisell.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Product,Long>
        implements IProductService {

    @Autowired
    private ProductRepository productRepository;



}
