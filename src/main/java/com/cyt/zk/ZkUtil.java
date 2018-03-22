package com.cyt.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author CaoYangTao
 * @date 2018/3/19  18:18
 */
public class ZkUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZkUtil.class);

    private ZooKeeper zk;

    public ZooKeeper getZk() {
        return zk;
    }

    public void setZk(ZooKeeper zk) {
        this.zk = zk;
    }

    public void create(String path, byte[] data) throws KeeperException, InterruptedException {
        zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    public Stat zkNodeExists(String path) throws KeeperException, InterruptedException {
        return zk.exists(path, true);
    }

    public byte[] getData(String path, Watcher watcher, Stat stat) {
        try {
            Stat statExist = zkNodeExists(path);
            if (statExist != null) {
                byte[] data = zk.getData(path, watcher, stat);
                return data;
            } else {
                LOGGER.warn("zk path={} is not exist", path);
            }
        } catch (Exception e) {
            LOGGER.error("zk path={} getData error ：", path, e);
        }
        return null;
    }

    public byte[] getData(String path, boolean watch, Stat stat) {
        try {
            Stat statExist = zkNodeExists(path);
            if (statExist != null) {
                byte[] data = zk.getData(path, watch, stat);
                return data;
            } else {
                LOGGER.warn("zk path={} is not exist", path);
            }
        } catch (Exception e) {
            LOGGER.error("zk path={} getData error ：", path, e);
        }
        return null;
    }

    public Stat setData(String path, byte[] data, int version) {
        try {
            Stat stat = zk.setData(path, data, version);
            return stat;
        } catch (Exception e) {
            LOGGER.error("zk path={}，version={}，setData error ：", path, version, e);
        }
        return null;
    }

    public List<String> getChildren(String path, boolean watch) {
        try {
            return zk.getChildren(path, watch);
        } catch (Exception e) {
            LOGGER.error("zk path={} getChildren error ：", path, e);
        }
        return null;
    }

    public void delete(String path, int version) {
        try {
            zk.delete(path, version);
            LOGGER.error("zk path={}，version={}，delete success.", path, version);
        } catch (Exception e) {
            LOGGER.error("zk path={}，version={}，delete error ：", path, version, e);
        }
    }
}
