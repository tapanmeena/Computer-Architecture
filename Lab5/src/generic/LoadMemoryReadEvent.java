package generic;

public class LoadMemoryReadEvent extends Event {

	int addressToReadFrom,aluResult,rd;
	String opcode;
	
	public LoadMemoryReadEvent(long eventTime, Element requestingElement, Element processingElement, int address, String opcode, int aluResult, int rd) {
		super(eventTime, EventType.LoadMemoryRead, requestingElement, processingElement);
		this.addressToReadFrom = address;
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
	public int getAddressToReadFrom() {
		return addressToReadFrom;
	}

	public void setAddressToReadFrom(int addressToReadFrom) {
		this.addressToReadFrom = addressToReadFrom;
	}
}
