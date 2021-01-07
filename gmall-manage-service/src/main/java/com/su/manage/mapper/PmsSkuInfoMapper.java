package com.su.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.su.pojo.PmsSkuInfo;

import java.util.List;

public interface PmsSkuInfoMapper extends BaseMapper<PmsSkuInfo>{
    List<PmsSkuInfo> selectSkuAttrValueListBySpu(Long productId);
}
