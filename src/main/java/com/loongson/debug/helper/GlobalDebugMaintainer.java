package com.loongson.debug.helper;

import java.util.HashMap;

/**
 * 全局在线Debug维护器，借助单例模式维护debug状态
 */
public class GlobalDebugMaintainer {
    /**
     * 单例实体
     */
    private static GlobalDebugMaintainer instance;

    private static int count;
    private HashMap<Integer, DebugVar> map;

    private GlobalDebugMaintainer() {
        map = new HashMap<>();
    }

    public static GlobalDebugMaintainer getInstance() {
        if (instance == null) {
            instance = new GlobalDebugMaintainer();

        }
        return instance;
    }


    public int create(String ip) {
        count++;
        map.put(count, new DebugVar(count,ip));
        return count;
    }

    public DebugVar get(int id) {
        return map.get(id);
    }

    public void remove(int id) {
        map.remove(id);
    }

    public HashMap<Integer, DebugVar> getAll() {
        return this.map;
    }

}
