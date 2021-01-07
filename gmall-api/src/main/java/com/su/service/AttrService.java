package com.su.service;

import com.su.pojo.PmsBaseAttrInfo;
import com.su.pojo.PmsBaseAttrValue;
import com.su.pojo.PmsBaseSaleAttr;

import java.util.List;

public interface AttrService {
    //通过catalog3Id查询平台属性
    List<PmsBaseAttrInfo> getAttrInfoList(String catalog3Id);

    //添加或修改平台属性和属性值
    String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo);

    //查询平台属性值
    List<PmsBaseAttrValue> getAttrValueList(String attrId);

    //查询销售属性
    List<PmsBaseSaleAttr> baseSaleAttrList();
}
