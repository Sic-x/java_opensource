package cn.itsource._03_di;

import java.math.BigDecimal;
import java.util.*;

public class MyBean {

    // 简单属性
    private Long id;
    private String name;
    private Boolean sex;
    private BigDecimal salary;

    // 集合属性
    private List<OtherBean> otherBeanList;
    private Set<String> set;
    private Set<OtherBean> otherBeanSet;
    private List<String> list;
    private String[] arrays;
    private Map<Integer,OtherBean> map;

    //下面这个是重点
    private Properties props1;
    private Properties props2;



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

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public List<OtherBean> getOtherBeanList() {
        return otherBeanList;
    }

    public void setOtherBeanList(List<OtherBean> otherBeanList) {
        this.otherBeanList = otherBeanList;
    }

    public Set<String> getSet() {
        return set;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public Set<OtherBean> getOtherBeanSet() {
        return otherBeanSet;
    }

    public void setOtherBeanSet(Set<OtherBean> otherBeanSet) {
        this.otherBeanSet = otherBeanSet;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Properties getProps1() {
        return props1;
    }

    public void setProps1(Properties props1) {
        this.props1 = props1;
    }

    public Properties getProps2() {
        return props2;
    }

    public void setProps2(Properties props2) {
        this.props2 = props2;
    }

    public String[] getArrays() {
        return arrays;
    }

    public void setArrays(String[] arrays) {
        this.arrays = arrays;
    }

    public Map<Integer, OtherBean> getMap() {
        return map;
    }

    public void setMap(Map<Integer, OtherBean> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        System.out.println(otherBeanList);
        System.out.println(set);
        System.out.println(otherBeanSet);
        System.out.println(list);
        System.out.println(Arrays.asList(arrays));
        System.out.println(props1);
        System.out.println(props2);
        System.out.println(map);
        return "PhoneFactory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", salary=" + salary +
                '}';
    }
}
