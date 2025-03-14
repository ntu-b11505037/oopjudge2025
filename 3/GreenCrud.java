
public class GreenCrud {

	public static int calPopulation(int initialSize,int days) {
		int a = initialSize;
        int b = initialSize;
        int c = 0;
        
        for (int i = 10; i <= days; i += 5) {
            c = a + b;
            a = b;
            b = c;
        	}
        
	return b;
	}
}
