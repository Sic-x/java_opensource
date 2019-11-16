package com.xmh.aisell.query;

import com.github.wenhao.jpa.Specifications;
import com.xmh.aisell.domain.Product;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


public class ProductQuery extends BaseQuery{

    //产品名称
    private String name;

    private Long productTypeId;


    @Override
    public Specification createSpec() {

        Specification<Product> specification = Specifications.<Product>and()
                //StringUtils 是commons-lang3工具包提供的工具
                //确认传来的值是否为空或空字符串,如果为空的话则不执行后面的语句,不为空则执行
                .like(StringUtils.isNoneBlank(name), "name", "%" + name + "%")
                .eq(productTypeId!=null,"productType.id",productTypeId)
                .build();
        return specification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Long productTypeId) {
        this.productTypeId = productTypeId;
    }
}
