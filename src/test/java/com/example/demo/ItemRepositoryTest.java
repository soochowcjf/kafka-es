package com.example.demo;

import com.KafkaESApplication;
import com.alibaba.fastjson.JSONObject;
import com.cjf.es.entity.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KafkaESApplication.class)
public class ItemRepositoryTest {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void testItemKafka2ES() {
        Item item = new Item();
        item.setId("11111111");
        item.setTitle("我的nike鞋");
        item.setCategory("nike");
        item.setCategory("shoe");
        item.setPrice(488D);
        item.setImages("www.nike.shoePic");

        Object o = JSONObject.toJSON(item);
        kafkaTemplate.send("test_topic1", o.toString());
    }

}