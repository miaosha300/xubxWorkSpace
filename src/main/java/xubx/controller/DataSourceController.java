package xubx.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xubx.entity.RouteMsisdn;
import xubx.mapper1.RouteMsisdnMapper;
import xubx.mapper1.UserMapper1;
import xubx.service.ManyService4;

import java.util.List;

//import xubx.service.ManyService1;
//import xubx.service.ManyService2;
//import xubx.service.ManyService3;

/**
 * 项目名称:   pinkstone
 * 包:        xubx.controller
 * 类名称:     DataSourceController
 * 类描述:     类功能描述
 * 创建人:     xubx
 * 创建时间:   2019/12/16 11:29
 */
@RestController
@Slf4j
public class DataSourceController {

//    @Autowired
//    private ManyService1 manyService1;
//
//    @Resource
//    private ManyService2 manyService2;
//
//    @Autowired
//    private ManyService3 manyService3;

    @Autowired
    private ManyService4 manyService4;
    @Autowired
    private RouteMsisdnMapper routeMsisdnMapper;
    @Autowired
    private UserMapper1 userMapper1;

//    //http://localhost:8080/datasource1?msisdn=01518066117926&user_id=015U00000001
//    @RequestMapping(value = "datasource1")
//    public int datasource1(String msisdn, String user_id) {
//        return manyService1.insert(msisdn, user_id);
//    }
//
//    //http://localhost:8080/datasource2?identifier=01300000002&user_id=013U000000002
//    @RequestMapping(value = "datasource2")
//    public int datasource2(String identifier, String user_id) {
//        return manyService2.insert(identifier, user_id);
//    }
//
//    //http://localhost:8080/datasource3?identifier=01300000002&msisdn=01518066117926&user_id=013U000000002
//    @RequestMapping(value = "datasource3")
//    public int datasource3(String identifier,String msisdn, String user_id) {
//        return manyService3.insert(identifier,msisdn, user_id);
//    }

    //http://localhost:8080/datasource3?identifier=01300000002&msisdn=01518066117926&user_id=000000002
    @RequestMapping(value = "datasource4")
    public int datasource4() {
        return manyService4.insertMsisdn() + manyService4.insertFlow();
    }

    @RequestMapping(value = "datasource5")
    public int datasource5() {
        return manyService4.insertDB();
    }

    @RequestMapping(value = "/interceptor")
    public RouteMsisdn queryRouteMsisdn(@RequestParam(value = "phone")String phone) {
        RouteMsisdn routeMsisdn = routeMsisdnMapper.selectByPhone(phone);
        return routeMsisdn;
    }

    @RequestMapping(value = "/interceptorAll")
    public int queryMsisdnUserId() {
        userMapper1.deleteMsisdn();
        return 1;
    }

    @RequestMapping("/log")
    public String selectStudentMsg() {
        System.out.println("查询了学生信息");
        log.trace("trace查询了学生信息");
        log.info("info查询了学生信息");
        log.warn("warn查询了学生信息");
        log.error("erro查询了学生信息");
        log.debug("debug查询了学生信息");
        return "查询学生信息成功";
    }



}
