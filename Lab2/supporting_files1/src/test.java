public class test{
	public static void main(String[] args) {
		System.out.println(123);
		String s="0";
		s="000";
		s+="111";
		int numBits=5;
		String numB="";
		String binaryString=Integer.toBinaryString(31);
		if(!(binaryString.length()-numBits>=0))
		{
			int sam=binaryString.length()-numBits;
			while(sam<0){
				numB+="0";
				sam++;
			}
		}
		System.out.println(numB);

		binaryString+=numB;
//		binaryString = binaryString.substring(binaryString.length()-numBits >= 0 ? binaryString.length() - numBits : 0);
		System.out.println(numB);
		numB="";
		numB+=binaryString;
		System.out.println(numB);

		String as=Integer.toBinaryString(2);
		System.out.println(as);
	}
}