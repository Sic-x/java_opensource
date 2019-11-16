package com.xmh.aisell.service;


import com.xmh.aisell.domain.ProductType;

import java.util.List;

public interface IProductTypeService extends IBaseService<ProductType, Long> {

    List<ProductType> findChildren();

    List<ProductType> findParent();



}


