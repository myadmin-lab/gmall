package com.su.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.su.manage.mapper.PmsBaseCatalog1Mapper;
import com.su.manage.mapper.PmsBaseCatalog2Mapper;
import com.su.manage.mapper.PmsBaseCatalog3Mapper;
import com.su.pojo.PmsBaseCatalog1;
import com.su.pojo.PmsBaseCatalog2;
import com.su.pojo.PmsBaseCatalog3;
import com.su.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private PmsBaseCatalog1Mapper pmsBaseCatalog1Mapper;
    @Autowired
    private PmsBaseCatalog2Mapper pmsBaseCatalog2Mapper;
    @Autowired
    private PmsBaseCatalog3Mapper pmsBaseCatalog3Mapper;


    /**
     * 一级菜单查询
     *
     * @return
     */
    @Override
    public List<PmsBaseCatalog1> getCatalog1() {
        return pmsBaseCatalog1Mapper.selectList(null);
    }

    /**
     * 二级菜单查询
     *
     * @param catalog1Id 一级菜单类目id
     * @return
     */
    @Override
    public List<PmsBaseCatalog2> getCatalog2(String catalog1Id) {
        QueryWrapper<PmsBaseCatalog2> wrapper = new QueryWrapper<>();
        return pmsBaseCatalog2Mapper.selectList(wrapper.eq("catalog1_id", catalog1Id));
    }


    /**
     * 三级菜单查询
     *
     * @param catalog2Id 二级菜单类目id
     * @return
     */
    @Override
    public List<PmsBaseCatalog3> getCatalog3(String catalog2Id) {
        QueryWrapper<PmsBaseCatalog3> wrapper = new QueryWrapper<>();
        return pmsBaseCatalog3Mapper.selectList(wrapper.eq("catalog2_id", catalog2Id));
    }
}
