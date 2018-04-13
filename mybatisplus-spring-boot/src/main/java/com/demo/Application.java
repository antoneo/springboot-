package com.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement
@SpringBootApplication
public class Application {

    protected final static Logger logger = LoggerFactory.getLogger(Application.class);


    /**
     * 测试
     *  like查询：     http://localhost:8081/like?id=1
     *  add:          http://localhost:8081/add
     *  delete:       http://localhost:8081/delete
     *  update：       http://localhost:8081/update
     *  selectBySql   http://localhost:8081/selectBySql
     *  分页           /test
     *  参数分页模式    http://localhost:8081/page?size=1&current=1
     *  ThreadLocal 模式分页             http://localhost:8081/pagehelper?size=1&current=1
     *  测试事物        http://localhost:8081/test_transactional
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
        logger.info("PortalApplication is success!");
    }

}
