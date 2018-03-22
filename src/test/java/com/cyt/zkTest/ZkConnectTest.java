package com.cyt.zkTest;

import com.cyt.zk.ZkUtil;
import com.cyt.zk.ZooKeeperConnection;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * @author CaoYangTao
 * @date 2018/3/19  18:00
 */
@RunWith(JUnit4.class)
public class ZkConnectTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZkConnectTest.class);

    @Test
    public void testConnection() throws InterruptedException {
        ZkUtil zkUtil = null;
        try {
            zkUtil = getZkConnection();
            zkUtil.create("/zkDemoNode", new String("testData").getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (zkUtil != null) {
                zkUtil.getZk().close();
            }
        }
    }

    @Test
    public void testExist() throws InterruptedException {
        ZkUtil zkUtil = null;
        try {
            zkUtil = getZkConnection();
            Stat stat = zkUtil.zkNodeExists("/zkDemoNode");
            if (stat != null) {
                LOGGER.info(ToStringBuilder.reflectionToString(stat, ToStringStyle.SHORT_PREFIX_STYLE).toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (zkUtil != null) {
                zkUtil.getZk().close();
            }
        }
    }

    @Test
    public void testGetData() throws InterruptedException {
        ZkUtil zkUtil = null;
        try {
            zkUtil = getZkConnection();

            String zkNode = "/zkDemoNode";
            byte[] data = zkUtil.getData(zkNode, false, null);
            if (data != null) {
                String dataStr = new String(data, "UTF-8");
                LOGGER.info("zkNode={}, data = {}", zkNode, dataStr);
            }
        } catch (Exception e) {
            LOGGER.error("testGetData出错：", e);
        } finally {
            if (zkUtil != null) {
                zkUtil.getZk().close();
            }
        }
    }

    @Test
    public void testSetData() throws InterruptedException {
        ZkUtil zkUtil = null;
        try {
            zkUtil = getZkConnection();
            String zkNode = "/zkDemoNode";

            Stat statExist = zkUtil.zkNodeExists(zkNode);
            if (statExist != null) {
                byte[] newData = "newData".getBytes();
                int version = statExist.getVersion();
                Stat stat = zkUtil.setData(zkNode, newData, version);
                LOGGER.info(ToStringBuilder.reflectionToString(stat, ToStringStyle.SHORT_PREFIX_STYLE).toString());
            } else {
                LOGGER.warn("zkNode={} is not exists", zkNode);
            }
        } catch (Exception e) {
            LOGGER.error("testSetData出错：", e);
        } finally {
            if (zkUtil != null) {
                zkUtil.getZk().close();
            }
        }
    }

    @Test
    public void testGetChildren() throws InterruptedException {
        ZkUtil zkUtil = null;
        try {
            zkUtil = getZkConnection();
            String zkNode = "/FirstZnode";

            Stat statExist = zkUtil.zkNodeExists(zkNode);
            if (statExist != null) {
                List<String> childrenList = zkUtil.getChildren(zkNode, false);
                LOGGER.info(StringUtils.join(childrenList));
            } else {
                LOGGER.warn("zkNode={} is not exists", zkNode);
            }
        } catch (Exception e) {
            LOGGER.error("testGetChildren出错：", e);
        } finally {
            if (zkUtil != null) {
                zkUtil.getZk().close();
            }
        }
    }

    @Test
    public void testDelete() throws InterruptedException {
        ZkUtil zkUtil = null;
        try {
            zkUtil = getZkConnection();
            String zkNode = "/zkDemoNode";

            Stat statExist = zkUtil.zkNodeExists(zkNode);
            if (statExist != null) {
                zkUtil.delete(zkNode, statExist.getVersion());
            } else {
                LOGGER.warn("zkNode={} is not exists", zkNode);
            }
        } catch (Exception e) {
            LOGGER.error("testDelete出错：", e);
        } finally {
            if (zkUtil != null) {
                zkUtil.getZk().close();
            }
        }
    }

    private ZkUtil getZkConnection() throws IOException, InterruptedException {
        ZooKeeperConnection zooKeeperConnection = new ZooKeeperConnection();
        ZooKeeper zk = zooKeeperConnection.connect("localhost:2181");
        ZkUtil zkUtil = new ZkUtil();
        zkUtil.setZk(zk);
        return zkUtil;
    }
}
