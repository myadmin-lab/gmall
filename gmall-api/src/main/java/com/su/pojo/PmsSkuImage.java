package com.su.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 库存单元图片表
 * </p>
 *
 * @author gmall
 * @since 2020-10-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PmsSkuImage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品id
     */
    private Long skuId;

    /**
     * 图片名称（冗余）
     */
    private String imgName;

    /**
     * 图片路径(冗余)
     */
    private String imgUrl;

    /**
     * 商品图片id
     */
    private Long spuImgId;

    /**
     * 是否默认
     */
    private String isDefault;


}
