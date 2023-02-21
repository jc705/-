package com.caterpillar.zyzshop.domain;

import lombok.Data;

@Data
public class Cart {
    /**
     * 用户id
     */
    private Long uid;

    /**
    * 商品id
    */
    private Long sid;

    /**
     * 商品数量
     */
    private Integer tcount;

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
     * 状态
     */
    private Integer statics;
}
