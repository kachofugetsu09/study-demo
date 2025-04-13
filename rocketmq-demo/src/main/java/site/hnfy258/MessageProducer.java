package site.hnfy258;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

    @Autowired
    private DefaultMQProducer producer;

    /**
     * 发送简单消息
     */
    public void sendMessage(String topic, String tags, String content) throws Exception {
        Message message = new Message(topic, tags, content.getBytes());
        producer.send(message);
    }
}