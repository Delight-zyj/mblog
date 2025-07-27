package com.mblog.Utils;

/**
 * CurrentHolder类用于在线程本地存储中管理当前员工ID
 * 提供设置、获取和清除当前线程员工ID的功能
 * 同token拦截器为一部分
 */
public class CurrentHolder {

    /**
     * 线程本地变量，用于存储每个线程的员工ID
     * 使用ThreadLocal确保线程安全，每个线程拥有独立的员工ID副本
     */
    private static final ThreadLocal<Integer> CURRENT_LOCAL = new ThreadLocal<>();

    /**
     * 设置当前线程的员工ID
     * @param employeeId 员工ID，可以为null
     */
    public static void setCurrentId(Integer employeeId) {
        CURRENT_LOCAL.set(employeeId);
    }

    /**
     * 获取当前线程的员工ID
     * @return 返回当前线程存储的员工ID，如果未设置则返回null
     */
    public static Integer getCurrentId() {
        return CURRENT_LOCAL.get();
    }

    /**
     * 清除当前线程的员工ID
     * 防止内存泄漏，使用完毕后应调用此方法清理ThreadLocal中的数据
     */
    public static void remove() {
        CURRENT_LOCAL.remove();
    }
}

