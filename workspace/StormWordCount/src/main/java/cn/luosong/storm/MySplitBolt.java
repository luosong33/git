package cn.luosong.storm;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IBasicBolt;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import java.util.Map;

/**
 * Created by Administrator on 2016/5/2.
 */
public class MySplitBolt extends BaseRichBolt {

    OutputCollector collector;

    // 被storm框架while循环调用
    public void execute(Tuple input) {

        String line = input.getString(0);
//        String line1 = input.getStringByField();
        String[] arrWords = line.split("");
        for (String word :
                arrWords) {
            collector.emit(new Values(word,1));
        }
    }

    // 初始化
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {

        declarer.declare(new Fields("word","num"));
    }
}
