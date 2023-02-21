package com.caterpillar.zyzshop.domain;

import lombok.Data;

@Data
public class Collect {
    /**
     * 用户id
     */
    private Long uid;

    /**
    * 商品id
    */
    private Long sid;

    /**
     * 商品名称
     */
    private String product;

    /**
     * 商品价格
     */
    private Float money;

    /**
     * 商品图片
     */
    private String pic;

    /**
     * 商品分类
     */
    private String sclass;
}
