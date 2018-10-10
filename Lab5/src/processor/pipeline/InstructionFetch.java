package processor.pipeline;

import processor.Processor;
import generic.Element;
import generic.Event;
import generic.MemoryReadEvent;
import generic.MemoryResponseEvent;
import processor.Clock;
import configuration.Configuration;
import generic.Simulator;
public class InstructionFetch implements Element {
	
	Processor containingProcessor;
	IF_EnableLatchType IF_EnableLatch;
	IF_OF_LatchType IF_OF_Latch;
	EX_IF_LatchType EX_IF_Latch;
	MA_RW_LatchType MA_RW_Latch;
	public InstructionFetch(Processor containingProcessor, IF_EnableLatchType iF_EnableLatch, IF_OF_LatchType iF_OF_Latch, EX_IF_LatchType eX_IF_Latch, MA_RW_LatchType mA_RW_Latch)
	{
		this.containingProcessor = containingProcessor;
		this.IF_EnableLatch = iF_EnableLatch;
		this.IF_OF_Latch = iF_OF_Latch;
		this.EX_IF_Latch = eX_IF_Latch;
		this.MA_RW_Latch = mA_RW_Latch;
	}
	
	public String addBits(String binaryString){
		String result = "";
		int sam = binaryString.length() - 32;
		while(sam<0){
			result+="0";
			sam++;
		}
		result+=binaryString;
		return result;
	}

	public void performIF()
	{
		System.out.println("---> Instruction Stage <---");
		System.out.println("instruction  "+IF_EnableLatch.isIF_enable()+"   "+IF_OF_Latch.getIF_enable());
		if(IF_EnableLatch.isIF_enable() && IF_OF_Latch.getIF_enable())
		{
//new 
			if(IF_EnableLatch.isIF_busy()){
				System.out.println("Currently in IFBusy Stage");
				System.out.println("");
				return;
			}
			else{
				System.out.println("Currently Adding New MemoryReadEvent in Instruction Fetch Stage");
				int currentPC = containingProcessor.getRegisterFile().getProgramCounter();
				Simulator.getEventQueue().addEvent(
					new MemoryReadEvent(
						Clock.getCurrentTime()+Configuration.mainMemoryLatency,
						this,
						containingProcessor.getMainMemory(),
						currentPC));
				System.out.println(currentPC+" current pc counter");
				IF_EnableLatch.setIF_busy(true);
			}
		}
		System.out.println("");
	}

	@Override
	public void handleEvent(Event e){
		System.out.println("handle Event If Stage");
		if(IF_OF_Latch.isOF_busy()){
			e.setEventTime(Clock.getCurrentTime()+1);
			Simulator.getEventQueue().addEvent(e);
		}
		else{
			MemoryResponseEvent event = (MemoryResponseEvent) e;
			IF_OF_Latch.setProgramCounter(event.getPC());
			int newInstruction = event.getValue();
			String inst = Integer.toBinaryString(newInstruction);
			inst = addBits(inst);
			System.out.println("Instruction in IF Stage "+inst);
			IF_OF_Latch.setInstruction(inst);
			containingProcessor.getRegisterFile().setProgramCounter(event.getPC() + 1);
			IF_EnableLatch.setIF_enable(false);
			IF_OF_Latch.setIF_enable(false);
			IF_OF_Latch.setOF_enable(true);
			IF_EnableLatch.setIF_busy(false);
			MA_RW_Latch.setRW_busy(false);
		}
	}
}
