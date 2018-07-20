package com.ch.storm;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import storm.kafka.KafkaSpout;
import storm.kafka.SpoutConfig;
import storm.kafka.ZkHosts;

import java.util.UUID;


public class LocalPology {
    private static final String SPOUT_ID = KafkaSpout.class.getName();

    public static void main(String[] args) throws InterruptedException {
        TopologyBuilder builder = new TopologyBuilder();


        String brokerZkStr = "master.ch:2181";
        ZkHosts zkHosts = new ZkHosts(brokerZkStr);
        //表示的是kafak中存储数据的主题名称
        String topic = "mytopic";
        //指定zookeeper中的一个根目录，里面存储kafkaspout读取数据的位置等信息
        String zkRoot = "/kafkaspout";
        String id = UUID.randomUUID().toString();
        SpoutConfig spoutconf  = new SpoutConfig(zkHosts, topic, zkRoot, id);
        builder.setSpout(SPOUT_ID , new KafkaSpout(spoutconf));

        builder.setSpout("out-word", new WordSpout(),1);
        builder.setBolt("word-count", new CountBolt(),1).shuffleGrouping("out-word");
        //本地模式:本地提交
        LocalCluster cluster = new LocalCluster();
        Config conf = new Config();
        conf.setNumWorkers(2);
        conf.setDebug(true);
        conf.put(Config.TOPOLOGY_MAX_TASK_PARALLELISM, 1);

        cluster.submitTopology("firstTopo", conf, builder.createTopology());
        //一定要等待足够的时间，否则程序没来得及运行就已经结束了，程序启动需要消耗时间
        Thread.sleep(30000);
        cluster.killTopology("firstTopo");
        cluster.shutdown();
    }
}
