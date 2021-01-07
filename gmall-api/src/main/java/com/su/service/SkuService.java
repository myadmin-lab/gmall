package com.su.service;

import com.su.pojo.PmsSkuInfo;

import java.util.List;

public interface SkuService {
    //添加sku
    void saveSkuInfo(PmsSkuInfo pmsSkuInfo);

    //通过skuId查询sku
    PmsSkuInfo getSkuById(Long skuId);

    //查询当前sku的spu的其他集合的hash表
    List<PmsSkuInfo> getSkuSaleAttrValueList(Long productId);
}
