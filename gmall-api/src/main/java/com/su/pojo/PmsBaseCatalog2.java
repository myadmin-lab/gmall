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
public class PmsBaseCatalog2 implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 二级分类名称
     */
    private String name;

    /**
     * 一级分类编号
     */
    private Integer catalog1Id;


}
