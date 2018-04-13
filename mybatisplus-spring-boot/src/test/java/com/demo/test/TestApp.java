package com.demo.test;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.demo.entity.User;
import com.demo.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApp {
    private Logger log= LoggerFactory.getLogger(TestApp.class);
    @Autowired
    private IUserService userService;

    @Test
    public void Test(){

        showData();
        User user=new User("test","1111");
        log.info("插入数据:"+user.toString());
        userService.insert(user);
        showData();
        User u=userService.selectOne(new EntityWrapper<User>(user));
        log.info("数据库查出刚插入的数据:");
        log.info(u.toString());
        log.info("修改数据:");
        u.setPassword("update11111111");
        userService.updateById(u);
        showData();
        log.info("删除数据:");
        log.info(u.toString());
        userService.delete(new EntityWrapper<User>(u));
        showData();
    }

    public void showData(){
        log.info("查询：");
        List<User> users=userService.selectList(new EntityWrapper<User>());
        for(User user:users){
            log.info(user.toString());
        }
    }
}
