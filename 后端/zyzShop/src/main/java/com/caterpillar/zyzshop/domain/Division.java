package com.caterpillar.zyzshop.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_division")
public class Division implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分区id
     */
    @TableId(value = "did", type = IdType.AUTO)
    private Integer did;

    /**
     * 分区名称
     */
    private String dname;



}
