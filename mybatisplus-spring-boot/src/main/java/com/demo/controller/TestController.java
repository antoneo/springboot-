package com.demo.controller;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.demo.entity.Message;
import com.demo.entity.User;
import com.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private IUserService userService;

    /**
     * 测试实体注解注入条件 LIKE 查询
     */
    @GetMapping("/like")
    public User test(@RequestParam long id ){
        EntityWrapper<User> ew=new EntityWrapper<>();
        User u=new User();
        u.setId(id);
        ew.setEntity(u);
        User user=userService.selectOne(ew);
        return user;
    }

    //增
    @GetMapping("/add")
    public Object addUser(){
        User user=new User("testAdd","123456");
        userService.insert(user);
        return userService.selectOne(new EntityWrapper<User>(user)).toString();
    }

    //删
    @GetMapping("/delete")
    public Object deleteUser(){
        String content=null;
        StringBuffer result=new StringBuffer();
        User user=new User("testAdd","123456");
        result.append("新增:");
        result.append(user.toString());
        result.append("\n");
        userService.delete(new EntityWrapper<User>(user));
        result.append("执行删除命令！");
        User u=userService.selectOne(new EntityWrapper<User>(user));

        if(u==null){
            content="数据为空！";
            result.append("\n");
            result.append("数据库查询：空");
        }else{
            content="数据还存在，删除失败！";
            result.append("\n");
            result.append("数据库查询：");
            result.append(u.toString());
        }

        return new Message("delete",content,result.toString());

    }

    //改
    @GetMapping("/update")
    public Object update(){
        StringBuffer sb= new StringBuffer();
        User user=new User("update","123456");
        userService.insert(user);
        sb.append("更新前插入：");
        User u=userService.selectOne(new EntityWrapper<User>(user));
        System.out.println("更新前ID："+u.getId());
        sb.append(u.toString());
        sb.append("\n");
        User user1=new User("update2","up123456");
        user1.setId(u.getId());
        userService.updateById(user1);
        sb.append("更新后查询：");
        User u1=new User();
        u1.setId(u.getId());
        sb.append(userService.selectOne(new EntityWrapper<User>(u1)));
        System.out.println("更新后ID："+u.getId());
        System.out.println(sb.toString());
        return new Message("update",sb.toString(),"");

    }

    //sql查
    @GetMapping("/selectBySql")
    public List<User> selectBySql(){
        return userService.selectListBySQL();
    }



    /**
     * 分页 PAGE
     */
    @GetMapping("/test")
    public Page<User> test() {
        return userService.selectPage(new Page<User>(0, 12));
    }


    /**
     * 参数分页模式
     * url: http://localhost:8081/page?size=1&current=1
     * @param page
     * @return
     */
    @GetMapping("/page")
    public Object page(Page page) {
        return userService.selectPage(page);
    }

    /**
     * ThreadLocal 模式分页
     * url: http://localhost:8081/pagehelper?size=1&current=1
     * @param page
     * @return
     */
    @GetMapping("/pagehelper")
    public Object pagehelper(Page page) {
        PageHelper.setPagination(page);
        page.setRecords(userService.selectList(null));
        page.setTotal(PageHelper.freeTotal());//获取总数并释放资源 也可以 PageHelper.getTotal()
        return page;
    }

    /**
     * 测试事物
     * <br>
     * 启动  Application 加上 @EnableTransactionManagement 注解其实可无默认貌似就开启了<br>
     * 需要事物的方法加上 @Transactional 必须的哦！！
     * 还是建议写在service层吧
     */

    @Transactional
    @GetMapping("/test_transactional")
    public void testTransactional() {
        userService.insert(new User("测试事物", "11111"));
        System.out.println(" 这里手动抛出异常，自动回滚数据");
        throw new RuntimeException();
    }
 }
