//package xubx.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import xubx.mapper1.UserMapper1;
//
///**
// * 项目名称:   pinkstone
// * 包:        xubx.service
// * 类名称:     ManyService1
// * 类描述:     测试
// * 创建人:     xubx
// * 创建时间:   2019/12/16 14:29
// */
//@Service
//public class ManyService1 {
//    @Autowired
//    private UserMapper1 userMapper1;
//
//    @Transactional(transactionManager = "test1TransactionManager")
//    public int insert(String identifier, String user_id) {
//        userMapper1.insert(identifier, user_id);
//        throw new RuntimeException();
//    }
//}
