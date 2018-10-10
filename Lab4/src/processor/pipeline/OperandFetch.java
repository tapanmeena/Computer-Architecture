package processor.pipeline;

import processor.Processor;

public class OperandFetch {
	Processor containingProcessor;
	IF_EnableLatchType IF_EnableLatch;
	IF_OF_LatchType IF_OF_Latch;
	OF_EX_LatchType OF_EX_Latch;
	EX_MA_LatchType EX_MA_Latch;
	MA_RW_LatchType MA_RW_Latch;
	RegisterFile rF;
	public OperandFetch(Processor containingProcessor, IF_OF_LatchType iF_OF_Latch, OF_EX_LatchType oF_EX_Latch, EX_MA_LatchType eX_MA_Latch, MA_RW_LatchType mA_RW_Latch,IF_EnableLatchType iF_EnableLatch)
	{
		this.containingProcessor = containingProcessor;
		this.IF_OF_Latch = iF_OF_Latch;
		this.OF_EX_Latch = oF_EX_Latch;
		this.EX_MA_Latch = eX_MA_Latch;
		this.MA_RW_Latch = mA_RW_Latch;
		this.IF_EnableLatch = iF_EnableLatch;
	}
	
	public void performOF()
	{
		System.out.println("Operand" +" "+IF_OF_Latch.isOF_enable());

		if(IF_OF_Latch.isOF_enable())
		{
			int noDH = containingProcessor.getDataHazard();
			System.out.println("---->OperandStage Start<----");
			int rs2=150;
			String inst = IF_OF_Latch.getInstruction();
			int currentPC = IF_OF_Latch.getProgramCounter();
//			String inst = Integer.toBinaryString(inst1);
//			inst = addBits(inst);
			System.out.println("Operand inst "+inst+" "+inst.length());
			String opcode = inst.substring(0,5);
			System.out.println(opcode);

			String immediate = inst.substring(15,32);
			String branch_dest = inst.substring(10,32);
			int branch = Integer.parseInt(branch_dest,2);
			System.out.println("bbbbbrrranch  "+branch);
			int rs1 = Integer.parseInt(inst.substring(5,10),2);
			System.out.println(rs1);
			int op1 = containingProcessor.getRegisterFile().getValue(rs1);
			int op2;
			String rd = "00000";

			switch(opcode){
				case "10111":
					rd = inst.substring(10,15);
					int r_dest_int = Integer.parseInt(rd,2);
					op2 = containingProcessor.getRegisterFile().getValue(r_dest_int);
					rs2 = Integer.parseInt(rd,2);
					break;
				default:
					String rs_2 = inst.substring(10,15);
					int tempOp = Integer.parseInt(rs_2,2);
//					rs2 = tempOp;
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
					rs2 = Integer.parseInt(inst.substring(10,15),2);
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
				case "11000":
					rs2 = 151;
					rd = inst.substring(10,15);
					break;
				case "11001":
				case "11010":
				case "11011":
				case "11100":
					rs2 = Integer.parseInt(inst.substring(10,15),2);					
					rd = inst.substring(10,15);
					break;
				default:
					break;
			}
			System.out.println("rs1  "+rs1+"  rs2  "+rs2);

			int rdEX = OF_EX_Latch.getrdEX();
			int rdMA = EX_MA_Latch.getrdMA();
			int rdRW = MA_RW_Latch.getrdRW();
	//		int rd_temp = Integer.parseInt(rd,2);
//			System.out.println("rd ->"+rd_temp);
			System.out.println("rdEX  "+rdEX+"  rdMA  "+rdMA+"  rdRW  "+rdRW);
			System.out.println(opcode+" "+immediate+" "+branch+" "+op1+" "+op2+" "+rd+" "+currentPC);
			//System.out.println(rF.getContentsAsString());
			// String abcd = containingProcessor.getRegisterFile().getContentsAsString();
			// System.out.println(abcd);
			if(rs1 == rdEX || rs1 == rdMA || rs1 == rdRW || rs2 == rdEX || rs2 == rdMA || rs2 == rdRW ){
				OF_EX_Latch.setcanContinue(false);
//				IF_EnableLatch.setIF_enable(false);
				IF_OF_Latch.setIF_enable(false);
				IF_OF_Latch.setOF_enable(false);
				System.out.println("rd found HAZARD");
				noDH++;
				containingProcessor.setDataHazard(noDH);
			}
			else{
				System.out.println("rd not found HAZARD");				
				OF_EX_Latch.setImmediate(immediate);
				OF_EX_Latch.setBranchTarget(branch);
				OF_EX_Latch.setOp1(op1);
				OF_EX_Latch.setOp2(op2);
				OF_EX_Latch.setOpcode(opcode);
				OF_EX_Latch.setRD(rd);
				OF_EX_Latch.setProgramCounter(currentPC);
				OF_EX_Latch.setrs1(rs1);
				OF_EX_Latch.setrs2(rs2);


				OF_EX_Latch.setcanContinue(true);
				EX_MA_Latch.setcanContinue(true);
				MA_RW_Latch.setcanContinue(true);
				IF_OF_Latch.setIF_enable(true);
				IF_EnableLatch.setIF_enable(true);
				IF_OF_Latch.setOF_enable(false);
				OF_EX_Latch.setOF_enable(true);
				switch(opcode){
					case "11101":
						IF_OF_Latch.setIF_enable(false);
						break;
					default:
						break;
				}
			}
		}
	}

}
