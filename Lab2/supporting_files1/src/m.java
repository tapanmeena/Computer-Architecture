package generic;
import generic.Operand.OperandType;
import java.io.*;

public class Simulator {
		
	static FileInputStream inputcodeStream = null;
	static FileOutputStream out = null;
	static DataOutputStream dout = null;
	
	public static void setupSimulation(String assemblyProgramFile, String objectProgramFile) throws Exception{	
		int firstCodeAddress = ParsedProgram.parseDataSection(assemblyProgramFile);
		ParsedProgram.parseCodeSection(assemblyProgramFile, firstCodeAddress);
		ParsedProgram.printState();
		out = new FileOutputStream(objectProgramFile);
		dout = new DataOutputStream(out);
	}
	
	public static String type_R3(String getvalue, Instruction ins){
		String s1= Integer.toBinaryString(ins.sourceOperand1.getValue());
		String s2= Integer.toBinaryString(ins.sourceOperand2.getValue());
		String d= Integer.toBinaryString(ins.destinationOperand.getValue());
		for(int i=0; s1.length()<=4;i++) s1 = "0" + s1;
		getvalue = getvalue + s1;
		for(int i=0; s2.length()<=4;i++) s2 = "0" + s2;
		getvalue = getvalue + s2;
		for(int i=0; d.length()<=4; i++) d = "0"+ d;
		getvalue = getvalue + d;
		getvalue = getvalue + "000000000000";
		return getvalue;
	}

	public static String type_R2I(String getvalue, Instruction ins){
		String s1= Integer.toBinaryString(ins.sourceOperand1.getValue());
		String s2= Integer.toBinaryString(ins.sourceOperand2.getValue());
		String d= Integer.toBinaryString(ins.destinationOperand.getValue());
		for(int i=0; s1.length()<=4;i++) s1 = "0" + s1;
		getvalue = getvalue + s1;
		for(int i=0; d.length()<=4; i++) d = "0"+ d;
		getvalue = getvalue + d;
		for(int i=0; s2.length()<=16;i++) s2 = "0" + s2;
		getvalue = getvalue + s2;
		return getvalue;
	}

	public static String type_RI(String getvalue, Instruction ins){
		String s1= Integer.toBinaryString(ins.sourceOperand1.getValue());
		String s2= Integer.toBinaryString(ins.sourceOperand2.getValue());
		int pc = ins.getProgramCounter();
		int label = ParsedProgram.symtab.get(ins.destinationOperand.getLabelValue());
		int addr_ret = label-pc;
		String c = "";
		for(int i=0; s1.length()<=4;i++) s1 = "0" + s1;
		getvalue = getvalue + s1;
		for(int i=0; s2.length()<=4;i++) s2 = "0" + s2;
		getvalue = getvalue + s2;
		String s= Integer.toBinaryString(addr_ret);
		if(s.length()<=16){
			for(int i=0; s.length()<=16; i++) s = "0" + s;
		}
		else{
			int l = s.length()-1;
			for(int i=l; c.length()<=16;i--) c = s.charAt(i) + c;
			s=c;
		}
		getvalue = getvalue+s;
		return getvalue;
	}

