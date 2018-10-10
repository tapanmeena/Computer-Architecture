package processor.pipeline;

public class EX_MA_LatchType {
	
	boolean EX_enable,canContinue,isMA_busy;
	int aluresult,Op2,Op1,ProgramCounter,rd,rdMA;
	String Opcode,immediate;
	public EX_MA_LatchType()
	{
		EX_enable = false;
		canContinue = true;
		isMA_busy = false;
	}
	public boolean isEX_enable() {
		return EX_enable;
	}

	public void setEX_enable(boolean eX_enable) {
		EX_enable = eX_enable;
	}
	public boolean getcanContinue(){
		return canContinue;
	}
	public void setcanContinue(boolean cancontinue){
		this.canContinue = cancontinue;
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
	public int getrdMA(){
		return rdMA;
	}
	public void setrdMA(int rdma){
		this.rdMA = rdma;
	}

	public boolean isMA_busy(){
		return isMA_busy;
	}
	public void setMA_busy(boolean isMA_busy){
		this.isMA_busy = isMA_busy;
	}
}
