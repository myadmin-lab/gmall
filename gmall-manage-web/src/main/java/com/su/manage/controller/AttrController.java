package com.su.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.su.pojo.PmsBaseAttrInfo;
import com.su.pojo.PmsBaseAttrValue;
import com.su.pojo.PmsBaseSaleAttr;
import com.su.service.AttrService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class AttrController {
    @Reference
    private AttrService attrService;

    /**
     * 通过catalog3Id查询平台属性
     *
     * @param catalog3Id
     * @return
     */
    @RequestMapping("/attrInfoList")
    public List<PmsBaseAttrInfo> attrInfoList(String catalog3Id) {
        List<PmsBaseAttrInfo> list = attrService.getAttrInfoList(catalog3Id);
        return list;
    }

    /**
     * 添加或修改平台属性和属性值
     *
     * @param pmsBaseAttrInfo
     * @return
     */
    @RequestMapping("/saveAttrInfo")
    public String saveAttrInfo(@RequestBody PmsBaseAttrInfo pmsBaseAttrInfo) {
        String success = attrService.saveAttrInfo(pmsBaseAttrInfo);
        return "success";
    }

    /**
     * 查询平台属性值
     *
     * @param attrId
     * @return
     */
    @RequestMapping("/getAttrValueList")
    public List<PmsBaseAttrValue> getAttrValueList(String attrId) {
        List<PmsBaseAttrValue> attrValueList = attrService.getAttrValueList(attrId);
        return attrValueList;
    }

    /**
     * 查询销售属性
     *
     * @return
     */
    @RequestMapping("/baseSaleAttrList")
    public List<PmsBaseSaleAttr> baseSaleAttrList() {
        List<PmsBaseSaleAttr> pmsBaseSaleAttrs = attrService.baseSaleAttrList();
        return pmsBaseSaleAttrs;
    }
}
