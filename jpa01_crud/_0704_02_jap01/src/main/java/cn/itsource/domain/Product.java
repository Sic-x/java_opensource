package cn.itsource.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_product")
public class Product {
    @Id
    @GeneratedValue
    Long id;
    String name;
    Long dirId;

    public Product() {
    }

    public Product(Long id, String name, Long dirId) {
        this.id = id;
        this.name = name;
        this.dirId = dirId;
    }

    public Product(String name, Long dirId) {
        this.name = name;
        this.dirId = dirId;
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

    public Long getDirId() {
        return dirId;
    }

    public void setDirId(Long dirId) {
        this.dirId = dirId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dirId=" + dirId +
                '}';
    }
}
