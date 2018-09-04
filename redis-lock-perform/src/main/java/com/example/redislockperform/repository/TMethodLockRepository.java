package com.example.redislockperform.repository;

import com.example.redislockperform.entity.TMethodLockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @Auther: changzhaoliang
 * @Date: 2018/9/4 20:25
 * @Description:
 */
public interface TMethodLockRepository extends JpaRepository<TMethodLockEntity,Long>,
        JpaSpecificationExecutor<TMethodLockEntity>{

    @Query("select u from TMethodLockEntity u where u.methodName = ?1")
    TMethodLockEntity findOneByMethodName(String methodName);
}
