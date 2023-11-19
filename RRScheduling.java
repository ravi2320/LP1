import java.util.*;

class Process {
    int PID, AT, BT, RBT, WT, TAT, RT, CT, V, PR;
}

public class RRScheduling {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int total = 0, start = 0, sumBurst = 0;
        Queue<Integer> q = new LinkedList<>();
        float ATAT = 0, AWT = 0;
        System.out.println("*** Round Robin Scheduling ***");
        System.out.print("Enter Number of Processes: ");
        int n = sc.nextInt();
        Process p[] = new Process[n];
        
        for (int i = 0; i < n; i++) {
            p[i] = new Process();
            p[i].PID = i + 1;
            System.out.print("Enter Arrival Time for Process " + (i + 1) + " : ");
            p[i].AT = sc.nextInt();
            System.out.print("Enter Burst Time for Process " + (i + 1) + " : ");
            p[i].BT = sc.nextInt();
            p[i].RBT = p[i].BT;
            p[i].V = 0;
            sumBurst += p[i].BT;
            System.out.println();
        }

        System.out.print("\nEnter time quantum: ");
        int quantum = sc.nextInt();
        Process temp;
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (p[i].AT > p[j].AT) {
                    temp = p[j];
                    p[j] = p[i];
                    p[i] = temp;
                }
            }
        }

        q.add(0);
        int time = p[0].AT;

        while (!q.isEmpty()) {
            Integer I = q.remove();
            int i = I.intValue();

            if (p[i].RBT <= quantum) {
                time += p[i].RBT;
                p[i].RBT = 0;
                p[i].V = 1;
                p[i].CT = time;
                p[i].TAT = p[i].CT - p[i].AT;
                p[i].WT = p[i].TAT - p[i].BT;

                for (int j = 0; j < n; j++) {
                    if (p[j].AT <= time && p[j].V == 0 && !q.contains(j)) {
                        q.add(j);
                    }
                }
            } else {
                time += quantum;
                p[i].RBT -= quantum;

                for (int j = 0; j < n; j++) {
                    if (p[j].AT <= time && p[j].V == 0 && i != j && !q.contains(j)) {
                        q.add(j);
                    }
                }
                q.add(i);
            }
        }

        System.out.println("*** Round Robin Scheduling ***");
        System.out.println("Processor\tArrival Time\tBurst Time\tCompletion Time\tTurn Around Time\tWaiting Time");

        for (int i = 0; i < n; i++) {
            ATAT += p[i].TAT;
            AWT += p[i].WT;
            System.out.println("P" + p[i].PID + "\t\t" + p[i].AT + "ms\t\t" + p[i].BT + "ms\t\t" + p[i].CT + "ms\t\t"
                    + p[i].TAT + "ms\t\t" + p[i].WT + "ms");
        }

        System.out.println("\nAverage turn around time of processor: " + (ATAT / n) + "ms\nAverage waiting time of processor: "
                + (AWT / n) + "ms");
    }
}
