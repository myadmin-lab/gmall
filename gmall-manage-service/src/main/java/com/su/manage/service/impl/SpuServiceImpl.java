package com.su.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.su.manage.mapper.PmsProductImageMapper;
import com.su.manage.mapper.PmsProductInfoMapper;
import com.su.manage.mapper.PmsProductSaleAttrMapper;
import com.su.manage.mapper.PmsProductSaleAttrValueMapper;
import com.su.pojo.PmsProductImage;
import com.su.pojo.PmsProductInfo;
import com.su.pojo.PmsProductSaleAttr;
import com.su.pojo.PmsProductSaleAttrValue;
import com.su.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SpuServiceImpl implements SpuService {
    @Autowired
    private PmsProductInfoMapper pmsProductInfoMapper;

    @Autowired
    private PmsProductImageMapper pmsProductImageMapper;

    @Autowired
    private PmsProductSaleAttrMapper pmsProductSaleAttrMapper;

    @Autowired
    private PmsProductSaleAttrValueMapper pmsProductSaleAttrValueMapper;

    /**
     * 查询商品spu
     *
     * @param catalog3Id 三级菜单类目id
     * @return
     */
    @Override
    public List<PmsProductInfo> spuList(String catalog3Id) {
        QueryWrapper<PmsProductInfo> wrapper = new QueryWrapper<>();
        List<PmsProductInfo> pmsProductInfos = pmsProductInfoMapper.selectList(wrapper.eq("catalog3_id", catalog3Id));
        return pmsProductInfos;
    }

    /**
     * 添加spu
     *
     * @param pmsProductInfo 商品spu
     * @return
     */
    @Override
    public String saveSpuInfo(PmsProductInfo pmsProductInfo) {
        //保存商品信息
        pmsProductInfoMapper.insert(pmsProductInfo);

        //获取商品主键Id
        Long id = pmsProductInfo.getId();

        //保存商品图片信息
        List<PmsProductImage> spuImageList = pmsProductInfo.getSpuImageList();
        for (PmsProductImage image : spuImageList) {
            image.setProductId(id);
            pmsProductImageMapper.insert(image);
        }

        //保存销售属性信息
        List<PmsProductSaleAttr> spuSaleAttrList = pmsProductInfo.getSpuSaleAttrList();
        for (PmsProductSaleAttr pmsProductSaleAttr : spuSaleAttrList) {
            //保存商品属性名
            pmsProductSaleAttr.setProductId(id);
            pmsProductSaleAttrMapper.insert(pmsProductSaleAttr);

            //保存商品属性值
            List<PmsProductSaleAttrValue> spuSaleAttrValueList = pmsProductSaleAttr.getSpuSaleAttrValueList();

            for (PmsProductSaleAttrValue pmsProductSaleAttrValue : spuSaleAttrValueList) {
                pmsProductSaleAttrValue.setProductId(id);
                pmsProductSaleAttrValueMapper.insert(pmsProductSaleAttrValue);
            }
        }
        return "success";
    }

    /**
     * 查询销售属性和销售属性值
     *
     * @param spuId 当前spu的id
     * @return
     */
    @Override
    public List<PmsProductSaleAttr> spuSaleAttrList(String spuId) {
        QueryWrapper<PmsProductSaleAttr> wrapper = new QueryWrapper<>();
        List<PmsProductSaleAttr> pmsProductSaleAttrs = pmsProductSaleAttrMapper.selectList(wrapper.eq("product_id", spuId));
        for (PmsProductSaleAttr pmsProductSaleAttr : pmsProductSaleAttrs) {
            Long saleAttrId = pmsProductSaleAttr.getSaleAttrId();
            QueryWrapper<PmsProductSaleAttrValue> wrapper2 = new QueryWrapper<>();
            Map<String, Object> map = new HashMap<>();
            map.put("product_id", spuId);
            map.put("sale_attr_id", saleAttrId);
            List<PmsProductSaleAttrValue> pmsProductSaleAttrValues = pmsProductSaleAttrValueMapper.selectList(wrapper2.allEq(map));
            pmsProductSaleAttr.setSpuSaleAttrValueList(pmsProductSaleAttrValues);
        }
        return pmsProductSaleAttrs;
    }

    /**
     * 查询spu图片
     *
     * @param spuId 当前spu的id
     * @return
     */
    @Override
    public List<PmsProductImage> spuImageList(String spuId) {
        QueryWrapper<PmsProductImage> wrapper = new QueryWrapper<>();
        List<PmsProductImage> pmsProductImages = pmsProductImageMapper.selectList(wrapper.eq("product_id", spuId));
        return pmsProductImages;
    }

    /**
     * 查询商品spu销售属性和对应sku销售属性值
     *
     * @param productId 当前spu的id
     * @param skuId     当前sku的id
     * @return
     */
    @Override
    public List<PmsProductSaleAttr> spuSaleAttrListCheckBySku(Long productId, Long skuId) {
        List<PmsProductSaleAttr> pmsProductSaleAttrs = pmsProductSaleAttrMapper.selectSpuSaleAttrListCheckBySku(productId, skuId);
        return pmsProductSaleAttrs;
    }
}
