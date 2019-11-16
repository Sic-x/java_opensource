package com.xmh.aisell.domain;

        import javax.persistence.GeneratedValue;
        import javax.persistence.Id;
        import javax.persistence.MappedSuperclass;


/**
 *  在JPA 如果要抽取父类必须加@MappedSuperclass注解
 *  代表这是用于映射的父类
 *  JPA才会读取
 *
 * */
@MappedSuperclass
public class BaseDomain {

    @Id
    @GeneratedValue
    protected Long id;

    /**
     * @return 返回该实体类的主键
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id 设置该实体类的主键
     */
    public void setId(Long id) {
        this.id = id;
    }
}
