package com.blogforum.notebook.service.enums;

public enum MsgQueueNameEnum {

	SSO_INIT_USER_QUEUE_KEY("sso_init_user_queue_key", "initNoteHandler", "用户注册时创建初始笔记本和笔记");

	/** 消息队列名称 */
	private String	queueName;
	/** 对应的处理bean类的名称 */
	private String	beanName;
	/** 描述 */
	private String	memo;

	private MsgQueueNameEnum(String queueName, String beanName, String memo) {
		this.queueName = queueName;
		this.beanName = beanName;
		this.memo = memo;
	}

	public static String getBeanNameByQueueName(String queueName){
		for (MsgQueueNameEnum msg : values()) {
			if (msg.getQueueName().equals(queueName)) {
				return msg.getBeanName();
			}
		}
		return null;
	}
	
	
	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
