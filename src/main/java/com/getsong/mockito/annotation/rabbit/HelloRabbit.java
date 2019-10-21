package com.getsong.mockito.annotation.rabbit;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * TODO: Purpose
 *
 * @author TODO: getso
 * @since 20/10/2019 2:32 PM
 */
@Slf4j
public class HelloRabbit {

  public static void main(String[] args) {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    factory.setUsername("guest");
    factory.setPassword("guest");
    try (Connection connection = factory.newConnection();
        Channel channel = connection.createChannel()) {
      //      channel.queueDeclare("products_queue", false, false, false, null);
      String message = "product details";
      channel.basicPublish("", "products_queue", null, message.getBytes());
      Consumer consumer =
          new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(
                String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                throws IOException {

              String message = new String(body, "UTF-8");
              log.info(message);
            }
          };
      channel.basicConsume("products_queue", true, consumer);
      for (int i = 0; i < 5; i++) {
        channel.basicPublish("", "products_queue", null, message.getBytes());
        Thread.sleep(1000);
      }
    } catch (IOException | TimeoutException | InterruptedException e) {
      e.printStackTrace();
    }
  }
}
