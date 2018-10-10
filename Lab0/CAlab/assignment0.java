/*by Tapan(160010039) and Ritik(160020009)*/
import java.util.*;
class Border{
	boolean border[][]; 
	public Border(int width){
		border=new boolean[width][100]; //create a 2d array of width by 100 dimension
	}
	public boolean[][] create(){ return border;} //returns border to main loop
}
class Sensor{
	boolean z[][];
	public Sensor(boolean[][] a,Double prob){ //constructor for activating all sensors by their prob first time
		prob=prob*100;
		for(int i=0;i<a.length;i++){
			for(int j=0;j<a[0].length;j++){
				Random r=new Random();
				int number=r.nextInt(100);
				if(number<=prob)
					a[i][j]=true;
				else
					a[i][j]=false;			
			}		
		}
		z=a;}
	public boolean[][] update(boolean[][] a,Double prob){ //update sensor ON/OFF status
		prob=prob*100;
		for(int i=0;i<a.length;i++){
			for(int j=0;j<a[0].length;j++){
				Random r=new Random();
				int number=r.nextInt(100);
				if(number<=prob)	 a[i][j]=true;
				else		a[i][j]=false;
							}		}
		return a;	}
	public boolean[][] back(){	return z;	}	
}
class Infiltrator{ //stores Infiltrator(agentJ) current position
	int row,column;
	public Infiltrator(boolean[][] a){
		row=0;
		column=50;	}
	public void up_rowp(int r){	row=r; }
	public void up_columnp(int c){ column=c; }
	public int rowp(){ return row; }
	public int columnp(){ return column; }	}
class assignment{
	public static void main(String args[]){
		Scanner in=new Scanner(System.in);
		int someNumber=0,average=0;
		System.out.print("Enter Width ");
		int width=in.nextInt();
		System.out.print("Enter Probability for Sensor to be ON ");
		Double prob=in.nextDouble();
		Border b=new Border(width);
		boolean[][] a=b.create();
		while(someNumber!=10){
			someNumber++;
			if(prob==1){
				System.out.println("Infiltrator cannot Pass. :(");
				return;
			}
			if(prob==0){
				System.out.println(width*10+10);
				return;
			}
			Sensor s=new Sensor(a,prob);	//activate sensor first time
			a=s.back();
			Infiltrator agentJ=new Infiltrator(a);
			int t=10;
			while(agentJ.rowp()<a.length-1){	//this loop run until infiltrator reaches last line of tile
				a=s.update(a,prob);			//update sensor after every 10 Second
				if(a[agentJ.rowp()][agentJ.columnp()]){
					t+=10;continue;			}
				else{
					if(agentJ.columnp()==0 || agentJ.columnp()==99) {
						agentJ.up_columnp(50);
						continue;}
					if(!a[agentJ.rowp()+1][agentJ.columnp()-1]){	//first three if/else if check for front three positions
						agentJ.up_rowp(agentJ.rowp()+1);
						agentJ.up_columnp(agentJ.columnp()-1);
						t+=10;				}
					else if(!a[agentJ.rowp()+1][agentJ.columnp()]){
						agentJ.up_rowp(agentJ.rowp()+1);
						agentJ.up_columnp(agentJ.columnp());
						t+=10;				}
					else if(!a[agentJ.rowp()+1][agentJ.columnp()+1]){
						agentJ.up_rowp(agentJ.rowp()+1);
						agentJ.up_columnp(agentJ.columnp()+1);
						t+=10;				}		
					}		
				}
			while(true){	//for crossing last tile break when current tile is false
				a=s.update(a,prob);
				if(!a[agentJ.rowp()][agentJ.columnp()]){
					t+=10;
					break;
				}
				else
					t+=10;		}average+=t;
			System.out.print("Time taken ");System.out.println(t);
		}System.out.print("Average Time Taken ");System.out.println(average/10);
	}
}
