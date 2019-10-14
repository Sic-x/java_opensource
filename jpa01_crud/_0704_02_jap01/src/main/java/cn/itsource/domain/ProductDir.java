package cn.itsource.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_productdir")
public class ProductDir {

    @Id
    @GeneratedValue
    Long id;
    String name;

    public ProductDir() {

    }

    public ProductDir(String name) {
        this.name = name;
    }

    public ProductDir(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProductDir{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
