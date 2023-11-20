import java.util.*;

public class FirstFit
{
	static void firstFit(int BS[], int m, int PS[], int n, int RBS[])
	{
		int allocate[] = new int[n];
		for(int i=0; i<n; i++)
			allocate[i] = -1;

		for(int i=0; i<n; i++)
		{
			for(int j=0; j<m; j++)
			{
				if(BS[j] >= PS[i])
				{
					allocate[i] = j;
					BS[j] -= PS[i];
					RBS[i] = BS[j];
					break;
				}
			}
		}
		System.out.println("\nProcess No. \tProcess Size \t Block No. \tRemaining Block Size");
		for(int i=0; i<n; i++)
		{
			System.out.print(" " + (i+1) + "\t\t" + PS[i] + "\t\t");
			if(allocate[i] != -1)
				System.out.print((allocate[i] + 1) + "\t\t" + RBS[i]);
			else
				System.out.print("Not Allocated\t" + RBS[i]);
			System.out.println();
		}
	}
	public static void main(String args[])
	{
		int n, m;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter how many number of blocks you want to enter");
		m = sc.nextInt();
		int BS[] = new int[m];
		int RBS[] = new int[m];
		for(int i=0; i<m; i++)
		{
			System.out.print("Enter  Data " + (i+1) + " : ");
			BS[i] = sc.nextInt();
		}
		System.out.println("Enter how many number of process you want to enter : ");
		n = sc.nextInt();
		int PS[] = new int[n];
		for(int i=0; i<n; i++)
		{
			System.out.print("Enter Data " + (i+1) + " : ");
			PS[i] = sc.nextInt();
		}
		firstFit(BS, m, PS, n, RBS);
	}
}