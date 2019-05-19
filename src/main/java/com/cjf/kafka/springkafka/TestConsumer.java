package com.cjf.kafka.springkafka;

import com.alibaba.fastjson.JSONObject;
import com.cjf.es.entity.Item;
import com.cjf.es.repository.ItemRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * kafka消费者测试
 */
@Component
public class TestConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(TestKafkaProducerController.class);

    @Autowired
    private ItemRepository itemRepository;

    @KafkaListener(topics = "test_topic1")
    public void listen(ConsumerRecord<String, String> record) throws Exception {

        try {
            Item item = JSONObject.parseObject(record.value(), Item.class);
            LOG.info("topic: {}, offset: {}, value:{}", record.topic(), record.offset(), record.value());
            itemRepository.save(item);

        } catch (Exception e) {
            LOG.error("parse record: {} occur", record.value(), e);
        }
    }

    /**
     * 监听topic2主题,单条消费
     */
//    @KafkaListener(topics = "${topicName.topic1}")
//    public void listen1(ConsumerRecord<String, String> record) {
//        consumer(record);
//    }

    /**
     * 监听topic5,批量消费
     */
    @KafkaListener(topics = "${topicName.topic2}", containerFactory = "batchFactory")
    public void listen2(List<ConsumerRecord<String, String>> records) {
        batchConsumer(records);
    }

    /**
     * 批量消费
     */
    public void batchConsumer(List<ConsumerRecord<String, String>> records) {
        records.forEach(this::consumer);
    }

    /**
     * 单条消费
     */
    public void consumer(ConsumerRecord<String, String> record) {
        LOG.info("主题:{}, 内容: {}", record.topic(), record.value());
    }
}