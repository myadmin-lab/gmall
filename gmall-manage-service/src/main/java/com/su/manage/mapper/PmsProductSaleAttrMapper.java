package com.su.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.su.pojo.PmsProductSaleAttr;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PmsProductSaleAttrMapper extends BaseMapper<PmsProductSaleAttr>{

    List<PmsProductSaleAttr> selectSpuSaleAttrListCheckBySku(@Param("productId") Long productId,@Param("skuId") Long skuId);
}
