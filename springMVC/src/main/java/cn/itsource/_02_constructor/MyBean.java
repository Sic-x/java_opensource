package cn.itsource._02_constructor;

public class MyBean {
    private String name;
    private Integer age;
    private OtherBean otherBean;


    public MyBean(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public MyBean(String name, Integer age, OtherBean otherBean) {
        this.name = name;
        this.age = age;
        this.otherBean = otherBean;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public OtherBean getOtherBean() {
        return otherBean;
    }

    public void setOtherBean(OtherBean otherBean) {
        this.otherBean = otherBean;
    }

    @Override
    public String toString() {
        return "PhoneFactory{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", otherBean=" + otherBean +
                '}';
    }
}
