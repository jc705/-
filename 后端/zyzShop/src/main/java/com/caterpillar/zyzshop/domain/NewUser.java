package com.caterpillar.zyzshop.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_user")
public class NewUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "uid", type = IdType.AUTO)
    private Long uid;

    /**
     * 用户账号
     */
    private String uname;

    /**
     * 用户密码
     */
    private String upassword;

    /**
     * 用户邮箱
     */
    private String uemail;

    /**
     * 收藏
     */
    private Integer like;

    /**
     * 购物车
     */
    private Integer cart;
}
