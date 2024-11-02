import java.util.Scanner;

class FCFS {
    public static void main(String args[]) {
        int burst_time[], process[], waiting_time[], tat[], i, j, n, total = 0;
        float wait_avg, TAT_avg;
        
        Scanner s = new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        n = s.nextInt();
        
        process = new int[n];
        burst_time = new int[n];
        waiting_time = new int[n];
        tat = new int[n];
        
        System.out.println("\nEnter Burst time:");
        for (i = 0; i < n; i++) {
            System.out.print("Process[" + (i + 1) + "]: ");
            burst_time[i] = s.nextInt();
            process[i] = i + 1; // Process number
        }

        // First process has 0 waiting time
        waiting_time[0] = 0;

        // Calculate waiting time for each process
        for (i = 1; i < n; i++) {
            waiting_time[i] = waiting_time[i - 1] + burst_time[i - 1];
            total += waiting_time[i];
        }

        // Calculating Average Waiting Time
        wait_avg = (float) total / n;
        total = 0;

        System.out.println("\nProcess\tBurst Time\tWaiting Time\tTurnaround Time");
        
        // Calculate Turnaround Time and print process details
        for (i = 0; i < n; i++) {
            tat[i] = burst_time[i] + waiting_time[i];
            total += tat[i]; // Accumulating Turnaround Time
            
            System.out.println("P" + process[i] + "\t\t" + burst_time[i] + "\t\t" 
                + waiting_time[i] + "\t\t" + tat[i]);
        }

        // Calculating Average Turnaround Time
        TAT_avg = (float) total / n;

        System.out.println("\nAverage Waiting Time: " + wait_avg);
        System.out.println("Average Turnaround Time: " + TAT_avg);
        
        s.close();
    }
}
