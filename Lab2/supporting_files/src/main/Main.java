package main;
import generic.Misc;
import generic.Simulator;

public class Main {

	public static void main(String[] args) throws Exception{
		if(args.length != 2)
		{
			Misc.printErrorAndExit("usage: java -jar <path-to-jar-file> <path-to-assembly-program> <path-to-object-file>\n");
		}		
		
		Simulator.setupSimulation(args[0],args[1]);
		Simulator.assemble(args[1]);
	}

}
