package com.example.redislockperform.dataBaseLock2;

/**
 * @Auther: changzhaoliang
 * @Date: 2018/9/9 18:59
 * @Description:
 */
public interface DbLockManager2 {
    /**
     * 对某业务进行添加排他锁
     */
    boolean lock(String methodName);

}
