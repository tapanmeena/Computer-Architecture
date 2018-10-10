package generic;
import java.util.*;
import java.io.*;
import processor.Clock;
import processor.Processor;
import processor.memorysystem.MainMemory;
import generic.Statistics;

public class Simulator {
		
	static Processor processor;
	static boolean simulationComplete;
	
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
		int numberOfInstruction=0;
		while(simulationComplete == false)
		{
			processor.getIFUnit().performIF();
			Clock.incrementClock();
			processor.getOFUnit().performOF();
			Clock.incrementClock();
			processor.getEXUnit().performEX();
			Clock.incrementClock();
			processor.getMAUnit().performMA();
			Clock.incrementClock();
			processor.getRWUnit().performRW();
			Clock.incrementClock();
			numberOfInstruction++;
			numberOfCycles++;
		}
		
		Statistics.setNumberOfInstructions(numberOfInstruction);
		Statistics.setNumberOfCycles(numberOfCycles);
		// TODO
		// set statistics
	}
	
	public static void setSimulationComplete(boolean value)
	{
		simulationComplete = value;
	}
}
