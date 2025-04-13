package site.hnfy258;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RocketMQConfig {

    @Value("${rocketmq.name-server}")
    private String nameServer;

    @Value("${rocketmq.producer.group}")
    private String producerGroup;

    @Value("${rocketmq.consumer.group}")
    private String consumerGroup;

    @Value("${rocketmq.consumer.topic}")
    private String topic;

    public String getNameServer() {
        return nameServer;
    }

    public String getProducerGroup() {
        return producerGroup;
    }

    public String getConsumerGroup() {
        return consumerGroup;
    }

    public String getTopic() {
        return topic;
    }

    @Bean
    public DefaultMQProducer mqProducer() throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer(producerGroup);
        producer.setNamesrvAddr(nameServer);
        producer.start();
        return producer;
    }
}