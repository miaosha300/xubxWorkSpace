//package xubx.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import xubx.mapper1.UserMapper1;
//import xubx.mapper2.UserMapper2;
//
///**
// * 项目名称:   pinkstone
// * 包:        xubx.service
// * 类名称:     ManyService2
// * 类描述:     测试两个事务，第一条语句不能回滚
// * 创建人:     xubx
// * 创建时间:   2019/12/16 14:36
// */
//@Service
//public class ManyService3 {
//    @Autowired
//    private UserMapper2 userMapper2;
//    @Autowired
//    private UserMapper1 userMapper1;
//
//    @Transactional(transactionManager = "test2TransactionManager")
//    public int insert(String identifier, String msisdn, String user_id) {
//        userMapper1.insert(msisdn, user_id);
//        userMapper2.insert(identifier, user_id);
//        throw new RuntimeException();
//    }
//}
