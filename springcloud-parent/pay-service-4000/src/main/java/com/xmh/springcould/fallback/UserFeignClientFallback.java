package com.xmh.springcould.fallback;


import com.xmh.springcloud.domain.User;
import com.xmh.springcould.feignclient.UserFeignClient;
import org.springframework.stereotype.Component;

@Component
public class UserFeignClientFallback implements UserFeignClient {
    @Override
    public User getById(Long id) {
        return new User(-1l,"无效的用户","用户服务不可用...");
    }
}
