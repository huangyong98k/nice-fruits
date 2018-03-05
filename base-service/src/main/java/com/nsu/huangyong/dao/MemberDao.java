package com.nsu.huangyong.dao;

import com.nsu.huangyong.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MemberDao extends JpaRepository<Member,Long>, JpaSpecificationExecutor<Member> {

}
