package generic;

import java.io.PrintWriter;

public class Statistics {
	
	// TODO add your statistics here
	static int numberOfInstructions;
	static int numberOfCycles;
	static int numberOfDataHazard;
	static int numberOfWrongPath;

	public static void printStatistics(String statFile)
	{
		try
		{
			PrintWriter writer = new PrintWriter(statFile);
			
//			writer.println("Number of instructions executed = " + numberOfInstructions);
			writer.println("Number of cycles taken = " + (--numberOfCycles));
			writer.println("Number of times OF stage needed to Stall = "+ numberOfDataHazard);			
			writer.println("Number of Times wrong instruction taken due to Branch = "+numberOfWrongPath);
			// TODO add code here to print statistics in the output file
			
			writer.close();
		}
		catch(Exception e)
		{
			Misc.printErrorAndExit(e.getMessage());
		}
	}
	
	// TODO write functions to update statistics
	public static void setNumberOfInstructions(int numberOfInstructions) {
		Statistics.numberOfInstructions = numberOfInstructions;
	}

	public static void setNumberOfCycles(int numberOfCycles) {
		Statistics.numberOfCycles = numberOfCycles;
	}

	public static void setNumberOfDataHazard(int numberOfdatahazard){
		Statistics.numberOfDataHazard = numberOfdatahazard;
	}
	public static void setNumberOfWrongPath(int numberOfwrongpath){
		Statistics.numberOfWrongPath = numberOfwrongpath;
	}	
}
