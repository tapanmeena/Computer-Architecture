package processor.pipeline;

public class OF_EX_LatchType {
	
	boolean EX_enable;
	int Op1,Op2,Branch,ProgramCounter;
	String Opcode,immediate,rD;
	public int getProgramCounter(){
		return ProgramCounter;
	}
	public void setProgramCounter(int pc){
		this.ProgramCounter = pc;
	}
	public OF_EX_LatchType()
	{
		EX_enable = false;
	}

	public boolean isEX_enable() {
		return EX_enable;
	}

	public void setEX_enable(boolean eX_enable) {
		EX_enable = eX_enable;
	}

	public String getImmediate(){
		return immediate;
	}

	public void setImmediate(String imm){
		this.immediate = imm;
	}

	public int getBranchTarget(){
		return Branch;
	}

	public void setBranchTarget(int branch){
		this.Branch = branch;
	}

	public int getOp1(){
		return Op1;
	}

	public void setOp1(int op1){
		this.Op1 = op1;
	}

	public int getOp2(){
		return Op2;
	}

	public void setOp2(int op2){
		this.Op2 = op2;
	}

	public String getOpcode(){
		return Opcode;
	}

	public void setOpcode(String opcode){
		this.Opcode = opcode;
	}

	public String getRD(){
		return rD;
	}

	public void setRD(String rd){
		this.rD = rd;
	}
}
