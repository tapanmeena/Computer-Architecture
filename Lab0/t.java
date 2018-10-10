import java.util.*;


class zz{
		public boolean[][] update(boolean[][] a,Double prob){
		prob=prob*100;
		for(int i=0;i<a.length;i++){
			for(int j=0;j<a[0].length;j++){
				Random r=new Random();
				int number=r.nextInt(100);
		//		System.out.println(number);
				if(number<=prob){
					a[i][j]=true;
				}
				else{
					a[i][j]=false;
				}
			}
		}
	/*	for(int i=0;i<a.length;i++){
			for(int j=0;j<a[0].length;j++){
				System.out.println(a[i][j]);
			}
		}*/
		return a;
	}
}
class t{


	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		boolean sa[][];
		sa=new boolean[5][100];
		zz z=new zz();
		Double prob=0.1;
		boolean[][] s=z.update(sa,prob);
		for(int i=0;i<s.length;i++){
			for(int j=0;j<s[0].length;j++){
				System.out.println(s[i][j]);
			}
		}
//		Random b=new Random();
//		boolean r=b.nextDouble(a)==0;
//		System.out.println(r);
		boolean arr[][];
		arr=new boolean[4][5];
		System.out.println(arr.length);
		boolean ad[][];
		ad=arr;
		System.out.println(ad[0].length);
	//	System.out.println(arr[0].length());
	/*while(true){		
		if(new Random().nextDouble()<=0.25){
			System.out.println("Yaaay");
		}
	//	int b=in.newInt();
		Double a=in.nextDouble();
	}*/
}
}