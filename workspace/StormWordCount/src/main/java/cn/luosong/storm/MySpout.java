package cn.luosong.storm;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

import java.util.Map;

/**
 * Created by Administrator on 2016/5/2.
 */
public class MySpout extends BaseRichSpout {

    SpoutOutputCollector collector;

    // 初始化方法
    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {

        this.collector = collector;
    }

    // storm框架在while（true）调用nextTuple方法
    public void nextTuple() {

        collector.emit(new Values(" i am lilei love hameimei"));
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {

        declarer.declare(new Fields("love"));
    }
}
