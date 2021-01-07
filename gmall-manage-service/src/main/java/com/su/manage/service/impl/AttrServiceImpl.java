package com.su.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.su.manage.mapper.PmsBaseAttrInfoMapper;
import com.su.manage.mapper.PmsBaseAttrValueMapper;
import com.su.manage.mapper.PmsBaseSaleAttrMapper;
import com.su.pojo.PmsBaseAttrInfo;
import com.su.pojo.PmsBaseAttrValue;
import com.su.pojo.PmsBaseSaleAttr;
import com.su.service.AttrService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class AttrServiceImpl implements AttrService {
    @Autowired
    private PmsBaseAttrInfoMapper pmsBaseAttrInfoMapper;

    @Autowired
    private PmsBaseAttrValueMapper pmsBaseAttrValueMapper;

    @Autowired
    private PmsBaseSaleAttrMapper pmsBaseSaleAttrMapper;


    /**
     * 通过catalog3Id查询平台属性
     *
     * @param catalog3Id 三级菜单类目的id
     * @return
     */
    @Override
    public List<PmsBaseAttrInfo> getAttrInfoList(String catalog3Id) {
        QueryWrapper<PmsBaseAttrInfo> wrapper = new QueryWrapper<>();
        List<PmsBaseAttrInfo> pmsBaseAttrInfos = pmsBaseAttrInfoMapper.selectList(wrapper.eq("catalog3_id", catalog3Id));

        //查询平台属性
        for (PmsBaseAttrInfo pmsBaseAttrInfo : pmsBaseAttrInfos) {
            Long id = pmsBaseAttrInfo.getId();
            QueryWrapper<PmsBaseAttrValue> wrapper2 = new QueryWrapper<>();
            List<PmsBaseAttrValue> pmsBaseAttrValues = pmsBaseAttrValueMapper.selectList(wrapper2.eq("attr_id", id));
            pmsBaseAttrInfo.setAttrValueList(pmsBaseAttrValues);
        }

        return pmsBaseAttrInfos;
    }

    /**
     * 添加或修改平台属性和属性值
     *
     * @param pmsBaseAttrInfo 当前选中的平台属性
     * @return
     */
    @Override
    public String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo) {
        /*
        获取属性名，判断是修改还是新增
         */
        String attrName = pmsBaseAttrInfo.getAttrName();
        QueryWrapper<PmsBaseAttrInfo> wrapper = new QueryWrapper<>();
        PmsBaseAttrInfo attrNameInfo = pmsBaseAttrInfoMapper.selectOne(wrapper.eq("attr_name", attrName));

        //新的属性值
        List<PmsBaseAttrValue> attrValueList = pmsBaseAttrInfo.getAttrValueList();

        //新增
        if (attrNameInfo == null) {
            // 保存属性名
            pmsBaseAttrInfoMapper.insert(pmsBaseAttrInfo);

            // 保存属性值
            for (PmsBaseAttrValue pmsBaseAttrValue : attrValueList) {
                pmsBaseAttrValue.setAttrId(pmsBaseAttrInfo.getId());
                pmsBaseAttrValueMapper.insert(pmsBaseAttrValue);
            }
        } else {
            //修改
            QueryWrapper<PmsBaseAttrValue> wrapper2 = new QueryWrapper<>();
            // 属性值删除
            pmsBaseAttrValueMapper.delete(wrapper2.eq("attr_id", attrNameInfo.getId()));

            // 将新的属性值插入
            for (PmsBaseAttrValue attrValue : attrValueList) {
                attrValue.setAttrId(attrNameInfo.getId());
                pmsBaseAttrValueMapper.insert(attrValue);
            }
        }
        return "success";
    }

    /**
     * 查询平台属性值
     *
     * @param attrId 平台属性id
     * @return
     */
    @Override
    public List<PmsBaseAttrValue> getAttrValueList(String attrId) {
        QueryWrapper<PmsBaseAttrValue> wrapper = new QueryWrapper<>();
        List<PmsBaseAttrValue> attrValues = pmsBaseAttrValueMapper.selectList(wrapper.eq("attr_id", attrId));
        return attrValues;
    }

    /**
     * 查询销售属性
     *
     * @return
     */
    @Override
    public List<PmsBaseSaleAttr> baseSaleAttrList() {
        List<PmsBaseSaleAttr> pmsBaseSaleAttrs = pmsBaseSaleAttrMapper.selectList(null);
        return pmsBaseSaleAttrs;
    }
}


