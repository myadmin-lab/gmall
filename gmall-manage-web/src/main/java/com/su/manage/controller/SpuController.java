package com.su.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.su.manage.util.PmsUploadUtil;
import com.su.pojo.PmsProductImage;
import com.su.pojo.PmsProductInfo;
import com.su.pojo.PmsProductSaleAttr;
import com.su.service.SpuService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
public class SpuController {
    @Reference
    private SpuService spuService;

    /**
     * 查询商品spu
     *
     * @param catalog3Id 三级菜单类目id
     * @return
     */
    @RequestMapping("/spuList")
    public List<PmsProductInfo> spuList(String catalog3Id) {
        List<PmsProductInfo> pmsProductInfos = spuService.spuList(catalog3Id);
        return pmsProductInfos;
    }

    /**
     * 添加spu
     *
     * @param pmsProductInfo 商品spu
     * @return
     */
    @RequestMapping("/saveSpuInfo")
    public String saveSpuInfo(@RequestBody PmsProductInfo pmsProductInfo) {
        String success = spuService.saveSpuInfo(pmsProductInfo);
        return "success";
    }

    /**
     * 查询销售属性和销售属性值
     *
     * @param spuId 当前spu的id
     * @return
     */
    @RequestMapping("/spuSaleAttrList")
    public List<PmsProductSaleAttr> spuSaleAttrList(String spuId) {
        List<PmsProductSaleAttr> pmsProductSaleAttrs = spuService.spuSaleAttrList(spuId);
        return pmsProductSaleAttrs;
    }

    /**
     * 图片上传到fastdfs
     *
     * @param multipartFile 图片文件
     * @return
     */
    @RequestMapping("/fileUpload")
    public String fileUpload(@RequestParam("file") MultipartFile multipartFile) {
        //将文件或音频上传到分布式文件储存系统
        //将图片的存储路径返回页面
        String imgUrl = PmsUploadUtil.uploadImage(multipartFile);
        return imgUrl;
    }

    /**
     * 查询spu图片
     *
     * @param spuId 当前spu的id
     * @return
     */
    @RequestMapping("/spuImageList")
    public List<PmsProductImage> spuImageList(String spuId) {
        List<PmsProductImage> pmsProductImages = spuService.spuImageList(spuId);
        return pmsProductImages;
    }
}
