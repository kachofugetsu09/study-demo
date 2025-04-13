package site.hnfy258;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RocketMQDemoApplication {

    @Autowired
    private DemoMessageListener messageListener;

    @Autowired
    private RocketMQConfig rocketMQConfig;

    public static void main(String[] args) {
        SpringApplication.run(RocketMQDemoApplication.class, args);
    }

    @Bean
    public DefaultMQPushConsumer mqConsumer() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(rocketMQConfig.getConsumerGroup());
        consumer.setNamesrvAddr(rocketMQConfig.getNameServer());
        consumer.subscribe(rocketMQConfig.getTopic(), "*");
        consumer.registerMessageListener(messageListener);

        // 添加日志
        System.out.println("消费者配置：");
        System.out.println("NameServer: " + rocketMQConfig.getNameServer());
        System.out.println("Consumer Group: " + rocketMQConfig.getConsumerGroup());
        System.out.println("Topic: " + rocketMQConfig.getTopic());

        consumer.start();
        System.out.println("消费者启动成功！");
        return consumer;
    }
}