//package xubx.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import xubx.mapper2.UserMapper2;
//
///**
// * 项目名称:   pinkstone
// * 包:        xubx.service
// * 类名称:     ManyService2
// * 类描述:     类功能描述
// * 创建人:     xubx
// * 创建时间:   2019/12/16 14:36
// */
//@Service
//public class ManyService2 {
//    @Autowired
//    private UserMapper2 userMapper2;
//
//    @Transactional(transactionManager = "test2TransactionManager")
//    public int insert(String identifier, String user_id) {
//        userMapper2.insert(identifier, user_id);
//        throw new RuntimeException();
//    }
//}
