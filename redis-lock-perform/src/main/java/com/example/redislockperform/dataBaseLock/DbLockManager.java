package com.example.redislockperform.dataBaseLock;

/**
 * @Auther: changzhaoliang
 * @Date: 2018/9/4 19:35
 * @Description:
 */
public interface DbLockManager {
    /**
     * 加锁
     */
    boolean lock(String methodName);

    /**
     * 解锁
     */
    void unLock(String methodName);
}