	public static int getOpcode(Instruction ins) throws Exception{
		String getvalue = "";
		int binvalue=0, start=1;
		String s="";
		if(ins.getOperationType() == Instruction.OperationType.add){
			getvalue+="00000";
			getvalue=type_R3(getvalue,ins);
		}
		else if(ins.getOperationType() == Instruction.OperationType.addi){
			getvalue+="00001";
			getvalue=type_R2I(getvalue,ins);
		}
		else if(ins.getOperationType() == Instruction.OperationType.sub){
			getvalue+="00010";
			getvalue=type_R3(getvalue,ins);
		}
		else if(ins.getOperationType() == Instruction.OperationType.subi){
			getvalue+="00011";
			getvalue=type_R2I(getvalue,ins);
		}
		else if(ins.getOperationType() == Instruction.OperationType.mul){
			getvalue+="00100";
			getvalue=type_R3(getvalue,ins);
		}
		else if(ins.getOperationType() == Instruction.OperationType.muli){
			getvalue+="00101";
			getvalue=type_R2I(getvalue,ins);
		}
		else if(ins.getOperationType() == Instruction.OperationType.div){
			getvalue+="00110";
			getvalue=type_R3(getvalue,ins);
		}
		else if(ins.getOperationType() == Instruction.OperationType.divi){
			getvalue+="00111";
			getvalue=type_R2I(getvalue,ins);
		}
		else if(ins.getOperationType() == Instruction.OperationType.and){
			getvalue+="01000";
			getvalue=type_R3(getvalue,ins);
		}
		else if(ins.getOperationType() == Instruction.OperationType.andi){
			getvalue+="01001";
			getvalue=type_R2I(getvalue,ins);
		}
		else if(ins.getOperationType() == Instruction.OperationType.or){
			getvalue+="01010";
			getvalue=type_R3(getvalue,ins);
		}
		else if(ins.getOperationType() == Instruction.OperationType.ori){
			getvalue+="01011";
			getvalue=type_R2I(getvalue,ins);
		}
		else if(ins.getOperationType() == Instruction.OperationType.xor){
			getvalue+="01100";
			getvalue=type_R3(getvalue,ins);
		}
		else if(ins.getOperationType() == Instruction.OperationType.xori){
			getvalue+="01101";
			getvalue=type_R2I(getvalue,ins);
		}
		else if(ins.getOperationType() == Instruction.OperationType.slt){
			getvalue+="01110";
			getvalue=type_R3(getvalue,ins);
		}
		else if(ins.getOperationType() == Instruction.OperationType.slti){
			getvalue+="01111";
			getvalue=type_R2I(getvalue,ins);
		}
		else if(ins.getOperationType() == Instruction.OperationType.sll){
			getvalue+="10000";
			getvalue=type_R3(getvalue,ins);
		}
		else if(ins.getOperationType() == Instruction.OperationType.slli){
			getvalue+="10001";
			getvalue=type_R2I(getvalue,ins);
		}
		else if(ins.getOperationType() == Instruction.OperationType.srl){
			getvalue+="10010";
			getvalue=type_R3(getvalue,ins);
		}
		else if(ins.getOperationType() == Instruction.OperationType.srli){
			getvalue+="10011";
			getvalue=type_R2I(getvalue,ins);
		}
		else if(ins.getOperationType() == Instruction.OperationType.sra){
			getvalue+="10100";
			getvalue=type_R3(getvalue,ins);
		}
		else if(ins.getOperationType() == Instruction.OperationType.srai){
			getvalue+="10101";
			getvalue=type_R2I(getvalue,ins);
		}
		else if(ins.getOperationType() == Instruction.OperationType.load){
			getvalue+="10110";
			getvalue=type_R2I(getvalue,ins);
		}
		else if(ins.getOperationType() == Instruction.OperationType.store){
			getvalue+="10111";
			getvalue=type_R2I(getvalue,ins);
		}
		else if(ins.getOperationType() == Instruction.OperationType.beq){
			getvalue+="11001";
			getvalue=type_RI(getvalue,ins);
		}
		else if(ins.getOperationType() == Instruction.OperationType.bne){
			getvalue+="11010";
			getvalue=type_RI(getvalue,ins);
		}
		else if(ins.getOperationType() == Instruction.OperationType.blt){
			getvalue+="11011";
			getvalue=type_RI(getvalue,ins);
		}
		else if(ins.getOperationType() == Instruction.OperationType.bgt){
			getvalue+="11100";
			getvalue=type_RI(getvalue,ins);
		}
		else if(ins.getOperationType() == Instruction.OperationType.jmp){
			getvalue+="1100000000";
			int pc = ins.getProgramCounter();
			int label= ParsedProgram.symtab.get(ins.destinationOperand.getLabelValue());
			int addr_ret = label-pc;
			String c = "";
			String s1= Integer.toBinaryString(addr_ret);
			if(s1.length()<=21){
				for(int i=0; s1.length()<=21; i++) s1 = "0" + s1;
			}
			else{
				int l = s1.length()-1;
				for(int i=l; c.length()<=21;i--) c = s1.charAt(i) + c;
				s1=c;
			}
			getvalue = getvalue+s1;
		}
		else if(ins.getOperationType() == Instruction.OperationType.end){
			getvalue+="11101";
			for(int i=0;getvalue.length()<32;i++){
				getvalue=getvalue+"0";
			}
		}
		int i=31;
		while(i>=0){
			if(getvalue.charAt(i)=='1') binvalue=binvalue+start;
			start=start*2;
			i--;
		}
		return binvalue;
	}
	
	public static void assemble() throws Exception{
		int header = ParsedProgram.data.size();
		int code_length = ParsedProgram.code.size();
		System.out.println(header);
		dout.writeInt(header);
	
		int i=0;
		while(i<header){
			dout.writeInt(ParsedProgram.data.get(i));
			System.out.println(ParsedProgram.data.get(i));
			i++;
		}
		i=0;
		while(i<code_length){
			int opcode = getOpcode(ParsedProgram.code.get(i));
			System.out.println(opcode);
			dout.writeInt(opcode);
			i++;
		}		
	}
	
}