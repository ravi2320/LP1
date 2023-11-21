import java.util.*;

class Process
{
	int AT, BT, CT, TAT, WT, RBT, V, PR; 
}

public class PriorityScheduling_Non_Premitive
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter No of Process : ");
		float AWT = 0, ATAT = 0;
		int n = sc.nextInt();
		Process P[] = new Process[n];

		for(int i=0; i<n; i++)
		{
			P[i] = new Process();
			System.out.print("Enter Arrival Time for Process " + (i+1) + " : ");
			P[i].AT = sc.nextInt();
			
			System.out.print("Enter Burst Time for Process " + (i+1) + " : ");
			P[i].BT = sc.nextInt();

			System.out.print("Enter Priority for Process " + (i+1) + " : ");
			P[i].PR = sc.nextInt();
			
			P[i].RBT = P[i].BT;
			P[i].V = 0;
			System.out.println();
		}

		Process temp;
		for(int i=0; i<n-1; i++)
		{
			for(int j=i+1; j<n; j++)
			{
				if((P[i].AT > P[j].AT) || (P[i].AT == P[j].AT && P[i].PR < P[j].PR))
				{
					temp = P[j];
					P[j] = P[i];
					P[i] = temp; 
				}	
			}
		}		
		System.out.println("\n-----------------------------------------------------------------------------------------------------");
		System.out.println("Processor\tArrival Time\tBurst Time\tPriority\tCompletion Time\tTurn Around Time\tWaiting Time");
		System.out.println("\n-----------------------------------------------------------------------------------------------------");
		int start = 0;
		for(int i=0; i<n; i++)
		{
			if(i==0 || P[i].AT > P[i-1].CT)

				P[i].CT = P[i].AT + P[i].BT;
			else
				P[i].CT = P[i-1].CT + P[i].BT;

			P[i].TAT = P[i].CT - P[i].AT;
			P[i].WT = P[i].TAT - P[i].BT;
			ATAT += P[i].TAT;
			AWT += P[i].WT;
			System.out.println("P" + (i+1) + "\t\t" + P[i].AT + "ms\t\t" + P[i].BT + "ms\t\t" + P[i].PR + "\t\t" + P[i].CT + "ms\t\t" + P[i].TAT + "ms\t\t" + P[i].WT + "ms");
		}
		System.out.println("\n-----------------------------------------------------------------------------------------------------");
		System.out.printf("\nAverage Waiting Time : %.2f", (AWT/n));
		System.out.printf("\nAverage Turn Around Time : %.2f", (ATAT/n));
		System.out.println("\n-----------------------------------------------------------------------------------------------------");
		
	}
}