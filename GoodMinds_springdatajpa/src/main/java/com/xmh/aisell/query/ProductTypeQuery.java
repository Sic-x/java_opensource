package com.xmh.aisell.query;

import com.github.wenhao.jpa.Specifications;
import com.xmh.aisell.domain.ProductType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class ProductTypeQuery extends BaseQuery {

    private Long id;

    //用户名
    private String name;

    private Long parentId;

    //查询的规则应该在查询对象中来创建
    @Override
    public Specification createSpec(){
            Specification<ProductType> specification = Specifications.<ProductType>and()
                    .gt("parent.id",0)
                    .like(StringUtils.isNotBlank(name), "name", "%" + name + "%")
                    .eq(parentId!=null,"parent.id",parentId)
                    .build();
            return specification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long departmentId) {
        this.parentId = departmentId;
    }
}
