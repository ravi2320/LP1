import java.util.*;

class Process 
{
    int PID, AT, BT, RBT, WT, TAT, RT, CT, V, PR;
}

public class PriorityScheduling
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		int total = 0, start = 0;
		float ATAT = 0, AWT = 0;
		System.out.println("*** First Come First Secheduling ***");
		System.out.print("Enter Number of Prcess : ");
		int n = sc.nextInt();
		Process p[] = new Process[n+1];
		for(int i=0; i<n; i++)
		{
			p[i] = new Process();
			p[i].PID = i+1;
			System.out.print("Enter Arrival Time for Process " + (i+1) + " : ");
			p[i].AT = sc.nextInt();
			System.out.print("Enter Burst Time for Process " + (i+1) + " : ");
			p[i].BT = sc.nextInt();
			System.out.print("Enter Priority for Process " + (i+1) + " : ");
			p[i].PR = sc.nextInt();
			p[i].RBT = p[i].BT;	
			p[i].V = 0;
		}

		Process temp;
		for(int i=0; i<n; i++)
		{
			for(int j=i+1; j<n; j++)
			{
				if(p[i].AT > p[j].AT || (p[j].PR > p[i].PR))
				{
					temp = p[j];
					p[j] = p[i];
					p[i] = temp;
				}
			}
		}
		p[n] = new Process();
		p[n].PR = 999;
		while(total != n)
		{
			int min = n;

			for(int i=0; i<n; i++)
			{
				if(p[i].AT <= start && p[i].V == 0 && p[i].PR < p[min].PR)
					min = i; 
			}
			if(min == n)
				start++;
			else
			{
				p[min].BT--;
				start++;
				if(p[min].BT == 0)
				{
					p[min].CT = start;
					p[min].V = 1;
					p[min].TAT = start - p[min].AT;
					p[min].WT = p[min].TAT - p[min].RBT;
					total++;
				}
			}
		}
		System.out.println("*** First Come First Serve Secheduling ***");
		System.out.println("Processor\tArrival Time\tBrust Time\tCompletion Time\tTurn Around Time\tWaiting Time");
		for(int i=0; i<n; i++)
		{
			ATAT += p[i].TAT;
			AWT += p[i].WT;
			System.out.println("P"+p[i].PID+"\t\t"+p[i].AT+"ms\t\t"+p[i].RBT+"ms\t\t"+p[i].CT+"ms\t\t"+p[i].TAT+"ms\t\t"+p[i].WT+"ms");
		}
		System.out.println("\nAverage turn around time of processor: "+(ATAT/n)+"ms\nAverage waiting time of processor: "+(AWT/n)+"ms");
	}
}