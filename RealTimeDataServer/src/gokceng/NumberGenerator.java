package gokceng;

import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import flex.messaging.MessageBroker;
import flex.messaging.messages.AsyncMessage;
import flex.messaging.util.UUIDUtils;

public class NumberGenerator extends Thread
{
	private ScheduledExecutorService scheduler;
	private static final Random RANDOM = new Random();

	public NumberGenerator(ScheduledExecutorService scheduler)
	{
		this.scheduler = scheduler;
	}

	@Override
	public void run()
	{
		final String clientId = UUIDUtils.createUUID();
		final MessageBroker msgBroker = MessageBroker.getMessageBroker(null);
		final AsyncMessage msg = new AsyncMessage();
		msg.setDestination("RealTimeDataServicePush");
		msg.setClientId(clientId);
		msg.setMessageId(UUIDUtils.createUUID());
		msg.setBody(generateRandomValue());
		msgBroker.routeMessageToService(msg, null);
		scheduler.schedule(this, 8, TimeUnit.MILLISECONDS);
	}

	public int generateRandomValue()
	{
		return RANDOM.nextInt(256);
	}
}
