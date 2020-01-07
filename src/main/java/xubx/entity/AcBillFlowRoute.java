package xubx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcBillFlowRoute implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 编号类型 : 0:内部编号 1:外部编号
     */
    private String idType;

    /**
     * 编号
     */
    private String identifier;

    /**
     * 账单类型: 0-充值 1-扣费 2-补款 3-预留扣费 12:付费延期 13:免费延期
     */
    private String billType;

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
    private Date gmtUpdate;

    /**
     * 0：无效 1：有效
     */
    private Byte state;


}