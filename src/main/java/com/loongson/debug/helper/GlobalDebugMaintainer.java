package com.loongson.debug.helper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.loongson.debug.entity.OnlineDebug;
import com.loongson.debug.service.IOnlineDebugService;
import com.loongson.debug.util.BeanUtils;

import java.util.HashMap;
import java.util.List;

/**
 * 全局在线Debug维护器，借助单例模式维护debug状态
 */
public class GlobalDebugMaintainer {
    /**
     * 单例实体
     */
    private static GlobalDebugMaintainer instance;

    private IOnlineDebugService iOnlineDebugService;
    private static HashMap<Integer, OnlineDebug> cache;

    public static GlobalDebugMaintainer getInstance() {
        if (instance == null) {
            instance = new GlobalDebugMaintainer();
            cache = new HashMap<>();

        }
        return instance;
    }

    private IOnlineDebugService onlineDebugService() {
        if (iOnlineDebugService == null) {
            this.iOnlineDebugService = BeanUtils.getBean(IOnlineDebugService.class);
        }
        return iOnlineDebugService;
    }


    public int create(String ip) {
        OnlineDebug onlineDebug = new OnlineDebug(ip);
        onlineDebugService().save(onlineDebug);
        return onlineDebug.getUid();
    }

    public OnlineDebug get(int id) {
        if (cache.containsKey(id)) {
            return cache.get(id);
        } else {
            OnlineDebug onlineDebug = onlineDebugService().getById(id);
            cache.put(id, onlineDebug);
            return onlineDebug;
        }

    }

    public void remove(int id) {
        cache.remove(id);
        onlineDebugService().removeById(id);
    }

    public List<OnlineDebug> getAll() {
        return onlineDebugService().list(new QueryWrapper<OnlineDebug>().orderByDesc("createTime"));
    }

    public void setDEBUG(int id, boolean value) {
        update(id, "DEBUG", value);
    }

    public void setBreakPointAddress(int id, long breakPointAddress) {
        update(id, "breakPointAddress", breakPointAddress);
    }

    public void setCanStart(int id, boolean canStart) {
        update(id, "canStart", canStart);
    }

    public void setDebugState(int id, int debugState) {
        update(id, "debugState", debugState);
    }

    public void setPreviousTrace(int id, String value) {
        update(id, "previousTrace", value);

    }

    public void setCanExecute(int id, boolean canExecute) {
        update(id, "canExecute", canExecute);
    }

    public void setCurrentAddress(int id, long currentAddress) {
        update(id, "currentAddress", currentAddress);
    }

    public void setEnd(int id, boolean end) {
        update(id, "isEnd", end);

    }

    void update(int id, String coloum, Object value) {
        UpdateWrapper<OnlineDebug> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("uid", id).set(coloum, value);
        boolean update = onlineDebugService().update(updateWrapper);
        if (update) {
            cache.put(id, onlineDebugService().getById(id));
        }
    }

    public void updateByObject(OnlineDebug onlineDebug) {
        boolean update = onlineDebugService().updateById(onlineDebug);
        if (update) {
            cache.put(onlineDebug.getUid(), onlineDebugService().getById(onlineDebug.getUid()));
        }
    }
}
