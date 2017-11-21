package com.blogforum.notebook.service.rabbitmq.consumer;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import com.blogforum.notebook.service.enums.MsgQueueNameEnum;
import com.rabbitmq.client.Channel;

/**
 * 接收sso系统消息处理类
 * @author wwd
 *
 */
public class SsoMsgConsumerListenter implements ChannelAwareMessageListener  {

	private static Logger logger = LoggerFactory.getLogger(SsoMsgConsumerListenter.class);
	
	/**消息对应的处理类*/
	private Map<String, Handler> msgHandler = new HashMap<String, Handler>();
	
	@Override
	public void onMessage(Message message, Channel channel) throws Exception {
		try {
			String queueName = message.getMessageProperties().getConsumerQueue();

			String beanName = MsgQueueNameEnum.getBeanNameByQueueName(queueName);
			if (StringUtils.isEmpty(beanName)) {
				StringBuffer loginfo = new StringBuffer();
				loginfo.append("消息对应的bean不存在不进行处理:beanName=" ).append(beanName)
					.append("消息id").append(message.getMessageProperties().getMessageId())
					.append(",消息内容:").append(message.getBody());
				logger.warn(loginfo.toString());
				channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
				return;
			}
			Handler handler = msgHandler.get(beanName);
			handler.doHandler(message);
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
			//channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
			//channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}


	public void setMsgHandler(Map<String, Handler> msgHandler) {
		this.msgHandler = msgHandler;
	}

	
	
}
