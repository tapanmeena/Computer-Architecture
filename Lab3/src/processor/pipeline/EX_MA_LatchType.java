package processor.pipeline;

public class EX_MA_LatchType {
	
	boolean MA_enable;
	int aluresult,Op2,Op1,ProgramCounter,rd;
	String Opcode,immediate;
	public EX_MA_LatchType()
	{
		MA_enable = false;
	}

	public boolean isMA_enable() {
		return MA_enable;
	}

	public void setMA_enable(boolean mA_enable) {
		MA_enable = mA_enable;
	}
	public int getRD(){
		return rd;
	}
	public void setRD(int rD){
		this.rd = rD;
	}
	public int getProgramCounter(){
		return ProgramCounter;
	}
	public void setProgramCounter(int pc){
		this.ProgramCounter = pc;
	}
	public int getALUresult(){
		return aluresult;
	}
	public void setALUresult(int alures){
		this.aluresult = alures;
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
	public int getOp1(){
		return Op1;
	}
	public void setOp1(int op1){
		this.Op1 = op1;
	}
	public String getImmediate(){
		return immediate;
	}

	public void setImmediate(String imm){
		this.immediate = imm;
	}
}
