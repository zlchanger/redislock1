package com.example.redislockperform.dataBaseLock.Impl;

import com.example.redislockperform.dataBaseLock.DbLockManager;
import com.example.redislockperform.entity.TMethodLockEntity;
import com.example.redislockperform.repository.TMethodLockRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Auther: changzhaoliang
 * @Date: 2018/9/4 20:27
 * @Description:
 */
public class DbLockManagerImpl implements DbLockManager{

    @Autowired
    private TMethodLockRepository methodLockRepository;

    /**
     * 利用数据库的唯一索引对method_name做了唯一限制，数据库保证了操作的唯一性
     * @param methodName
     * @return
     */
    @Override
    public boolean lock(String methodName) {
        try{
            TMethodLockEntity lockEntity = new TMethodLockEntity();
            lockEntity.setDesc("加锁");
            lockEntity.setMethodName(methodName);
            methodLockRepository.save(lockEntity);
        }catch (Exception e){
            return false;
        }
        return true;
    }


    @Override
    public void unLock(String methodName) {
        TMethodLockEntity lockEntity = methodLockRepository.findOneByMethodName(methodName);
        if(lockEntity != null){
            methodLockRepository.delete(lockEntity);
        }
    }
}
