package com.xmh.springcould.feignclient;


import com.xmh.springcloud.domain.User;
import com.xmh.springcould.fallback.UserFeignClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient :Feign的客户端接口标签 ， value属性指向要调用的服务的名字
// String url = "http://user-server/user/getById/"+id;
/**
 *  http://user-server/user/getById/1111
 */
/*@FeignClient(value = "user-server" )*/
@FeignClient(value = "user-server" , fallback = UserFeignClientFallback.class)
public interface UserFeignClient {

    //要调用的目标controller的方法 ，记住尽量去拷贝过来，保证一摸一样
    @GetMapping("/user/getById/{id}")
    User getById(@PathVariable("id") Long id);

}
