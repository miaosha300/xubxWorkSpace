package xubx.mapper1;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 项目名称:   pinkstone
 * 包:        xubx.mapper1
 * 类名称:     UserMapper1
 * 类描述:     类功能描述
 * 创建人:     xubx
 * 创建时间:   2019/12/16 11:34
 */
public interface UserMapper1 {
    // 查询语句
    @Select("SELECT userId from uc_route_msisdn where msisdn = #{msisdn}")
    String findByMsisdn(@Param("msisdn") String msisdn);

    // 添加
    @Insert("INSERT INTO uc_route_msisdn (msisdn, user_id) VALUES(#{msisdn}, #{user_id})")
    int insert(@Param("msisdn") String msisdn, @Param("user_id") String user_id);

}
