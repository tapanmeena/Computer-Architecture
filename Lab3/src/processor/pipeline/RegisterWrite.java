package processor.pipeline;

import generic.Simulator;
import processor.Processor;

public class RegisterWrite {
	Processor containingProcessor;
	MA_RW_LatchType MA_RW_Latch;
	IF_EnableLatchType IF_EnableLatch;
	
	public RegisterWrite(Processor containingProcessor, MA_RW_LatchType mA_RW_Latch, IF_EnableLatchType iF_EnableLatch)
	{
		this.containingProcessor = containingProcessor;
		this.MA_RW_Latch = mA_RW_Latch;
		this.IF_EnableLatch = iF_EnableLatch;
	}
	
	public void performRW()
	{
		if(MA_RW_Latch.isRW_enable())
		{
			int aluResult = MA_RW_Latch.getALUresult();
			int ldResult = MA_RW_Latch.getldResult();
			int pc = MA_RW_Latch.getProgramCounter();
			String opcode = MA_RW_Latch.getOpcode();
			int rd = MA_RW_Latch.getRD();
			switch(opcode){
				case "10110":
//					System.out.println("registerNumber" + rd);
					containingProcessor.getRegisterFile().setValue(rd,ldResult);
					break;
				case "10111"://store
					break;
				case "11101":
					Simulator.setSimulationComplete(true);
					break;
				case "11000":
				case "11001":
				case "11010":
				case "11011":
				case "11100":
					break;
				default:
//					System.out.println("registerNumber "+rd+"   ==="+aluResult );
					containingProcessor.getRegisterFile().setValue(rd,aluResult);
					break;
			}

			MA_RW_Latch.setRW_enable(false);
			IF_EnableLatch.setIF_enable(true);
		}
	}

}
