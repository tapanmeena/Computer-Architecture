package processor.pipeline;

public class OF_EX_LatchType {
	
	boolean OF_enable,canContinue;
	int Op1,Op2,Branch,ProgramCounter,rdEX,rs1,rs2;
	String Opcode,immediate,rD;
	public int getProgramCounter(){
		return ProgramCounter;
	}
	public void setProgramCounter(int pc){
		this.ProgramCounter = pc;
	}
	public OF_EX_LatchType()
	{
		OF_enable = false;
		canContinue = true;
	}

	public boolean isOF_enable() {
		return OF_enable;
	}

	public void setOF_enable(boolean oF_enable) {
		OF_enable = oF_enable;
	}
	public boolean getcanContinue(){
		return canContinue;
	}
	public void setcanContinue(boolean cancontinue){
		this.canContinue = cancontinue;
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
	public int getrdEX(){
		return rdEX;
	}
	public void setrdEX(int rdex){
		this.rdEX = rdex;
	}
	public int getrs1(){
		return rs1;
	}
	public void setrs1(int Rs1){
		this.rs1 = Rs1;
	}
	public int getrs2(){
		return rs2;
	}
	public void setrs2(int Rs2){
		this.rs2 = Rs2;
	}
}