import java.util.*;

public class NextFit
{
	static void NextFit(int BS[], int m, int PS[], int n, int RBS[])
	{
		int allocate[] = new int[n], prev = 0;
		for(int i=0; i<n; i++)
			allocate[i] = -1;
		
		for(int i=0; i<n; i++)
		{
			for(int j=prev; j<n; j++)
			{
				if(BS[j] >= PS[i])
				{
					allocate[i] = j;
					BS[j] -= PS[i];
					RBS[i] = BS[j];
					prev = j;
					break;
				}
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
