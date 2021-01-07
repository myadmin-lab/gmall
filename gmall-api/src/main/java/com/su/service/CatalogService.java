package com.su.service;

import com.su.pojo.PmsBaseCatalog1;
import com.su.pojo.PmsBaseCatalog2;
import com.su.pojo.PmsBaseCatalog3;

import java.util.List;

public interface CatalogService {
    //一级菜单查询
    List<PmsBaseCatalog1> getCatalog1();

    //二级菜单查询
    List<PmsBaseCatalog2> getCatalog2(String catalog1Id);

    //三级菜单查询
    List<PmsBaseCatalog3> getCatalog3(String catalog2Id);

}
