import java.util.*;

public class OptimalPageReplacement
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		int noOfPages, capacity, ptr = 0, hit = 0, fault = 0, idx = 0;
		System.out.print("Enter the number of pages you want to enter : ");
		noOfPages = sc.nextInt();
		int Pages[] = new int[noOfPages];
		for(int i=0; i<noOfPages; i++)
		{
			Pages[i] = sc.nextInt();
		} 
		System.out.print("Enter the capacity of frame : ");
		capacity = sc.nextInt();
		int frame[] = new int[capacity];
		int table[][] = new int[noOfPages][capacity];
		for(int i=0; i<capacity; i++)
			frame[i] = -1;

		System.out.println("------------------------------------------------------------------------");
		for(int i=0; i<noOfPages; i++)
		{
			int search = -1;
			for(int j=0; j<capacity; j++)
			{
				if(frame[j] == Pages[i])
				{
					hit++;
					search = j;
					System.out.printf("%4s", "H");
					break;
				}
			}
			if(search == -1)
			{
				if(idx<capacity)
				{											System.out.printf("%4s", "F");
					frame[idx] = Pages[i];
					idx++;
					fault++;
				}
				else
				{
					int index[] = new int[capacity];
					for(int k=0; k<capacity; k++)
						index[k] = -1;
					
					for(int j=i+1; j<noOfPages; j++)
					{
						for(int k=0; k<capacity; k++)
						{
							if(frame[k] == Pages[j] && index[k] == -1)
							{	
								index[k] = j;
								break;
							}
						}
					}
					int max = 0;
					for(int k=0; k<capacity; k++)
					{
						if(index[k] == -1)
						{
							max = k;
							break;
						}
						else if(index[k] > index[max])
							max = k;
					}
					frame[max] = Pages[i];
					fault++;
					System.out.printf("%4s", "F");
				}
			}
			System.arraycopy(frame, 0, table[i], 0, capacity);
		}
	System.out.println();	System.out.println("------------------------------------------------------------------------");
		for(int i=0; i<capacity; i++)
		{
			for(int j=0; j<noOfPages; j++)
				System.out.printf("%4d", table[j][i]);
			System.out.println();
		}
	System.out.println("------------------------------------------------------------------------");
		double hitRatio = ((double)hit/noOfPages) * 100;
		double faultRatio = ((double)fault/noOfPages) * 100;
		System.out.println("Page Fault : " + fault + "\n Page Hit : " + hit);
		System.out.printf("Hit Ratio : %.2f \nFault Ratio : %.2f", hitRatio, faultRatio);
	}
}