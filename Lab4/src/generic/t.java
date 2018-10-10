import java.util.*;
import java.io.*;
import java.nio.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.io.File;

public class t{
	public static void main(String[] args) throws IOException {
/*		Path filePath = FileSystems.getDefault().getPath(".","descending.out");
		byte[] allBytes = Files.readAllBytes(filePath);
		for(int i=0;i<allBytes.length;i++){
			System.out.println(allBytes[i]);
		}*/
		/*RandomAccessFile aFile = new RandomAccessFile("descending.out", "rw");
	    FileChannel inChannel = aFile.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(48);

	    int bytesRead = inChannel.read(buf);
	    while(bytesRead!=-1){
	    	System.out.println("Read "+ bytesRead);
	    	buf.flip();

	    	while(buf.hasRemaining()){
	    		System.out.println((char)buf.get());
	    	}

	    	buf.clear();
	    	bytesRead = inChannel.read(buf);
	    }
	    aFile.close();*/
	    String xyz ="1111";
	    String asd="0000";
	    asd=xyz.substring(0,2);
	    System.out.println(asd);
/*	    DataInputStream instr = new DataInputStream(new BufferedInputStream(new FileInputStream("descending.out")));

try{
		System.out.println(instr.readInt());
		System.out.println(instr.readInt());
		System.out.println(instr.readInt());
		System.out.println(instr.readInt());
		System.out.println(instr.readInt());
		System.out.println(instr.readInt());
		System.out.println(instr.readInt());
		System.out.println(instr.readInt());
		System.out.println(instr.readInt());
		System.out.println(instr.readInt());
		System.out.println(instr.readInt());
		System.out.println(instr.readInt());
		System.out.println(instr.readInt());
		System.out.println(instr.readInt());
		System.out.println(instr.readInt());
		System.out.println(instr.readInt());
		System.out.println(instr.readInt());
		System.out.println(instr.readInt());
		System.out.println(instr.readInt());
		System.out.println(instr.readInt());
		System.out.println(instr.readInt());
		System.out.println(instr.readInt());
		System.out.println(instr.readInt());
		System.out.println(instr.readInt());
		System.out.println(instr.readInt());
		System.out.println(instr.readInt());
		System.out.println(instr.readInt());
		System.out.println(instr.readInt());
		System.out.println(instr.readInt());
	if(instr.available()>0)	
		System.out.println(instr.readInt());
	System.out.println(instr.readInt());
	if(instr.available()>0)	
		System.out.println(instr.readInt());
		//if()
		//	System.out.println(instr.readInt());
	}
	catch(Exception e){
		e.printStackTrace();
	}
	finally{
		if(instr!=null)
			instr.close();
	}
*/	
String opcode ="00011";
if(opcode == "00011" || opcode == "11111" || opcode == "12131"){
	System.out.println("tapan");
}
else{
			System.out.println("meena");

}
}
}