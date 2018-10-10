package generic;

public class LoadMemoryEvent extends Event {

	int value,aluResult,rd;
	String opcode;	
	public LoadMemoryEvent(long eventTime, Element requestingElement, Element processingElement, int value, String opcode, int aluResult, int rd) {
		super(eventTime, EventType.LoadResponse, requestingElement, processingElement);
		this.value = value;
		this.aluResult = aluResult;
		this.rd = rd;
		this.opcode = opcode;
	}

	public int getrd() {
		return rd;
	}
	public void setrd(int rd) {
		this.rd = rd;
	}

	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}

	public int getaluResult() {
		return aluResult;
	}
	public void setaluResult(int aluResult) {
		this.aluResult = aluResult;
	}

	public String getopcode(){
		return opcode;
	}
	public void setopcode(String opcode){
		this.opcode = opcode;
	}
}
