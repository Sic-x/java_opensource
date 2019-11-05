package com.xmh.aisell.query;

import com.github.wenhao.jpa.Specifications;
import com.xmh.aisell.domain.SystemDictionaryType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class SystemDictionaryTypeQuery extends BaseQuery {

    //用户名
    private String name;

    //查询的规则应该在查询对象中来创建
    @Override
    public Specification createSpec(){
        Specification<SystemDictionaryType> specification = Specifications.<SystemDictionaryType>and()
                .like(StringUtils.isNotBlank(name), "name", "%" + name + "%")
                .build();
        return specification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
