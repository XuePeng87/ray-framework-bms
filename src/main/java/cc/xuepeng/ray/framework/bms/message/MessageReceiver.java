package cc.xuepeng.ray.framework.bms.message;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

//@Component
//@RabbitListener(queues = "bucketlogs")
@Slf4j
public class MessageReceiver {

    //@RabbitHandler
    public void process(byte[] message,
                        Channel channel,
                        @Header(AmqpHeaders.DELIVERY_TAG) long tag
    ) throws IOException {
        log.info(new String(message, StandardCharsets.UTF_8));
        channel.basicAck(tag, true);
    }

}
