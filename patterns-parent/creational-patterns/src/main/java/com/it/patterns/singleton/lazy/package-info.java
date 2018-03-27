/**
 * <pre>
 * Lazy initialization 懒汉方式。指全局的单例实例在<b>第一次被使用</b>时构建
 * 
 * 优点：实现了延迟加载，但是需要处理好多线程下同时访问问题，需要通过双重锁定等机制进行控制
 * 
 * 缺点：
 * ref饿汉的优点
 * 
 * </pre>
 */
package com.it.patterns.singleton.lazy;