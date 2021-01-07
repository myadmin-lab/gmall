package com.su.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.su.manage.mapper.PmsSkuAttrValueMapper;
import com.su.manage.mapper.PmsSkuImageMapper;
import com.su.manage.mapper.PmsSkuInfoMapper;
import com.su.manage.mapper.PmsSkuSaleAttrValueMapper;
import com.su.pojo.PmsSkuAttrValue;
import com.su.pojo.PmsSkuImage;
import com.su.pojo.PmsSkuInfo;
import com.su.pojo.PmsSkuSaleAttrValue;
import com.su.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class SkuServiceImpl implements SkuService {
    @Autowired
    private PmsSkuInfoMapper pmsSkuInfoMapper;
    @Autowired
    private PmsSkuAttrValueMapper pmsSkuAttrValueMapper;
    @Autowired
    private PmsSkuSaleAttrValueMapper pmsSkuSaleAttrValueMapper;
    @Autowired
    private PmsSkuImageMapper pmsSkuImageMapper;

    /**
     * 添加sku
     *
     * @param pmsSkuInfo 当前添加的sku
     */
    @Override
    public void saveSkuInfo(PmsSkuInfo pmsSkuInfo) {
        //插入skuInfo
        pmsSkuInfoMapper.insert(pmsSkuInfo);

        Long skuId = pmsSkuInfo.getId();

        //插入平台属性
        List<PmsSkuAttrValue> skuAttrValueList = pmsSkuInfo.getSkuAttrValueList();
        for (PmsSkuAttrValue pmsSkuAttrValue : skuAttrValueList) {
            pmsSkuAttrValue.setSkuId(skuId);
            pmsSkuAttrValueMapper.insert(pmsSkuAttrValue);
        }

        //插入销售属性
        List<PmsSkuSaleAttrValue> skuSaleAttrValueList = pmsSkuInfo.getSkuSaleAttrValueList();
        for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : skuSaleAttrValueList) {
            pmsSkuSaleAttrValue.setSkuId(skuId);
            pmsSkuSaleAttrValueMapper.insert(pmsSkuSaleAttrValue);
        }

        //插入图片
        List<PmsSkuImage> skuImageList = pmsSkuInfo.getSkuImageList();
        for (PmsSkuImage pmsSkuImage : skuImageList) {
            pmsSkuImage.setSkuId(skuId);
            pmsSkuImageMapper.insert(pmsSkuImage);
        }
    }


    public PmsSkuInfo getSkuByIdFromDb(Long skuId){
        QueryWrapper<PmsSkuInfo> wrapper = new QueryWrapper<>();
        PmsSkuInfo skuInfo = pmsSkuInfoMapper.selectOne(wrapper.eq("id", skuId));

        //图片集合
        QueryWrapper<PmsSkuImage> wrapper2 = new QueryWrapper<>();
        List<PmsSkuImage> pmsSkuImages = pmsSkuImageMapper.selectList(wrapper2.eq("sku_id", skuId));
        skuInfo.setSkuImageList(pmsSkuImages);

        return skuInfo;
    }

    /**
     * 通过skuId查询sku
     *
     * @param skuId  sku的id
     * @return
     */
    @Override
    public PmsSkuInfo getSkuById(Long skuId) {
        PmsSkuInfo pmsSkuInfo = new PmsSkuInfo();
        //连接缓存

        //查询缓存
        
        //如果缓存中没有，查询mysql

        //mysql查询结果存入redis

        return null;
    }

    @Override
    public List<PmsSkuInfo> getSkuSaleAttrValueList(Long productId) {
        List<PmsSkuInfo> pmsSkuInfos = pmsSkuInfoMapper.selectSkuAttrValueListBySpu(productId);
        return pmsSkuInfos;
    }


}
