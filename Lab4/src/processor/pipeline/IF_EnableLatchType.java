package processor.pipeline;

public class IF_EnableLatchType {
	
	boolean IF_enable,RW_enable;
	
	public IF_EnableLatchType()
	{
		IF_enable = true;
		RW_enable = false;
	}

	public boolean isIF_enable() {
		return IF_enable;
	}

	public void setIF_enable(boolean iF_enable) {
		IF_enable = iF_enable;
	}

	public boolean isRW_enable(){
		return RW_enable;
	}

	public void setRW_enable(boolean rW_enable){
		RW_enable = rW_enable;
	}
}
