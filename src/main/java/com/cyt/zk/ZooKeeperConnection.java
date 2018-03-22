package com.cyt.zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author CaoYangTao
 * @date 2018/3/19  18:09
 */
public class ZooKeeperConnection {
    private static final int OUT_TIME = 5000;
    private ZooKeeper zoo;
    final CountDownLatch connectedSignal = new CountDownLatch(1);

    /**
     * 连接zk
     *
     * @param host
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ZooKeeper connect(String host) throws IOException, InterruptedException {

        zoo = new ZooKeeper(host, OUT_TIME, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                    connectedSignal.countDown();
                }
            }
        });

        connectedSignal.await();
        return zoo;
    }

    public void close() throws InterruptedException {
        zoo.close();
    }
}
