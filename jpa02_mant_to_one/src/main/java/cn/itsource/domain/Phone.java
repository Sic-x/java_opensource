package cn.itsource.domain;

import javax.persistence.*;

@Entity
@Table(name="phone")
public class Phone {

    @Id
    @GeneratedValue
    private Long id;
    private String types;
    private String number;
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;

    public Employee getManager() {
        return employee;
    }

    public void setManager(Employee manager) {
        this.employee = manager;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
