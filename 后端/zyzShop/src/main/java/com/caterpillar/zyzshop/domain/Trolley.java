package com.caterpillar.zyzshop.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_trolley")
public class Trolley implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long uid;

    /**
     * 商品id
     */
    private Long sid;

    /**
     * 数量
     */
    private Integer tcount;

    /**
     * 状态
     */
    private Integer statics;

}
