package com.whu.study.security.demo.dao;

import com.whu.study.security.demo.data.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * JPA Repository for UserData.
 */
@Repository
public interface UserRepository
        extends JpaRepository<UserData, String>, JpaSpecificationExecutor<UserData> {
}
