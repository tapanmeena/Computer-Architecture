package processor.pipeline;

import processor.Processor;
import java.io.*;

public class Execute {
	Processor containingProcessor;
	OF_EX_LatchType OF_EX_Latch;
	EX_MA_LatchType EX_MA_Latch;
	EX_IF_LatchType EX_IF_Latch;
	IF_OF_LatchType IF_OF_Latch;
	IF_EnableLatchType IF_EnableLatch;	

	public Execute(Processor containingProcessor, OF_EX_LatchType oF_EX_Latch, EX_MA_LatchType eX_MA_Latch, EX_IF_LatchType eX_IF_Latch, IF_OF_LatchType iF_OF_Latch, IF_EnableLatchType iF_EnableLatch)
	{
		this.containingProcessor = containingProcessor;
		this.OF_EX_Latch = oF_EX_Latch;
		this.EX_MA_Latch = eX_MA_Latch;
		this.EX_IF_Latch = eX_IF_Latch;
		this.IF_OF_Latch = iF_OF_Latch;
		this.IF_EnableLatch = iF_EnableLatch;
	}
	public void performEX()
	{
		int wrongPath = containingProcessor.getPath();
		System.out.println("Execute" +" "+OF_EX_Latch.getcanContinue()+" "+OF_EX_Latch.isOF_enable());
		String rd_io = OF_EX_Latch.getRD();
		int rdEX=103;
		if(OF_EX_Latch.isOF_enable() && OF_EX_Latch.getcanContinue()){
			System.out.println("Execute running");
			int isBranchTaken=0;
			rdEX = Integer.parseInt(rd_io,2);//new
			int pc = OF_EX_Latch.getProgramCounter();
			int op1 = OF_EX_Latch.getOp1();
			int op2 = OF_EX_Latch.getOp2();
			String opcode = OF_EX_Latch.getOpcode();
			String immd = OF_EX_Latch.getImmediate();
			int imm = Integer.parseInt(OF_EX_Latch.getImmediate(),2);
			int rd = Integer.parseInt(OF_EX_Latch.getRD(),2);
			int aluResult=0;
			int fit=0;
			System.out.println(opcode+" "+imm+" "+immd+" "+op1+" "+op2+" "+rd+" "+pc);
			//ALU part Start
			System.out.println("Previous PC "+pc+" "+rd);
			System.out.println("immediate "+ imm);
			System.out.println(containingProcessor.getRegisterFile().getValue(rd));
			switch(opcode){
				case "00000":
					System.out.println("add");
					aluResult = op1 + op2;
					break;
				case "00010":
					System.out.println("sub");
					aluResult = op1 - op2;
					break;
				case "00100":
					System.out.println("mul");
					aluResult = op1 * op2;
					break;
				case "00110":
					System.out.println("div");
					aluResult = op1 / op2;
					int ttt = op1 % op2;
					containingProcessor.getRegisterFile().setValue(31,ttt);
					break;
				case "01000":
					System.out.println("and");
					aluResult = op1 & op2;
					break;
				case "01010":
					System.out.println("or");
					aluResult = op1 | op2;
					break;
				case "01100":
					System.out.println("xor");
					aluResult = op1 ^ op2;
					break;
				case "01110":
					System.out.println("slt");
					if(op1<op2) aluResult=1;
					else aluResult=0;
					break;
/*				case "10000":
					aluResult = op1<<< op2;
					break;
*/				case "10010":
					System.out.println("srl");
					aluResult = op1 >>> op2;
					break;
				case "10100":
					System.out.println("sra");
					aluResult = op1 << op2;
					break;
				//imm part
				case "00001":
					System.out.println("addi");
					aluResult = op1 + imm;
					break;
				case "00011":
					System.out.println("subi");
					aluResult = op1 - imm;
					break;
				case "00101":
					System.out.println("muli");
					aluResult = op1 * imm;
					break;
				case "00111":
					System.out.println("divi");
					aluResult = op1 / imm;
					int tti = op1 % imm;
					System.out.println(tti+"   remoainder");
					containingProcessor.getRegisterFile().setValue(31,tti);
					break;
				case "01001":
					System.out.println("andi");
					aluResult = op1 & imm;
					break;
				case "01011":
					System.out.println("orI");
					aluResult = op1 | imm;
					break;
				case "01101":
					System.out.println("xorI");
					aluResult = op1 ^ imm;
					break;
				case "01111":
					System.out.println("sltI");
					if(op1<imm) aluResult=1;
					else aluResult=0;
					break;
/*				case "10001":
					aluResult = op1  op2;
					break;
*/				case "10011":
					System.out.println("srli");
					aluResult = op1 >>> imm;
					break;
				case "10101":
					System.out.println("sraI");
					aluResult = op1 << imm;
					break;					
				case "11001":
					fit=op1-op2;
					System.out.println(fit+" value of fit");
/*					switch(fit){
						case 0:
//							System.out.println("beq");
							isBranchTaken=1;
							pc=imm+pc;
							containingProcessor.getRegisterFile().setProgramCounter(pc);
							break;
						default:
//						System.out.println("Not BEQ");
							break;
					}
					break;*/
					switch(fit){
						case 0:
							System.out.println("BEQ");
							isBranchTaken=1;
							if(imm>1000){
								String abcde = Integer.toBinaryString(~imm);
								imm = Integer.parseInt(abcde.substring(15,32),2);
								imm++;
								pc=pc-imm;
								containingProcessor.getRegisterFile().setProgramCounter(pc);
								break;
							}
							else{
								pc=pc+imm;
								containingProcessor.getRegisterFile().setProgramCounter(pc);
								break;
							}
						default:
						System.out.println("NOT BEQ");
							break;
						}
					break;
				case "11010":
					fit=op1-op2;
//					System.out.println(fit+" value of fit");
					switch(fit){
						case 0:
							System.out.println("NOT BNE");
							break;
						default:
							System.out.println("bne");
							isBranchTaken=1;
							if(imm>1000){
								String abcde = Integer.toBinaryString(~imm);
								imm = Integer.parseInt(abcde.substring(15,32),2);
								imm++;
								pc=pc-imm;
								containingProcessor.getRegisterFile().setProgramCounter(pc);
								break;
							}
							else{
								pc=pc+imm;
								containingProcessor.getRegisterFile().setProgramCounter(pc);
								break;
							}
						}
					break;
				case "11011":
/*					if(op1<op2){
//						System.out.println("blt");
						isBranchTaken=1;
						pc=imm+pc;
						containingProcessor.getRegisterFile().setProgramCounter(pc);
						break;
					}
					else{
//						System.out.println("NOT BLT");
						break;
					}*/
					if(op1<op2){
						isBranchTaken=1;
						if(imm>1000){
						System.out.println("BLT_IF");
						String abcd = Integer.toBinaryString(~imm);
//						System.out.println(abc);
						imm = Integer.parseInt(abcd.substring(15,32),2);
						imm++;
//						System.out.println(imm);
						pc = pc - imm;
						containingProcessor.getRegisterFile().setProgramCounter(pc);
						break;
						}
						else{
						System.out.println("BLT");
						pc=imm+pc;
						containingProcessor.getRegisterFile().setProgramCounter(pc);
						break;	
						}
					}
					else{
						System.out.println("NOT BLT");
						break;
					}
				case "11100":
					if(op1>op2){
						isBranchTaken=1;
						if(imm>1000){
						System.out.println("BGT_IF");
						String abc = Integer.toBinaryString(~imm);
//						System.out.println(abc);
						imm = Integer.parseInt(abc.substring(15,32),2);
						imm++;
//						System.out.println(imm);
						pc = pc - imm;
						containingProcessor.getRegisterFile().setProgramCounter(pc);
						break;
						}
						else{
						System.out.println("BGT");
						pc=imm+pc;
						containingProcessor.getRegisterFile().setProgramCounter(pc);
						break;	
						}
					}
					else{
						System.out.println("NOT BGT");
						break;
					}
				case "11000":
					isBranchTaken=1;
					if(imm>1000){
						System.out.println("jmp_if");
						String mno = Integer.toBinaryString(~imm);
//						System.out.println(mno);
						imm = Integer.parseInt(mno.substring(15,32),2);
						imm++;
//						System.out.println(imm);
						pc = pc-imm;
						containingProcessor.getRegisterFile().setProgramCounter(pc);
						break;
					}
					else{
//					System.out.println(imm+" tapan");
						System.out.println("jmp");
						pc = pc-imm;
						containingProcessor.getRegisterFile().setProgramCounter(pc);
						break;
					}
				default:
					break;
				}//switch
			System.out.println("aluResult "+aluResult);
			System.out.println("ProgramCounter "+pc);

			EX_MA_Latch.setALUresult(aluResult);
			EX_MA_Latch.setOp2(op2);
			EX_MA_Latch.setOp1(op1);
			EX_MA_Latch.setOpcode(opcode);
			EX_MA_Latch.setImmediate(immd);
			EX_MA_Latch.setProgramCounter(pc);
			EX_MA_Latch.setRD(rd);

			switch(isBranchTaken){
				case 1:
					IF_OF_Latch.setOF_enable(false);
					IF_OF_Latch.setIF_enable(true);
					IF_EnableLatch.setIF_enable(true);
					System.out.println("CONTROL HAZARD FOUND!!!!");
					wrongPath++;
					containingProcessor.setPath(wrongPath);
					break;
				default:
					System.out.println("NOT FOUND :)");
					break;
			}
			OF_EX_Latch.setrdEX(rdEX);
			OF_EX_Latch.setOF_enable(false);
			if(opcode == "11001" ||opcode == "11010" || opcode == "11011" || opcode == "11100" || opcode =="11000")
				EX_IF_Latch.setIF_enable(true);
			else
				EX_MA_Latch.setEX_enable(true);
		}
		else{
			System.out.println("Execute not Fucking running");
			EX_MA_Latch.setRD(rdEX);
			OF_EX_Latch.setrdEX(rdEX);
		}
	}
}
