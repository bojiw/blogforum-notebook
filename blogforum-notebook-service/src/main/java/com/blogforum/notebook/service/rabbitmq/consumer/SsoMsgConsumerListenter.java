package com.blogforum.notebook.service.rabbitmq.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import com.rabbitmq.client.Channel;

public class SsoMsgConsumerListenter implements ChannelAwareMessageListener  {

	@Override
	public void onMessage(Message message, Channel channel) throws Exception {
		System.out.println(message.toString());
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
		//channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
		//channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
	}

}
