package processor.pipeline;

public class IF_OF_LatchType {
	
	boolean OF_enable,IF_enable,isOF_busy;
	int PC;
	String instruction;
	public IF_OF_LatchType()
	{
		OF_enable = false;
		IF_enable = true;
		isOF_busy = false;
	}
	public boolean isOF_busy(){
		return isOF_busy;
	}

	public void setOF_busy(boolean isOF_busy){
		this.isOF_busy = isOF_busy;
	}

	public boolean isOF_enable() {
		return OF_enable;
	}

	public void setOF_enable(boolean oF_enable) {
		OF_enable = oF_enable;
	}
	public boolean getIF_enable(){
		return IF_enable;
	}
	public void setIF_enable(boolean iF_enable){
		this.IF_enable = iF_enable;
	}
	public int getProgramCounter(){
		return PC;
	}
	public void setProgramCounter(int pc){
		this.PC = pc;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
}
