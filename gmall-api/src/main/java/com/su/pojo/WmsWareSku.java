package com.su.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author gmall
 * @since 2020-10-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WmsWareSku implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * skuid
     */
    private Long skuId;

    /**
     * 仓库id
     */
    private Long warehouseId;

    /**
     * 库存数
     */
    private Integer stock;

    /**
     * 存货名称
     */
    private String stockName;

    private Integer stockLocked;


}
