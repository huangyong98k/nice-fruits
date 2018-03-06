package com.nsu.huangyong.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 卖家基础信息
 * @author Yong Huang
 */
@NoArgsConstructor
@Entity
@Data
public class Seller {
    /**
     * id
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * 卖家编号
     */
    private String sellerNo;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 性别
     */
    private String sex;
    /**
     * 真实姓名
     */
    private String trueName;
    /**
     * 证件类型
     */
    private String certificateType;
    /**
     * 证件号
     */
    private String certificateNo;
    /**
     * 登录密码
     */
    private String loginPassword;
    /**
     * 提现密码
     */
    private int withdrawalPassword;
    /**
     * 手机号码
     */
    private String phoneNo;
    /**
     * 注册时间
     */
    private String registerTime;
    /**
     * 状态（0：待审核 1：已实名 2：锁定 3：停用）
     */
    private int status;
    /**
     * 余额
     */
    private Double balance;
    /**
     * 头像图片地址
     */
    private String head_portrait;
    /**
     * 发货地址
     */
    private String send_address;

    public Seller(String sellerNo, String nickname, String sex, String trueName, String certificateType, String certificateNo, String loginPassword, String phoneNo, String registerTime, int status, Double balance, String head_portrait) {
        this.sellerNo = sellerNo;
        this.nickname = nickname;
        this.sex = sex;
        this.trueName = trueName;
        this.certificateType = certificateType;
        this.certificateNo = certificateNo;
        this.loginPassword = loginPassword;
        this.phoneNo = phoneNo;
        this.registerTime = registerTime;
        this.status = status;
        this.balance = balance;
        this.head_portrait = head_portrait;
    }
}
