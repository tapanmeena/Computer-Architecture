import java.util.*;

class Border{
	boolean border[][];
	public Border(int width){
		border=new boolean[width][100];
		border[0][0]=false;
	}
	public boolean[][] create(){
		return border;
	}
}

class Sensor{
	boolean z[][];
	public Sensor(boolean[][] a,Double prob){
		prob=prob*100;
		for(int i=0;i<a.length;i++){
			for(int j=0;j<a[0].length;j++){
				Random r=new Random();
				int number=r.nextInt(100);
				if(number<=prob){
					a[i][j]=true;
				}
				else{
					a[i][j]=false;
				}
			}
		}
		z=a;
	}

	public boolean[][] update(boolean[][] a,Double prob){
				prob=prob*100;

		for(int i=0;i<a.length;i++){
			for(int j=0;j<a[0].length;j++){
				Random r=new Random();
				int number=r.nextInt(100);
				if(number<=prob){
					a[i][j]=true;
				}
				else{
					a[i][j]=false;
				}
			}
		}
		return a;
	}
	public boolean[][] back(){
		return z;
	}
}

class Infiltrator{
	int row,column;//,row_max,column_max;
	public Infiltrator(boolean[][] a){
	//	column_max=100;
	//	row_max=a[0].length;
//		System.out.println(a[0].length);
//int j=0;
	/*	for(int i=0;i<100;i++){
	//	System.out.println(j);
//j++;
			if(!a[0][i]){
				if(i!=0){
					row=0;
					column=i;
					System.out.println(column);
					break;
				}
			}
		}*/
		row=0;
		column=98;
	}

	public void up_rowp(int r){
		row=r;
	}
	public void up_columnp(int c){
		column=c;
	}
	public int rowp(){
		return row;
	}
	public int columnp(){
		return column;
	}
}

class assignment{
	public static void main(String args[]){
		Scanner in=new Scanner(System.in);
		System.out.print("Enter Width ");
		int width=in.nextInt();
		System.out.print("Enter Probability ");
		Double prob=in.nextDouble();
		if(prob==1){
			System.out.println("Infiltrator cannot Pass. :(");
			return;
		}
//		System.out.println(i);
		Border b=new Border(width);
		boolean[][] a=b.create();
		Sensor s=new Sensor(a,prob);
		a=s.back();
	//	boolean[][] zzz=a;
		Infiltrator agentJ=new Infiltrator(a);
		int t=10;
		while(agentJ.rowp()<a.length-1){
			a=s.update(a,prob);
	//		if(zzz==a){
	//			System.out.println("yaaay");
	//		}
	//		System.out.println(t);
			if(!a[agentJ.rowp()][agentJ.columnp()]){
				t+=10;
				continue;
			}
			else{
	/*	System.out.println("row ");
		System.out.println(agentJ.rowp()+1);
		System.out.println("column ");
		System.out.println(agentJ.columnp()-1);*/

				if(!a[agentJ.rowp()+1][agentJ.columnp()-1]){
					agentJ.up_rowp(agentJ.rowp()+1);
					agentJ.up_columnp(agentJ.columnp()-1);
					t+=10;
				}
				else if(!a[agentJ.rowp()+1][agentJ.columnp()]){
					agentJ.up_rowp(agentJ.rowp()+1);
					agentJ.up_columnp(agentJ.columnp());
					t+=10;
				}
				else if(!a[agentJ.rowp()+1][agentJ.columnp()+1]){
					agentJ.up_rowp(agentJ.rowp()+1);
					agentJ.up_columnp(agentJ.columnp()+1);
					t+=10;
				}
				else if(a[agentJ.rowp()][agentJ.columnp()-1]){
					agentJ.up_rowp(agentJ.rowp());
					agentJ.up_columnp(agentJ.columnp()-1);
					t+=10;
				}
				else if(a[agentJ.rowp()][agentJ.columnp()+1]){
					agentJ.up_rowp(agentJ.rowp());
					agentJ.up_columnp(agentJ.columnp()+1);
					t+=10;
				}
			}
		}
		while(true){
			a=s.update(a,prob);
			if(!a[agentJ.rowp()][agentJ.columnp()]){
				t+=10;
				break;
			}
			else{
				t+=10;
			}
		}
		System.out.println(t);
//		System.out.println(a[0][0]);
//		for(int i=0;)
	}
}