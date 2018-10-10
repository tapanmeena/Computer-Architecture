package processor.pipeline;

import processor.Processor;
import java.util.*;

public class MemoryAccess {
	Processor containingProcessor;
	EX_MA_LatchType EX_MA_Latch;
	MA_RW_LatchType MA_RW_Latch;
	
	public MemoryAccess(Processor containingProcessor, EX_MA_LatchType eX_MA_Latch, MA_RW_LatchType mA_RW_Latch)
	{
		this.containingProcessor = containingProcessor;
		this.EX_MA_Latch = eX_MA_Latch;
		this.MA_RW_Latch = mA_RW_Latch;
	}
	
	public void performMA()
	{
		if(EX_MA_Latch.isMA_enable()){
			int pc = EX_MA_Latch.getProgramCounter();
			int op1 = EX_MA_Latch.getOp1();
			int op2 = EX_MA_Latch.getOp2();
			String immd = EX_MA_Latch.getImmediate();
			int imm = Integer.parseInt(EX_MA_Latch.getImmediate(),2);
			String opcode = EX_MA_Latch.getOpcode();
			int aluResult = EX_MA_Latch.getALUresult();
			int rd = EX_MA_Latch.getRD();
			int ldResult=0;
			int temp;
			System.out.println("imm "+imm);
			System.out.println("op1 "+op1);
			System.out.println("op2 "+op2);
			System.out.println("aluResult "+aluResult);
			switch(opcode){
				case "10110":
					temp = op1 + imm;
					System.out.println("load " + temp);
					ldResult = containingProcessor.getMainMemory().getWord(temp);
					System.out.println("loadResult "+ldResult);
					break;
				case "10111":
					temp = op2 + imm;
					containingProcessor.getMainMemory().setWord(temp,op1);
					System.out.println("store ");
					break;
				default:
					break;
			}
/*			if(opcode == "10110"){
				int temp = op1 + imm;
				System.out.println("tapan");
				ldResult = containingProcessor.getMainMemory().getWord(temp);
			}
			else if(opcode == "10111"){
				int temp = op2 + imm;
				containingProcessor.getMainMemory().setWord(temp,op1);
			}
*/			MA_RW_Latch.setOp1(op1);
			MA_RW_Latch.setOp2(op2);
			MA_RW_Latch.setImmediate(immd);
			MA_RW_Latch.setOpcode(opcode);
			MA_RW_Latch.setALUresult(aluResult);
			MA_RW_Latch.setldResult(ldResult);
			MA_RW_Latch.setProgramCounter(pc);
			MA_RW_Latch.setRD(rd);

			EX_MA_Latch.setMA_enable(false);
			MA_RW_Latch.setRW_enable(true);			
		}
	}

}
