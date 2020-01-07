package xubx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteMsisdn implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 手机号码
     */
    private String msisdn;

    /**
     * 用户编码
     */
    private String userId;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 1 有效0 无效
     */
    private Byte state;

}