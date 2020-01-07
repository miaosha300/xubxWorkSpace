package xubx.mapper1;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xubx.entity.RouteMsisdn;

import java.util.List;

public interface RouteMsisdnMapper {
    int deleteByPrimaryKey(Long id);
    int insert(RouteMsisdn record);
    int insertSelective(RouteMsisdn record);
    int updateByMsisdnIdSelective(RouteMsisdn record);
    int updateByPrimaryKeySelective(RouteMsisdn record);
    int updateByPrimaryKey(RouteMsisdn record);

    RouteMsisdn selectByPhone(@Param("phone") String phone);//根据手机号到号码路由表查找
    RouteMsisdn selectByMsisdnAndState(@Param("routeMsisdn") RouteMsisdn routeMsisdn);
    RouteMsisdn selectByUserId(@Param("routeMsisdn") RouteMsisdn record);
    List<RouteMsisdn> selectByRouteMsisdn(@Param("routeMsisdn") RouteMsisdn record);
    RouteMsisdn findByUserId(String userId);
    void deleteByPhone(String phone);
    void deleteByUserId(String useId);
}