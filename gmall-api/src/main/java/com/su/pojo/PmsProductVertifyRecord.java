package com.su.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 商品审核记录
 * </p>
 *
 * @author gmall
 * @since 2020-10-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PmsProductVertifyRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long productId;

    private LocalDateTime createTime;

    /**
     * 审核人
     */
    private String vertifyMan;

    private Integer status;

    /**
     * 反馈详情
     */
    private String detail;


}
