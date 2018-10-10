package processor.pipeline;

import processor.Processor;

public class OperandFetch {
	Processor containingProcessor;
	IF_OF_LatchType IF_OF_Latch;
	OF_EX_LatchType OF_EX_Latch;
	
	public OperandFetch(Processor containingProcessor, IF_OF_LatchType iF_OF_Latch, OF_EX_LatchType oF_EX_Latch)
	{
		this.containingProcessor = containingProcessor;
		this.IF_OF_Latch = iF_OF_Latch;
		this.OF_EX_Latch = oF_EX_Latch;
	}
	
	public void performOF()
	{
		if(IF_OF_Latch.isOF_enable())
		{
			String inst = IF_OF_Latch.getInstruction();
			int currentPC = IF_OF_Latch.getProgramCounter();
//			String inst = Integer.toBinaryString(inst1);
//			inst = addBits(inst);
//			System.out.println(inst+" "+inst.length());
			String opcode = inst.substring(0,5);
	//		System.out.println(opcode);

			String immediate = inst.substring(15,32);
			String branch_dest = inst.substring(10,32);
			int branch = Integer.parseInt(branch_dest,2);
//			System.out.println("bbbbbrrranch  "+branch);
			int rs1 = Integer.parseInt(inst.substring(5,10),2);
//			System.out.println(rs1);
			int op1 = containingProcessor.getRegisterFile().getValue(rs1);
			int op2;
			String rd = "00000";

			switch(opcode){
				case "10111":
					rd = inst.substring(10,15);
					int r_dest_int = Integer.parseInt(rd,2);
					op2 = containingProcessor.getRegisterFile().getValue(r_dest_int);
					break;
				default:
					String rs2 = inst.substring(10,15);
					int tempOp = Integer.parseInt(rs2,2);
					op2 = containingProcessor.getRegisterFile().getValue(tempOp);
					break;
			}
//			System.out.println(opcode);
			switch(opcode){
				case "00000":
				case "00010":
				case "00100":
				case "00110":
				case "01000":
				case "01010":
				case "01100":
				case "01110":
				case "10000":
				case "10010":
				case "10100":
					rd = inst.substring(15,20);
					break;
				case "00001":
				case "00011":
				case "00101":
				case "00111":
				case "01001":
				case "01011":
				case "01101":
				case "01111":
				case "10001":
				case "10011":
				case "10101":
				case "10110":
				case "11001":
				case "11010":
				case "11011":
				case "11100":
					rd = inst.substring(10,15);
					break;
				default:
					break;
			}
			int sm = Integer.parseInt(rd,2);
//			System.out.println("rd "+sm);
//			System.out.println(opcode+" "+immediate+" "+branch+" "+op1+" "+op2+" "+rd+" "+currentPC);
			OF_EX_Latch.setImmediate(immediate);
			OF_EX_Latch.setBranchTarget(branch);
			OF_EX_Latch.setOp1(op1);
			OF_EX_Latch.setOp2(op2);
			OF_EX_Latch.setOpcode(opcode);
			OF_EX_Latch.setRD(rd);
			OF_EX_Latch.setProgramCounter(currentPC);	

			IF_OF_Latch.setOF_enable(false);
			OF_EX_Latch.setEX_enable(true);
		}
	}

}
