package com.xiyan.xiyanapiinterface;

import com.xiyan.xiyanapiclientsdk.client.XiYanApiClient;
import com.xiyan.xiyanapiclientsdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class XiyanapiInterfaceApplicationTests {

    @Resource
    private XiYanApiClient xiYanApiClient;

    @Test
    void contextLoads() {
        String xiyan = xiYanApiClient.getNameByGet("xiyan");
        User user = new User();
        user.setUsername("xiyan");
        String usernameByPost = xiYanApiClient.getUsernameByPost(user);
        System.out.println(xiyan);
        System.out.println(usernameByPost);
    }

}
