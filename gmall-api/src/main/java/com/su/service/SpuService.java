package com.su.service;

import com.su.pojo.PmsProductImage;
import com.su.pojo.PmsProductInfo;
import com.su.pojo.PmsProductSaleAttr;

import java.util.List;

public interface SpuService {
    //查询商品spu
    List<PmsProductInfo> spuList(String catalog3Id);

    //添加spu
    String saveSpuInfo(PmsProductInfo pmsProductInfo);

    //查询销售属性和销售属性值
    List<PmsProductSaleAttr> spuSaleAttrList(String spuId);

    //查询spu图片
    List<PmsProductImage> spuImageList(String spuId);

    //查询当前商品spu销售属性和对应sku销售属性值
    List<PmsProductSaleAttr> spuSaleAttrListCheckBySku(Long productId,Long skuId);
}
