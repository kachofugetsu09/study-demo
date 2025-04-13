package site.hnfy258;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageProducer messageProducer;

    @GetMapping("/send")
    public String sendMessage(@RequestParam String content) throws Exception {
        messageProducer.sendMessage("demo-topic", "demo-tag", content);
        return "消息发送成功: " + content;
    }
}