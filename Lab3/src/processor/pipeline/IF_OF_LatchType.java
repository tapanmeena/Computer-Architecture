package processor.pipeline;

public class IF_OF_LatchType {
	
	boolean OF_enable;
	int PC;
	String instruction;
	
	public IF_OF_LatchType()
	{
		OF_enable = false;
	}

	public boolean isOF_enable() {
		return OF_enable;
	}

	public void setOF_enable(boolean oF_enable) {
		OF_enable = oF_enable;
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
