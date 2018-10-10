package processor.pipeline;

import processor.Processor;
import java.util.*;
import generic.*;
import configuration.Configuration;
import processor.Clock;
public class MemoryAccess implements Element{
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
		System.out.println("---> Memory Access Stage <---");
		System.out.println("MemoryAccess " +" "+EX_MA_Latch.getcanContinue()+" "+EX_MA_Latch.isEX_enable());
		int rdMA = 102;
		if(EX_MA_Latch.isEX_enable() && EX_MA_Latch.getcanContinue()){
			System.out.println("Memory Access running");
			rdMA = EX_MA_Latch.getRD();
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
			switch(opcode){
				case "10110":
					System.out.println("load Instruction");
					temp = op1 + imm;
					if(EX_MA_Latch.isMA_busy()){
						System.out.println("Currently in MA Busy Stage for Load Instruction");

						System.out.println("");
						return;
					}
					else{
						System.out.println("Currently Adding New MemoryReadEvent for Load Instruction");
						Simulator.getEventQueue().addEvent(
							new LoadMemoryReadEvent(
								Clock.getCurrentTime()+Configuration.mainMemoryLatency,
								this,
								containingProcessor.getMainMemory(),
								temp,
								opcode,
								aluResult,
								rd));
						System.out.println("load accessing Memory Location "+ temp);
						System.out.println(this +" from memory stage");
						EX_MA_Latch.setMA_busy(true);
					}
//					ldResult = containingProcessor.getMainMemory().getWord(temp);
//					System.out.println("loadResult "+ldResult);
					break;
				case "10111":
					temp = op2 + imm;
					if(EX_MA_Latch.isMA_busy()){
						System.out.println("Currently in MA Busy Stage for Store Instruction");
						System.out.println(" ");
						return;
					}
					else{
						System.out.println("Currently Adding New MemoryWrite Event for Store Instruction");
						Simulator.getEventQueue().addEvent(
							new MemoryWriteEvent(
								Clock.getCurrentTime()+Configuration.mainMemoryLatency,
								this,
								containingProcessor.getMainMemory(),
								temp,
								op1));
						EX_MA_Latch.setMA_busy(true);
					}
//					containingProcessor.getMainMemory().setWord(temp,op1);
					System.out.println("store ");
					break;
				default:
					System.out.println("Other Instruction");
					break;
			}
//			EX_MA_Latch.setrdMA(rdMA);//new thing
			System.out.println("opcode "+opcode);
			System.out.println("imm "+imm);
			System.out.println("op1 "+op1);
			System.out.println("op2 "+op2);
			System.out.println("aluResult "+aluResult);
			System.out.println("    rd    "+rdMA+" <<<<<<<<<<<<<<<<<<<<<<");

			MA_RW_Latch.setOp1(op1);
			MA_RW_Latch.setOp2(op2);
			MA_RW_Latch.setImmediate(immd);
			MA_RW_Latch.setOpcode(opcode);
			MA_RW_Latch.setALUresult(aluResult);
			MA_RW_Latch.setldResult(ldResult);
			MA_RW_Latch.setProgramCounter(pc);
			EX_MA_Latch.setrdMA(rdMA);
			MA_RW_Latch.setRD(rdMA);

			EX_MA_Latch.setEX_enable(false);
			MA_RW_Latch.setMA_enable(true);			
		}
		else{
			System.out.println("Memory access not Fucking running");
			EX_MA_Latch.setrdMA(rdMA);
			MA_RW_Latch.setRD(rdMA);
		}
	}
	@Override
	public void handleEvent(Event e){
		System.out.println("handling Event in Ma Stage");
		if(e.getEventType().ordinal()== 4){//load response
			System.out.println(" Handling Load ");
			LoadMemoryEvent event = (LoadMemoryEvent) e;
			MA_RW_Latch.setldResult(event.getValue());
			MA_RW_Latch.setALUresult(event.getaluResult());
			MA_RW_Latch.setRD(event.getrd());
			MA_RW_Latch.setOpcode(event.getopcode());
			System.out.println("load result "+event.getValue()+" aluresult "+event.getaluResult() +" rd value "+event.getrd());
		}
		else if(e.getEventType().ordinal()==2){//store response from Memory Response
			System.out.println(" Handling Store");
			MemoryResponseEvent event = (MemoryResponseEvent) e;
			MA_RW_Latch.setldResult(0);
			MA_RW_Latch.setALUresult(0);
			MA_RW_Latch.setRD(0);
			MA_RW_Latch.setOpcode("10111");
		}
		EX_MA_Latch.setMA_busy(false);
		EX_MA_Latch.setEX_enable(false);
		MA_RW_Latch.setMA_enable(true);			
	}
}
