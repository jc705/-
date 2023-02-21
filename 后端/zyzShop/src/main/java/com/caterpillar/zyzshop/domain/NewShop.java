package com.caterpillar.zyzshop.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.caterpillar.zyzshop.dao.DivisionDao;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;


@Data
public class NewShop implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    @TableId(value = "sid", type = IdType.AUTO)
    private Long sid;

    /**
     * 商品名称
     */
    private String product;

    /**
     * 商品分区
     */
    private String division;

    /**
     * 商品价格
     */
    private Float money;

    /**
     * 商品介绍
     */
    private String Introduction;

    /**
     * 商品分类
     */
    private String sclass;

    /**
     * 商品图片
     */
    private String pic;

    /**
     * 发布人id
     */
    private Long uname;

}
