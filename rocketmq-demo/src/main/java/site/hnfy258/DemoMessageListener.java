package site.hnfy258;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class DemoMessageListener implements MessageListenerConcurrently {

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> messages, 
                                                   ConsumeConcurrentlyContext context) {
        for (MessageExt message : messages) {
            String body = new String(message.getBody());
            System.out.printf("收到消息: %s, topic: %s, tags: %s%n", 
                             body, message.getTopic(), message.getTags());
            // 这里可以添加业务处理逻辑
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}