package generic;

import java.io.FileInputStream;
import generic.Operand.OperandType;
import genric.Instruction.OperationType;

public class Simulator {
		
	static FileInputStream inputcodeStream = null;

	public static void setupSimulation(String assemblyProgramFile, String objectProgramFile)
	{	
		int firstCodeAddress = ParsedProgram.parseDataSection(assemblyProgramFile);
		ParsedProgram.parseCodeSection(assemblyProgramFile, firstCodeAddress);
		ParsedProgram.printState();
	}

	public static String addBits(String r_value,int numBits){
		String numB="";
		if(!(r_value.length()-numBits>=0)){
			int sam=r_value.length()-numBits;
			while(sam<0){
				numB+="0";
				sam++;
			}
		}
		return numB;
	}

	public static String R3_Type(String opCode,Instruction inst){
		String main_string="";
		String r1_value = Integer.toBinaryString(inst1.getSourceOperand1.getValue());
		String r2_value = Integer.toBinaryString(inst1.getSourceOperand2.getValue());
		String rd_value = Integer.toBinaryString(inst1.getDestinationOperand.getValue());

		// Register 1
		String numB="";
		numB=addBits(r1_value,5);
		main_string=opCode+numB+r1_value;
		//Register 2
		numB="";
		numB=addBits(r2_value,5);
		main_string+=numB+r2_value;
		//Destination Register
		numB="";
		numB=addBits(rd_value,5);
		main_string+=numB+rd_value;
		main_string+="000000000000";
		return main_string;
	}

	public static String R2I_Type(String opCode,Instruction inst){
		String main_string="";
		String r1_value = Integer.toBinaryString(inst.getSourceOperand1.getValue());
		String imm_value = Integer.toBinaryString(inst.getSourceOperand2.getValue());
		String rd_value = Integer.toBinaryString(inst.getDestinationOperand.getValue());
		//Register 1
		String numB="";
		numB=addBits(r1_value,5);
		main_string=opCode+numB+r1_value;
		//Register Destionation
		numB="";
		numB=addBits(rd_value,5);
		main_string+=numB+rd_value;
		//Immediate Destination
		numB="";
		numB=addBits(r2_value,17);
		main_string+=numB+r2_value;
		return main_string;	
	}

	public static String RI_Type(String opCode,Instruction inst){
		int pc=inst.getProgramCounter();
		String main_string=opCode;
		Operand s1 = inst.getSourceOperand1();
		Operand s2 = inst.getSourceOperand2();
		Operand rd = inst.getDestinationOperand();

		if(s1==null){
			if(s2==null){
				if(rd!=null){
					if(rd.compareTo(OperandType.Label)==0){
						main_string+="00000";
						String rd_value = rd.getLabelValue();
						int temp = ParsedProgram.symtab.get(rd_value);
						int imm_value = temp-pc;
						if(imm_value>=0){
							String imm_binary = Integer.toBinaryString(imm_value);
							String numB="";
							numB=addBits(imm_binary,22);
							main_string+=numB+imm_binary;
						}
						else if(imm_value<0){
							String imm_binary = Integer.toBinaryString(imm_value);
							String imm = imm_value.substring(10);
							main_string+=imm;
						}
					}
					else if(rd.compareTo(OperationType.Register)==0){
						int r_value = rd.getValue();
						String r_binary = Integer.toBinaryString(r_value);
						String numB="";
						numB=addBits(r_binary,5);
						main_string+=numB+r_binary;
						main_string+="0000000000000000000000";
					}
				}
				else if(rd==null)
					main_string+="000000000000000000000000000";
			}
		}
		return main_string;
	}

	public static String branch(String opCode,Instruction inst){
		int pc=inst.getProgramCounter();
		String main_string=opCode;
		String r1_value = Integer.toBinaryString(inst.getSourceOperand1.getValue());
		String r2_value = Integer.toBinaryString(inst.getSourceOperand2.getValue());
		String rd_value = Integer.toBinaryString(inst.getDestinationOperand.getLabelValue());
		int temp = ParsedProgram.symtab.get(rd_value);
		//register 1
		String numB = "";
		numB = addBits(r1_value,5);
		main_string+=numB+r1_value;
		//register 2
		numB="";
		numB = addBits(r2_value,5);
		main_string+=numB+r2_value;
		//imm_value
		int imm_value=temp-pc;
		if(imm_value>=0){
			String imm_binary = Integer.toBinaryString(imm_value);
			numB="";
			numB=addBits(imm_binary,17);
			main_string+=numB+imm_binary;
		}
		else if(imm_value<0){
			String imm_binary = Integer.toBinaryString(imm_value);
			String imm = imm_value.substring(10);
			main_string+=imm;
		}
		return main_string;
	}
	
