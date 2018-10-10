package generic;
import java.util.*;
import java.io.*;
import processor.Clock;
import processor.Processor;
import processor.memorysystem.MainMemory;
import generic.Statistics;
import processor.pipeline.EX_MA_LatchType;
import processor.pipeline.IF_OF_LatchType;

public class Simulator {
		
	static Processor processor;
	static boolean simulationComplete;
	// static IF_OF_LatchType IF_OF_Latch;
	// static EX_MA_LatchType EX_MA_Latch;

	public static void setupSimulation(String assemblyProgramFile, Processor p) throws FileNotFoundException
	{
		Simulator.processor = p;
		loadProgram(assemblyProgramFile);
		
		simulationComplete = false;
	}
	
	static void loadProgram(String assemblyProgramFile) throws FileNotFoundException
	{
		DataInputStream instr = new DataInputStream(new BufferedInputStream(new FileInputStream(assemblyProgramFile)));
		try{
			int n=instr.readInt();
			int i;
			for(i=0;i<n;i++){
				int temp = instr.readInt();
				processor.getMainMemory().setWord(i,temp);
			}
			int pc =i;
			processor.getRegisterFile().setProgramCounter(pc);

			while(instr.available()>0){
				int temp = instr.readInt();
				processor.getMainMemory().setWord(i,temp);
				i++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		/*
		 * TODO
		 * 1. load the program into memory according to the program layout described
		 *    in the ISA specification
		 * 2. set PC to the address of the first instruction in the main
		 * 3. set the following registers:
		 *     x0 = 0
		 *     x1 = 65535
		 *     x2 = 65535
		 */
	}
	
	public static void simulate()
	{
		int numberOfCycles=0;
		int numberofdataHazard=0;
		int numberofwrongpath=0;
//		int numberOfInstruction=0;
		while(simulationComplete == false)
		{
			processor.getRWUnit().performRW();
			processor.getMAUnit().performMA();
			processor.getEXUnit().performEX();
			processor.getOFUnit().performOF();
			processor.getIFUnit().performIF();
			Clock.incrementClock();
			numberOfCycles++;
		}
		numberofdataHazard = processor.getDataHazard();
		numberofwrongpath = processor.getPath();
//		Statistics.setNumberOfInstructions(numberOfInstruction);
		Statistics.setNumberOfCycles(numberOfCycles);
		Statistics.setNumberOfDataHazard(numberofdataHazard);
		Statistics.setNumberOfWrongPath(numberofwrongpath);
		// TODO
		// set statistics
	}
	
	public static void setSimulationComplete(boolean value)
	{
		simulationComplete = value;
	}
}
