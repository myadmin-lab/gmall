package com.su.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.su.pojo.PmsBaseCatalog1;
import com.su.pojo.PmsBaseCatalog2;
import com.su.pojo.PmsBaseCatalog3;
import com.su.service.CatalogService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class CatalogController {

    @Reference
    private CatalogService catalogService;

    /**
     * 一级菜单查询
     * @return
     */
    @RequestMapping("/getCatalog1")
    public List<PmsBaseCatalog1> getCatalog1() {
        List<PmsBaseCatalog1> list = catalogService.getCatalog1();
        return list;
    }

    /**
     * 二级菜单查询
     * @param catalog1Id
     * @return
     */
    @RequestMapping("/getCatalog2")
    public List<PmsBaseCatalog2> getCatalog2(String catalog1Id) {
        List<PmsBaseCatalog2> list = catalogService.getCatalog2(catalog1Id);
        return list;
    }

    /**
     * 三级菜单查询
     * @param catalog2Id
     * @return
     */
    @RequestMapping("/getCatalog3")
    public List<PmsBaseCatalog3> getCatalog3(String catalog2Id) {
        List<PmsBaseCatalog3> list = catalogService.getCatalog3(catalog2Id);
        return list;
    }
}
