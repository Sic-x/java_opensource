package cn.itsource._07_bean_create._03_static_factory;


//手机工厂
public class PhoneFactory{
    public static Phone createPhone() {
        return new Phone();
    }

}
