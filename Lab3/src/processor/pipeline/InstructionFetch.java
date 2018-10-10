package processor.pipeline;

import processor.Processor;

public class InstructionFetch {
	
	Processor containingProcessor;
	IF_EnableLatchType IF_EnableLatch;
	IF_OF_LatchType IF_OF_Latch;
	EX_IF_LatchType EX_IF_Latch;
	
	public InstructionFetch(Processor containingProcessor, IF_EnableLatchType iF_EnableLatch, IF_OF_LatchType iF_OF_Latch, EX_IF_LatchType eX_IF_Latch)
	{
		this.containingProcessor = containingProcessor;
		this.IF_EnableLatch = iF_EnableLatch;
		this.IF_OF_Latch = iF_OF_Latch;
		this.EX_IF_Latch = eX_IF_Latch;
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
		if(IF_EnableLatch.isIF_enable())
		{
			int currentPC = containingProcessor.getRegisterFile().getProgramCounter();
			int newInstruction = containingProcessor.getMainMemory().getWord(currentPC);
			String inst = Integer.toBinaryString(newInstruction);
			inst = addBits(inst);
			IF_OF_Latch.setInstruction(inst);
	//		System.out.println(inst);
			containingProcessor.getRegisterFile().setProgramCounter(currentPC + 1);
			IF_OF_Latch.setProgramCounter(currentPC);
			IF_EnableLatch.setIF_enable(false);
			IF_OF_Latch.setOF_enable(true);
		}
	}

}
