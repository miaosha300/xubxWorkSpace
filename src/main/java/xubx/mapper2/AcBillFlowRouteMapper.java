package xubx.mapper2;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xubx.entity.AcBillFlowRoute;

import java.util.List;

public interface AcBillFlowRouteMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AcBillFlowRoute record);

    int insertSelective(AcBillFlowRoute record);

    AcBillFlowRoute selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AcBillFlowRoute record);

    int updateByPrimaryKey(AcBillFlowRoute record);

    List<AcBillFlowRoute> selectByIdentifier(String identifier);

    List<AcBillFlowRoute> selectByIdentifierAndType(@Param("identifier") String identifier
            , @Param("idType") String idType);

    String selectUserIdByIdentifierAndType(@Param("identifier") String identifier, @Param("idType")
            String idType, @Param("billType") String billType);

    int updateRouteByUserId(@Param("olduserId") String olduserId, @Param("newuserId") String newuserId);

    List<AcBillFlowRoute> selectByIdentifierAndBillType(@Param("identifier") String innerId);

    List<AcBillFlowRoute> selectByIdentifierAndBillTypeAndidType(@Param("identifier") String identifier
            , @Param("idType") String idType, @Param("billType") String billType);
}