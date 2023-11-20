import java.util.*;

public class BestFit
{
	static void NextFit(int BS[], int m, int PS[], int n, int RBS[])
	{
		int allocate[] = new int[n], prev = 0;
		for(int i=0; i<n; i++)
			allocate[i] = -1;
		
		for(int i=0; i<n; i++)
		{
			int best = -1;
			for(int j=0; j<n; j++)
			{
				if((BS[j] >= PS[i]) && (best == -1 || BS[best] > BS[j])) 
					best = j;

			}
			if(best != -1)
			{
				allocate[i] = best;
				BS[best] -= PS[i];
				RBS[i] = BS[best];
			}
		}
		System.out.println("\nProcess No.\tProcess Size\tBlock Size\tRemaining Block Size");
		for(int i=0; i<n; i++)
		{
			System.out.print( (i+1) + "\t\t" + PS[i] + "\t\t");
			if(allocate[i] != -1)
				System.out.print((allocate[i]+1) + "\t\t" + RBS[i]);
			else
				System.out.println("Not Allocated" + RBS[i]);

			System.out.println(); 
		}
	}

	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter how many number of blocks you want to enter : ");
		int m = sc.nextInt();
		int BS[] = new int[m];
		int RBS[] = new int[m];
		for(int i=0; i<m; i++)
		{
			System.out.print("Enter Data of " + (i+1) + " : ");
			BS[i] = sc.nextInt();
		}
		System.out.print("Enter how many number of process you want to enter : ");
		int n = sc.nextInt();
		int PS[] = new int[m];
		for(int i=0; i<m; i++)
		{
			System.out.print("Enter Data of " + (i+1) + " : ");
			PS[i] = sc.nextInt();
		}
		NextFit(BS, m, PS, n, RBS);
	}
}
