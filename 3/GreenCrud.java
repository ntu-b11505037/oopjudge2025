
public class GreenCrud {

	public static int calPopulation(int initialSize,int days) {
		int a,b,total=0;
		a=initialSize;
		b=initialSize;
		
		for(int number=days/5;number>1;number--) {
			
			total=a+b;
			a=b;
			b=total;
		}
		return total;
	}
	
}
