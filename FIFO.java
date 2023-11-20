import java.util.*;

public class FIFO
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		int noOfPages, capacity, idx = 0;
		int hit = 0, fault = 0;
		System.out.print("Enter the number of pages you want to enter : ");
		noOfPages = sc.nextInt();
		int pages[] = new int[noOfPages];
		
		for(int i=0; i<noOfPages; i++)
		{
			pages[i] = sc.nextInt();
		}
		System.out.print("Enter the capacity of frame : ");
		capacity = sc.nextInt();
		int frame[] = new int[capacity];
		int table[][] = new int[noOfPages][capacity];

		for(int i=0; i<capacity; i++)
			frame[i] = -1;

			System.out.println("\n------------------------------------------------------------------------");
			for(int i=0; i<noOfPages; i++)
			{
				int search = -1;
				for(int j=0; j<capacity; j++)
				{
					if(frame[j] == pages[i])
					{
						search = j;
						hit++;
						System.out.print("\tH");
						break;
					}
				}
				if(search == -1)
				{
					frame[idx] = pages[i];
					fault++;
					System.out.print("\tF");
					idx++;
					if(idx == capacity)
						idx = 0;
				}
				System.arraycopy(frame, 0, table[i], 0, capacity);
			}
	System.out.println("\n------------------------------------------------------------------------");		
			for(int i=0; i<capacity; i++)
			{
				for(int j=0; j<noOfPages; j++)
					System.out.print("\t" + table[j][i]);
				System.out.println();
			}
	System.out.println("\n------------------------------------------------------------------------");
			float faultRatio = ((float)fault/noOfPages)*100;
			float hitRatio = ((float)hit/noOfPages)*100; 	

			System.out.println("Page Hit : " + hit + "\nPage Fault : " + fault);
			System.out.printf("Hit Ratio:  %.2f \nFault Ratio:%.2f ",hitRatio,faultRatio);	
	}
}