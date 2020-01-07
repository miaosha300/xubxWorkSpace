package xubx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xubx.entity.AcBillFlowRoute;
import xubx.entity.RouteMsisdn;
import xubx.mapper1.RouteMsisdnMapper;
import xubx.mapper2.AcBillFlowRouteMapper;

import java.util.Date;

/**
 * 项目名称:   pinkstone
 * 包:        xubx.service
 * 类名称:     ManyService2
 * 类描述:     测试分布式事务
 * 创建人:     xubx
 * 创建时间:   2019/12/16 14:36
 */
@Service
public class ManyService4 {
    @Autowired
    private RouteMsisdnMapper routeMsisdnMapper;
    @Autowired
    private AcBillFlowRouteMapper acBillFlowRouteMapper;

    @Transactional
    public int insertMsisdn() {
        RouteMsisdn routeMsisdn = new RouteMsisdn();
        routeMsisdn.setMsisdn("01518066117926");
        routeMsisdn.setUserId("015U000000002");
        Date date = new Date();
        routeMsisdn.setGmtCreate(date);
        routeMsisdn.setGmtModified(date);
        routeMsisdn.setState((byte)1);
        return routeMsisdnMapper.insertSelective(routeMsisdn);
    }

    @Transactional
    public int insertFlow() {
        AcBillFlowRoute acBillFlowRoute = new AcBillFlowRoute();
        acBillFlowRoute.setUserId("013U000000002");
        acBillFlowRoute.setIdentifier("01300000002");
        Date date = new Date();
        acBillFlowRoute.setGmtCreate(date);
        acBillFlowRoute.setGmtUpdate(date);
        acBillFlowRoute.setState((byte)1);
        return acBillFlowRouteMapper.insertSelective(acBillFlowRoute);
    }

    @Transactional
    public int insertDB() {
        RouteMsisdn routeMsisdn = new RouteMsisdn();
        routeMsisdn.setMsisdn("01518066117903");
        routeMsisdn.setUserId("015U000000003");
        Date date = new Date();
        routeMsisdn.setGmtCreate(date);
        routeMsisdn.setGmtModified(date);
        routeMsisdn.setState((byte)1);
        int a = routeMsisdnMapper.insertSelective(routeMsisdn);

        AcBillFlowRoute acBillFlowRoute = new AcBillFlowRoute();
        acBillFlowRoute.setUserId("013U000000003");
        acBillFlowRoute.setIdentifier("01300000003");
        acBillFlowRoute.setGmtCreate(date);
        acBillFlowRoute.setGmtUpdate(date);
        acBillFlowRoute.setState((byte)1);
        int b = acBillFlowRouteMapper.insertSelective(acBillFlowRoute);
         int c = a/0;
        return c;
    }
}
