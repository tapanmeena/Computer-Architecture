package processor.pipeline;

import generic.Simulator;
import processor.Processor;

public class RegisterWrite{
	Processor containingProcessor;
	MA_RW_LatchType MA_RW_Latch;
	IF_EnableLatchType IF_EnableLatch;
	IF_OF_LatchType IF_OF_Latch;
	EX_MA_LatchType EX_MA_Latch;
	OF_EX_LatchType OF_EX_Latch;

	public RegisterWrite(Processor containingProcessor, MA_RW_LatchType mA_RW_Latch, IF_EnableLatchType iF_EnableLatch,IF_OF_LatchType iF_OF_Latch,EX_MA_LatchType eX_MA_Latch,OF_EX_LatchType oF_EX_Latch)
	{
		this.containingProcessor = containingProcessor;
		this.MA_RW_Latch = mA_RW_Latch;
		this.IF_EnableLatch = iF_EnableLatch;
		this.IF_OF_Latch = iF_OF_Latch;
		this.EX_MA_Latch = eX_MA_Latch;
		this.OF_EX_Latch = oF_EX_Latch;
	}
	
	public void performRW()
	{
		System.out.println("RegisterWrite "+MA_RW_Latch.isMA_enable()+" "+MA_RW_Latch.getcanContinue());
		int rdRW=101;
		if(MA_RW_Latch.isMA_enable() && MA_RW_Latch.getcanContinue() && !EX_MA_Latch.isMA_busy())
		{

			System.out.println("RegisterWrite running");
			rdRW = MA_RW_Latch.getRD();
			int aluResult = MA_RW_Latch.getALUresult();
			int ldResult = MA_RW_Latch.getldResult();
//			int pc = MA_RW_Latch.getProgramCounter();
			String opcode = MA_RW_Latch.getOpcode();
			int rd = MA_RW_Latch.getRD();
			switch(opcode){
				case "10110":
					System.out.println("registerNumber " + rd+ "  load instruction");
					containingProcessor.getRegisterFile().setValue(rd,ldResult);
//					rdRW=rd;
					break;
				case "10111"://store
					break;
				case "11101":
					System.out.println("===>end end end end end end<=====");
					EX_MA_Latch.setcanContinue(false);
					OF_EX_Latch.setcanContinue(false);
					Simulator.setSimulationComplete(true);
					break;
				case "11000":
				case "11001":
				case "11010":
				case "11011":
				case "11100":
					break;
				default:
					System.out.println("registerNumber "+rd+"   ==="+aluResult );
					containingProcessor.getRegisterFile().setValue(rd,aluResult);
//					rdRW=rd;
					break;
			}
			System.out.println(containingProcessor.getRegisterFile().getContentsAsString());
//			IF_OF_Latch.setOF_enable(true);
			// MA_RW_Latch.setrdRW(rdRW);
//			MA_RW_Latch.setrdRW(rdRW);
			MA_RW_Latch.setMA_enable(false);
			IF_EnableLatch.setIF_enable(true);
			MA_RW_Latch.setRW_busy(false);
//			IF_EnableLatch.setRW_enable(false);
		}
		else{
			System.out.println("RegisterWrite not FUCKING running");
			MA_RW_Latch.setRW_busy(true);
			MA_RW_Latch.setrdRW(rdRW);
		}
	}
}
