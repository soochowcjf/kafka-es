package com.cjf.kafka.springkafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试kafka生产者
 */
@RestController
@RequestMapping("kafka")
public class TestKafkaProducerController {

    private static final Logger LOG = LoggerFactory.getLogger(TestKafkaProducerController.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping("send")
    public String send(String msg) {

        ListenableFuture<SendResult<String, String>> future1 = kafkaTemplate.send("test_topic1", msg);

        ListenableFuture<SendResult<String, String>> future2 = kafkaTemplate.send("test_topic2", msg);

        future1.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            //正常回调
            @Override
            public void onSuccess(SendResult<String, String> result) {
                LOG.info("send data: {} success", result.getProducerRecord().value());
            }

            //异常回调
            @Override
            public void onFailure(Throwable ex) {
                LOG.error("send data excep", ex);
            }

        });

        return "success";
    }

}