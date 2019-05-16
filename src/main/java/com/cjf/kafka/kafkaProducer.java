package com.cjf.kafka;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.serializer.StringEncoder;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author chenjinfeng
 * @create 18:41 2019/5/16
 * @desc
 **/
public class kafkaProducer extends Thread {
    private String topic;

    public kafkaProducer(String topic) {
        super();
        this.topic = topic;
    }

    @Override
    public void run() {
        Producer producer = createProducer();
        int i = 0;
        while (true) {
            producer.send(new KeyedMessage<Integer, String>(topic, "message: " + i++));
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Producer createProducer() {
        Properties properties = new Properties();
        properties.put("zookeeper.connect", "localhost:2181");//声明zk
        properties.put("serializer.class", StringEncoder.class.getName());
        properties.put("metadata.broker.list", "localhost:9092");// 声明kafka broker
        return new Producer<Integer, String>(new ProducerConfig(properties));
    }

    public static void main(String[] args) {
        new kafkaProducer("test").start();// 使用kafka集群中创建好的主题 test       }
    }
}
