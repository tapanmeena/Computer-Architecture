package generic;

import java.util.Comparator;
import java.util.PriorityQueue;
import processor.Clock;

public class EventQueue {
	
	PriorityQueue<Event> queue;
	
	public EventQueue()
	{
		queue = new PriorityQueue<Event>(new EventComparator());
	}
	
	public void addEvent(Event event)
	{
		queue.add(event);
	}
	public void removeElement(){
		queue.poll();
	}

	public void processEvents()
	{
		System.out.println("---> Event Queue Processing Start <---");
		System.out.println("Top ELement in Priority Queue "+queue.peek());
		System.out.println("---printing queue size---"+ queue.size());
		if(queue.size()!=0){
		System.out.println("---printing queue peek time---"+ queue.peek().getEventTime()+" current clock time "+Clock.getCurrentTime());
}		while(queue.isEmpty() == false && queue.peek().getEventTime() <= Clock.getCurrentTime())
		{
			System.out.println("     ---> Queue running <---");
			Event event = queue.poll();
//			System.out.println("printing event"+ event);
			event.getProcessingElement().handleEvent(event);
		}
		System.out.println("    processing End   ");
	}
}

class EventComparator implements Comparator<Event>
{
	@Override
    public int compare(Event x, Event y)
    {
		if(x.getEventTime() < y.getEventTime())
		{
			return -1;
		}
		else if(x.getEventTime() > y.getEventTime())
		{
			return 1;
		}
		else
		{
			return 0;
		}
    }
}
