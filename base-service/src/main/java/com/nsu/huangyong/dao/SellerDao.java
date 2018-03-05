package com.nsu.huangyong.dao;

import com.nsu.huangyong.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SellerDao extends JpaRepository<Seller,Long>, JpaSpecificationExecutor<Seller>{
    /**
     * 匹配手机号和密码
     */
    Seller findSellerByPhoneNoAndLoginPassword(String phoneNo,String LoginPassword);

    /**
     * 根据手机号获取会员信息
     */
    Seller findSellerByPhoneNo(String phoneNo);

    /**
     * 重置密码
     */
    @Query(value = "update seller set login_password = ?1 where phone_no =?2",nativeQuery = true)
    @Modifying
    void resetPassword(String newPassword,String phoneNo);
}
