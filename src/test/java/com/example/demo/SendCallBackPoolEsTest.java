//package com.example.demo;
//
//import ch.qos.logback.core.joran.conditional.ElseAction;
//import com.KafkaESApplication;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.raiyi.dw.data.etl.kafka.KafkaMsg;
//import com.raiyi.dw.data.etl.kafka.sms.SmsSendData;
//import com.raiyi.es.entity.SendCallBackPoolES;
//import org.apache.commons.lang.StringUtils;
//import org.elasticsearch.action.index.IndexRequest;
//import org.elasticsearch.action.update.UpdateRequest;
//import org.elasticsearch.common.xcontent.XContentFactory;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
//import org.springframework.data.elasticsearch.core.query.DeleteQuery;
//import org.springframework.data.elasticsearch.core.query.IndexQuery;
//import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
//import org.springframework.data.elasticsearch.core.query.UpdateQuery;
//import org.springframework.data.elasticsearch.core.query.UpdateQueryBuilder;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.text.SimpleDateFormat;
//
///**
// * @author chenjinfeng
// * @create 14:07 2019/5/17
// * @desc
// **/
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = KafkaESApplication.class)
//public class SendCallBackPoolEsTest {
//
//    private static final String DATE_STRING_FORMAT_DAY4 = "yyyyMM";
//
//    private static final String str = "{\n" +
//            "    \"msgType\": 310,\n" +
//            "    \"submitTime\": 1557834459446,\n" +
//            "    \"validTo\": 1560426459446,\n" +
//            "    \"data\": {\n" +
//            "        \"userId\": \"\",\n" +
//            "        \"mobile\": \"15850061614\",\n" +
//            "        \"operators\": 1,\n" +
//            "        \"provinceCode\": \"320000\",\n" +
//            "        \"cityCode\": \"320500\",\n" +
//            "        \"consumerId\": 1,\n" +
//            "        \"msgCount\": 1,\n" +
//            "        \"reqMsgBody\": \"为保证您本月流量充足，推荐您回1开通每天2.6元每月10GB全国流量包月包（80元/月），流量不清零，回TD拒收\",\n" +
//            "        \"sendMsgBody\": \"\",\n" +
//            "        \"acceptType\": 0,\n" +
//            "        \"sendCode\": \"0000\",\n" +
//            "        \"msgId\": \"1128265603254194176\",\n" +
//            "        \"userMsgId\": \"\",\n" +
//            "        \"channelMsgId\": \"0514194738001814048409\",\n" +
//            "        \"channelId\": 191,\n" +
//            "        \"smsCode\": \"DELIVRD\",\n" +
//            "        \"smsStatus\": \"DELIVRD\",\n" +
//            "        \"accTime\": 1557834456000,\n" +
//            "        \"sendTime\": 1557834458000,\n" +
//            "        \"notifyTime\": 1557834420000,\n" +
//            "        \"smsType\": 3,\n" +
//            "        \"source\": 10,\n" +
//            "        \"id\": 1128265605267324900,\n" +
//            "        \"sequence_id\": \"0514194738001814048409\",\n" +
//            "        \"bizCode\": \"1\",\n" +
//            "        \"bizSource\": \"RXY\"\n" +
//            "    },\n" +
//            "    \"dataTime\": 1557834456000,\n" +
//            "    \"ishbt\": 0\n" +
//            "}";
//
//    @Autowired
//    private ElasticsearchTemplate elasticsearchTemplate;
//
//    @Test
//    public void testInseartSendCallBackPool() {
//
//        String data = JSONObject.parseObject(str).getString("data");
//        SmsSendData smsSendData = JSONObject.parseObject(data, SmsSendData.class);
//        System.out.println(smsSendData);
//
//        IndexQuery indexQuery = new IndexQuery();
////        System.out.println(new SimpleDateFormat(DATE_STRING_FORMAT_DAY4).format(smsSendData.getAccTime()));
//        //设置索引
//        String index = "sms_send_history_" + new SimpleDateFormat(DATE_STRING_FORMAT_DAY4).format(smsSendData.getAccTime());
//        indexQuery.setIndexName(index);
//        //设置type
//        indexQuery.setType("sms_send_history");
//        indexQuery.setId(String.valueOf(smsSendData.getId()));
//
//        SendCallBackPoolES sendCallBackPoolES = getSendCallBackPoolES(smsSendData);
////        indexQuery.setObject(sendCallBackPoolES);
//        indexQuery.setSource(JSONObject.toJSONString(sendCallBackPoolES));
//        elasticsearchTemplate.index(indexQuery);
////        elasticsearchTemplate.refresh(index);
//    }
//
//    private SendCallBackPoolES getSendCallBackPoolES(SmsSendData smsSendData) {
//        SendCallBackPoolES sendCallBackPoolES = new SendCallBackPoolES();
//        sendCallBackPoolES.setId(smsSendData.getId());
//        if (StringUtils.isNotBlank(smsSendData.getErrorMsg())) {
//            sendCallBackPoolES.setErrormessage(smsSendData.getErrorMsg());
//        }
////        sendCallBackPoolES.setQueuename();
////        sendCallBackPoolES.setStartdeliveryid();
//        if (StringUtils.isNotBlank(smsSendData.getCityCode())) {
//            sendCallBackPoolES.setCity(smsSendData.getCityCode());
//        }
////        sendCallBackPoolES.setNotifycallnum();
//        if (null != smsSendData.getNotifyTime()) {
//            sendCallBackPoolES.setNotifytime(smsSendData.getNotifyTime());
//        }
//        if (StringUtils.isNotBlank(smsSendData.getBulkMsgId())) {
//            sendCallBackPoolES.setBmsgid(smsSendData.getBulkMsgId());
//        }
////        sendCallBackPoolES.setPid();
//        if (null != smsSendData.getCityId()) {
//            sendCallBackPoolES.setCityId(smsSendData.getCityId());
//        }
//        if (null != smsSendData.getConsumerId()) {
//            sendCallBackPoolES.setUserid(smsSendData.getConsumerId());
//        }
//        if (StringUtils.isNotBlank(smsSendData.getSendCode())) {
//            sendCallBackPoolES.setSendcode(smsSendData.getSendCode());
//        }
//        if (StringUtils.isNotBlank(smsSendData.getChannelMsgId())) {
//            sendCallBackPoolES.setChannelmsgid(smsSendData.getChannelMsgId());
//        }
//        if (null != smsSendData.getOperators()) {
//            sendCallBackPoolES.setVendor(Long.valueOf(smsSendData.getOperators()));
//        }
//        if (StringUtils.isNotBlank(smsSendData.getChannelMsgId())) {
//            sendCallBackPoolES.setSequence_id(smsSendData.getChannelMsgId());
//        }
//        if (smsSendData.getMsgCount() != null && smsSendData.getMsgCount() >0) {
//            sendCallBackPoolES.setMsgcount(Long.valueOf(smsSendData.getMsgCount()));
//        }
////        sendCallBackPoolES.setNotifyurl();
//        if (StringUtils.isNotBlank(smsSendData.getSmsStatus())) {
//            sendCallBackPoolES.setSmsstat(smsSendData.getSmsStatus());
//        }
//        if (smsSendData.getAccTime() != null) {
//            sendCallBackPoolES.setAcctime(smsSendData.getAccTime());
//        }
//        if (StringUtils.isNotBlank(smsSendData.getFileId())) {
//            sendCallBackPoolES.setFileid(smsSendData.getFileId());
//        }
//        if (null != smsSendData.getSmsType()) {
//            sendCallBackPoolES.setSmstype(Long.valueOf(smsSendData.getSmsType()));
//        }
//        if (StringUtils.isNotBlank(smsSendData.getUserId())) {
//            sendCallBackPoolES.setEs_uid(smsSendData.getUserId());
//        }
//        if (StringUtils.isNotBlank(smsSendData.getMobile())) {
//            sendCallBackPoolES.setMobile(smsSendData.getMobile());
//        }
//        if (StringUtils.isNotBlank(smsSendData.getMsgId())) {
//            sendCallBackPoolES.setMsgid(smsSendData.getMsgId());
//        }
//        if (null != smsSendData.getProvinceId()) {
//            sendCallBackPoolES.setProvinceId(smsSendData.getProvinceId());
//        }
//        if (null != smsSendData.getAcceptType()) {
//            sendCallBackPoolES.setAccepttype(Long.valueOf(smsSendData.getAcceptType()));
//        }
//        if (StringUtils.isNotBlank(smsSendData.getUserMsgId())) {
//            sendCallBackPoolES.setUsermsgid(smsSendData.getUserMsgId());
//        }
//        if (StringUtils.isNotBlank(smsSendData.getSendMsgBody())) {
//            sendCallBackPoolES.setSendsmsbody(smsSendData.getSendMsgBody());
//        }
////        sendCallBackPoolES.setSender();
//        if (StringUtils.isNotBlank(smsSendData.getSmsCode())) {
//            sendCallBackPoolES.setSmscode(smsSendData.getSmsCode());
//        }
//        if (StringUtils.isNotBlank(smsSendData.getUserBulkMsgId())) {
//            sendCallBackPoolES.setUserbulkmsgid(smsSendData.getUserBulkMsgId());
//        }
//        if (null != smsSendData.getSendTime()) {
//            sendCallBackPoolES.setSendtime(smsSendData.getSendTime());
//        }
//        if (StringUtils.isNotBlank(smsSendData.getReqMsgBody())) {
//            sendCallBackPoolES.setMsgbody(smsSendData.getReqMsgBody());
//        }
//        if (null != smsSendData.getChannelId()) {
//            sendCallBackPoolES.setChannelid(smsSendData.getChannelId());
//        }
//        if (null != smsSendData.getMsg_success_count() && smsSendData.getMsg_success_count() > 0) {
//            sendCallBackPoolES.setMsg_success_count(Long.valueOf(smsSendData.getMsg_success_count()));
//        }
//        if (null != smsSendData.getMsg_fail_count() && smsSendData.getMsg_fail_count() > 0) {
//            sendCallBackPoolES.setMsg_fail_count(Long.valueOf(smsSendData.getMsg_fail_count()));
//        }
//        return sendCallBackPoolES;
//    }
//
//    @Test
//    public void testDeleteQuery() {
////        DeleteQuery deleteQuery = new DeleteQuery();
////
////        deleteQuery.setIndex("sms_send_history_201905");
////        deleteQuery.setType("sms_send_history");
////        deleteQuery.setQuery(QueryBuilders.matchQuery("id","100"));
//        elasticsearchTemplate.delete("sms_send_history_201905","sms_send_history","1128265605267324900");
//    }
//
//    @Test
//    public void testUpdateQuery() {
//
//        IndexRequest indexRequest = new IndexRequest();
//        indexRequest.source("msgbody", "哈哈哈 测试");
//        UpdateQuery updateQuery = new UpdateQueryBuilder()
//                .withIndexName("sms_send_history_201905")
//                .withType("sms_send_history")
//                .withId("1128265605267324900")
//                .withClass(SendCallBackPoolES.class)
//                .withIndexRequest(indexRequest)
//                .build();
//
//        elasticsearchTemplate.update(updateQuery);
//
//    }
//
//    @Test
//    public void testUpSert() {
//        String data = JSONObject.parseObject(str).getString("data");
//        SmsSendData smsSendData = JSONObject.parseObject(data, SmsSendData.class);
//        SendCallBackPoolES sendCallBackPoolES = getSendCallBackPoolES(smsSendData);
//
//        UpdateRequest updateRequest = new UpdateRequest("sms_send_history_201905","sms_send_history","1111111111111");
//        updateRequest.doc("msgbody","测试upsert");
//        updateRequest.upsert(JSONObject.toJSONString(sendCallBackPoolES));
//        updateRequest.retryOnConflict(3);
//
//        UpdateQuery updateQuery = new UpdateQueryBuilder()
//                .withId("1111111111111")
//                .withDoUpsert(true)
//                .withUpdateRequest(updateRequest)
//                .withClass(SendCallBackPoolES.class)
//                .build();
//        elasticsearchTemplate.update(updateQuery);
//    }
//
//}
