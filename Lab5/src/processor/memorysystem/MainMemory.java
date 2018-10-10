package processor.memorysystem;

import processor.Clock;
import configuration.Configuration;
import generic.*;

public class MainMemory implements Element {
	int[] memory;
	
	public MainMemory()
	{
		memory = new int[65536];
	}
	
	public int getWord(int address)
	{
		return memory[address];
	}
	
	public void setWord(int address, int value)
	{
		memory[address] = value;
	}
	
	public String getContentsAsString(int startingAddress, int endingAddress)
	{
		if(startingAddress == endingAddress)
			return "";
		
		StringBuilder sb = new StringBuilder();
		sb.append("\nMain Memory Contents:\n\n");
		for(int i = startingAddress; i <= endingAddress; i++)
		{
			sb.append(i + "\t\t: " + memory[i] + "\n");
		}
		sb.append("\n");
		return sb.toString();
	}

@Override
public void handleEvent(Event e){
		System.out.println("Checking Ordinality");
	if(e.getEventType().ordinal() == 1){
		System.out.println("Memory Read Event in Main Memory");
		MemoryReadEvent event = (MemoryReadEvent) e;
		Simulator.getEventQueue().addEvent(
			new MemoryResponseEvent(
				Clock.getCurrentTime(),
				this,
				event.getRequestingElement(),
				getWord(event.getAddressToReadFrom()),
				event.getAddressToReadFrom()
				));
	}
	else if(e.getEventType().ordinal() == 0){
		System.out.println("Execution Complete");
	}
	else if(e.getEventType().ordinal() == 3){
		System.out.println("Memory Write Event in Main Memory");
		MemoryWriteEvent event = (MemoryWriteEvent) e;
		System.out.println(" Address "+event.getAddressToWriteTo()+" Value "+event.getValue());
		setWord(event.getAddressToWriteTo(),event.getValue());
		Simulator.getEventQueue().addEvent(
			new MemoryResponseEvent(
			Clock.getCurrentTime(),
			this,
			event.getRequestingElement(),
			event.getValue(),
			event.getValue()
			));
	}
	else if(e.getEventType().ordinal() == 5){
		System.out.println("Load Memory Read Event");
			LoadMemoryReadEvent event = (LoadMemoryReadEvent) e;
			Simulator.getEventQueue().addEvent(
				new LoadMemoryEvent(
				Clock.getCurrentTime(),
				this,
				event.getRequestingElement(),
				getWord(event.getAddressToReadFrom()),
				event.getopcode(),
				event.getaluResult(),
				event.getrd()
				));
	}
	else{
		System.out.println("--SAYONARA--");
	}
}

}
