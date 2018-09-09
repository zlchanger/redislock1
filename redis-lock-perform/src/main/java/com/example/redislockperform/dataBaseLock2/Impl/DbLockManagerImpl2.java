package com.example.redislockperform.dataBaseLock2.Impl;

import com.example.redislockperform.dataBaseLock2.DbLockManager2;
import com.example.redislockperform.entity.TMethodLockEntity;
import com.example.redislockperform.repository.TMethodLockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: changzhaoliang
 * @Date: 2018/9/9 18:59
 * @Description: 利用数据库的排他锁select...for update
 */
@Service
public class DbLockManagerImpl2 implements DbLockManager2{

    @Autowired
    private TMethodLockRepository lockRepository;

    /**
     * 利用数据库的排他锁确保只有一个线程能抢到资源 jpa的排他锁要在事务中完成
     * 因为mysql的锁基于单次会话，会话结束后就会解锁，试想一下，select for update 不加事务，等于执行完这句话就解锁了，等于没加
     * 所以在service上加@Transactional
     * @param methodName
     * @return
     */
    @Override
    public boolean lock(String methodName) {
        try{
            TMethodLockEntity lockEntity = lockRepository.findOneByMethodName2(methodName);
            if(lockEntity == null){
                return true;
            }
        }catch (Exception e){
            return false;
        }
        return false;
    }

    //锁释放，在事务结束后，锁会自动释放
    //1.阻塞 在调用findOneByMethodName2 （select...for update 失败后会自动阻塞）
    //2.锁释放 当对某线程加锁后，服务器宕机，数据库会自动将锁释放

}
