package xubx.mapper1;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 项目名称:   pinkstone
 * 包:        xubx.mapper1
 * 类名称:     UserMapper1
 * 类描述:     类功能描述
 * 创建人:     xubx
 * 创建时间:   2019/12/16 11:34
 */
@Transactional
public interface UserMapper1 {
    // 查询语句
    @Select("SELECT user_id from uc_route_msisdn")
    List<String> findMsisdn();

    // 查询语句
    @Select("delete from uc_route_msisdn")
    void deleteMsisdn();

    // 添加
    @Insert("INSERT INTO uc_route_msisdn (msisdn, user_id) VALUES(#{msisdn}, #{user_id})")
    int insert(@Param("msisdn") String msisdn, @Param("user_id") String user_id);

}
