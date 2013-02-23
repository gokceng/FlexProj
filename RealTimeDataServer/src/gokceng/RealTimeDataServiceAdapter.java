package gokceng;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import flex.messaging.messages.AsyncMessage;
import flex.messaging.messages.Message;
import flex.messaging.services.MessageService;
import flex.messaging.services.ServiceAdapter;

public class RealTimeDataServiceAdapter extends ServiceAdapter
{
	final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
	NumberGenerator numberGenerator;

	public RealTimeDataServiceAdapter()
	{
		System.out.println("Adapter initilized");
	}

	@Override
	public void start()
	{
		if (numberGenerator == null)
		{
			System.out.println("NumberGenerator started");
			numberGenerator = new NumberGenerator(scheduledExecutorService);
			numberGenerator.start();
		}
	}

	@Override
	public void stop()
	{
		System.out.println("Adapter stopped");
		scheduledExecutorService.shutdown();
		numberGenerator = null;
	}

	@Override
	public Object invoke(final Message msg)
	{
		if (msg.getBody().equals("New"))
		{
			System.out.println("Adapter received new");
			return numberGenerator.generateRandomValue();
		}
		else
		{
			final AsyncMessage newMessage = (AsyncMessage) msg;
			final MessageService msgService = (MessageService) getDestination().getService();
			msgService.pushMessageToClients(newMessage, true);
		}
		return null;
	}
}
