package cn.luosong.storm;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;

/**
 * Created by Administrator on 2016/5/1.
 */
public class WordCountToplogMain {

    public static void main(String[] args) {

        // 准备一个topology生成器
        TopologyBuilder t = new TopologyBuilder();
        t.setSpout("mySpout", new MySpout(), 1);
        t.setBolt("mybolt1", new MySplitBolt(), 10).shuffleGrouping("mySpout");
        t.setBolt("mybolt2", new MyCountBolt(), 2).fieldsGrouping("mybolt1", new Fields("word"));

        // 2、创建一个configration，用来指定当前topology 需要的work数量
        Config config = new Config();
        config.setNumWorkers(2);

        // 3、提交任务  1本地2集群
//        StormSubmitter.submitTopology("mywordcount", config, t.createTopology());

        LocalCluster localCluster = new LocalCluster();
        localCluster.submitTopology("mywordcount",config,t.createTopology());


    }
}