	public static void assemble()
	{
		int addr=ParsedProgram.symtab.get("main");
		int code_length = ParsedProgram.code.size();
		//loop
	for(int i=0;i<code_length;i++){
		String binaryCode="";
		Instruction inst = ParsedProgram.getInstructionAt(addr);
		int pc = inst.getProgramCounter();
		OperationType opType = inst.getOperationType();
		switch (opType.ordinal()) {
			case 0:
				binaryCode="00000";
				binaryCode=R3_Type(binaryCode,inst);
				break;
			case 1:
				binaryCode="00001";
				binaryCode=R2I_Type(binaryCode,inst);
				break;
			case 2:
				binaryCode="00010";
				binaryCode=R3_Type(binaryCode,inst);
				break;
			case 3:
				binaryCode="00011";
				binaryCode=R2I_Type(binaryCode,inst);
				break;
			case 4:
				binaryCode="00100";
				binaryCode=R3_Type(binaryCode,inst);
				break;
			case 5:
				binaryCode="00101";
				binaryCode=R2I_Type(binaryCode,inst);
				break;
			case 6:
				binaryCode="00110";
				binaryCode=R3_Type(binaryCode,inst);
				break;
			case 7:
				binaryCode="00111";
				binaryCode=R2I_Type(binaryCode,inst);
				break;
			case 8:
				binaryCode="01000";
				binaryCode=R3_Type(binaryCode,inst);
				break;
			case 9:
				binaryCode="01001";
				binaryCode=R2I_Type(binaryCode,inst);
				break;
			case 10:
				binaryCode="01010";
				binaryCode=R3_Type(binaryCode,inst);
				break;
			case 11:
				binaryCode="01011";
				binaryCode=R2I_Type(binaryCode,inst);
				break;
			case 12:
				binaryCode="01100";
				binaryCode=R3_Type(binaryCode,inst);
				break;
			case 13:
				binaryCode="01101";
				binaryCode=R2I_Type(binaryCode,inst);
				break;
			case 14:
				binaryCode="01110";
				binaryCode=R3_Type(binaryCode,inst);
				break;
			case 15:
				binaryCode="01111";
				binaryCode=R2I_Type(binaryCode,inst);
				break;
			case 16:
				binaryCode="10000";
				binaryCode=R3_Type(binaryCode,inst);
				break;
			case 17:
				binaryCode="10001";
				binaryCode=R2I_Type(binaryCode,inst);
				break;
			case 18:
				binaryCode="10010";
				binaryCode=R3_Type(binaryCode,inst);
				break;
			case 19:
				binaryCode="10011";
				binaryCode=R2I_Type(binaryCode,inst);
				break;
			case 20:
				binaryCode="10100";
				binaryCode=R3_Type(binaryCode,inst);
				break;
			case 21:
				binaryCode="10101";
				binaryCode=R2I_Type(binaryCode,inst);
				break;
			case 22:
				binaryCode="10110";
				binaryCode=R2I_Type(binaryCode,inst);
				break;	
			case 23:
				binaryCode="10111";
				binaryCode=R2I_Type(binaryCode,inst);
				break;
			case 24:
				binaryCode="11000";
				binaryCode=RI_Type(binaryCode,inst);
				break;
			case 25:
				binaryCode="11001";
				binaryCode=branch(binaryCode,inst);
				break;
			case 26:
				binaryCode="11010";
				binaryCode=branch(binaryCode,inst);
				break;
			case 27:
				binaryCode="11011";
				binaryCode=branch(binaryCode,inst);
				break;
			case 28:
				binaryCode="11100";
				binaryCode=branch(binaryCode,inst);
				break;
			case 29:
				binaryCode="11101";
				binaryCode=RI_Type(binaryCode,inst);
				break;
			default: Misc.printErrorAndExit("unknown Instruction!!");
		}//switch
	addr++;
	}//for loop
	}
}