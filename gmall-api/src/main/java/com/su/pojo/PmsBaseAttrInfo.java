package com.su.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;


/**
 * <p>
 * 属性表
 * </p>
 *
 * @author gmall
 * @since 2020-10-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PmsBaseAttrInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 编号
     */

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 属性名称
     */

    private String attrName;

    private Long catalog3Id;

    /**
     * 启用：1 停用：0
     */

    private String isEnabled;


    @TableField(exist = false)
    List<PmsBaseAttrValue> attrValueList;



}
