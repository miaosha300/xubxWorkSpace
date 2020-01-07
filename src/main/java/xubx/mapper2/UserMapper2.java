package xubx.mapper2;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 项目名称:   pinkstone
 * 包:        xubx.mapper2
 * 类名称:     UserMapper2
 * 类描述:     类功能描述
 * 创建人:     xubx
 * 创建时间:   2019/12/16 14:35
 */
public interface UserMapper2 {
    // 查询语句
    @Select("SELECT * FROM ac_bill_flow_route WHERE identifier = #{identifier}")
    String findByName(@Param("identifier") String identifier);

    // 添加
    @Insert("INSERT INTO ac_bill_flow_route (identifier, user_id) VALUES(#{identifier}, #{user_id})")
    int insert(@Param("identifier") String identifier, @Param("user_id") String user_id);

}
