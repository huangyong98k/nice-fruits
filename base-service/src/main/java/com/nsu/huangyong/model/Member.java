package com.nsu.huangyong.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@Entity
@Data
public class Member {
    /**
     * id
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * 会员编号
     */
    private String memberNo;
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
    private int certificateType;
    /**
     * 证件号
     */
    private String certificateNo;
    /**
     * 登录密码
     */
    private String loginPassword;
    /**
     * 支付密码
     */
    private String payPassword;
    /**
     * 电话号码
     */
    private String phoneNo;
    /**
     * 出生日期
     */
    private String birthDate;
    /**
     * 会员等级（0：普通会员 ）
     */
    private int member_level;
    /**
     * 会员到期时间
     */
    private String dueTime;
    /**
     * 状态（0：正常 1：锁定 2：停用）
     */
    private String registerTime;
    /**
     * 会员编号
     */
    private int status;
    /**
     * 头像图片地址
     */
    private String headPortrait;

    public Member(String memberNo, String nickname, String sex, String loginPassword, String phoneNo,String registerTime, int status, String headPortrait) {
        this.memberNo = memberNo;
        this.nickname = nickname;
        this.sex = sex;
        this.loginPassword = loginPassword;
        this.phoneNo = phoneNo;
        this.registerTime = registerTime;
        this.status = status;
        this.headPortrait = headPortrait;
    }
}
