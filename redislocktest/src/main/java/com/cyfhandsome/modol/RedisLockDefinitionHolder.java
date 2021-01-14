package com.cyfhandsome.modol;

/**
 * @author cyf
 * @date 2021/1/13 14:54
 */

public class RedisLockDefinitionHolder {
    /**
     * 业务唯一 key
     */
    private String businessKey;
    /**
     * 加锁时间 (秒 s)
     */
    private Long lockTime;
    /**
     * 上次更新时间（ms）
     */
    private Long lastModifyTime;
    /**
     * 保存当前线程
     */
    private Thread currentTread;
    /**
     * 总共尝试次数
     */
    private int tryCount;
    /**
     * 当前尝试次数
     */
    private int currentCount;
    /**
     * 更新的时间周期（毫秒）,公式 = 加锁时间（转成毫秒） / 3
     */
    private Long modifyPeriod;

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public Long getLockTime() {
        return lockTime;
    }

    public void setLockTime(Long lockTime) {
        this.lockTime = lockTime;
    }

    public Long getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Long lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public Thread getCurrentTread() {
        return currentTread;
    }

    public void setCurrentTread(Thread currentTread) {
        this.currentTread = currentTread;
    }

    public int getTryCount() {
        return tryCount;
    }

    public void setTryCount(int tryCount) {
        this.tryCount = tryCount;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
    }

    public Long getModifyPeriod() {
        return modifyPeriod;
    }

    public void setModifyPeriod(Long modifyPeriod) {
        this.modifyPeriod = modifyPeriod;
    }

    public RedisLockDefinitionHolder(String businessKey, Long lockTime, Long lastModifyTime, Thread currentTread, int tryCount) {
        this.businessKey = businessKey;
        this.lockTime = lockTime;
        this.lastModifyTime = lastModifyTime;
        this.currentTread = currentTread;
        this.tryCount = tryCount;
        this.modifyPeriod = lockTime * 1000 / 3;
    }
}
