import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Process {
    int pid; // Process ID
    int burstTime; // Burst Time
    int priority; // Priority

    Process(int pid, int burstTime, int priority) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.priority = priority;
    }
}

public class PriorityScheduling {

    public static void priorityScheduling(Process[] processes) {
        int n = processes.length;
        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];

        // Sort processes by priority (lower number = higher priority)
        Arrays.sort(processes, Comparator.comparingInt(p -> p.priority));

        // Calculate waiting time and turnaround time
        turnaroundTime[0] = processes[0].burstTime; // First process turnaround time
        waitingTime[0] = 0; // First process has no waiting time

        for (int i = 1; i < n; i++) {
            // Waiting time is the turnaround time of the previous process
            waitingTime[i] = turnaroundTime[i - 1];
            // Turnaround time
            turnaroundTime[i] = waitingTime[i] + processes[i].burstTime;
        }

        // Calculate average waiting time and turnaround time
        double avgWaitingTime = Arrays.stream(waitingTime).average().orElse(0);
        double avgTurnaroundTime = Arrays.stream(turnaroundTime).average().orElse(0);

        // Print results
        System.out.println("Process\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < n; i++) {
            System.out.printf("P%d:\t%d\t\t%d%n", processes[i].pid, waitingTime[i], turnaroundTime[i]);
        }

        System.out.printf("%nAverage Waiting Time: %.2f%n", avgWaitingTime);
        System.out.printf("Average Turnaround Time: %.2f%n", avgTurnaroundTime);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();

        Process[] processes = new Process[n];

        // Taking user input for each process
        for (int i = 0; i < n; i++) {
            System.out.print("Enter burst time for Process " + i + ": ");
            int burstTime = scanner.nextInt();
            System.out.print("Enter priority for Process " + i + ": ");
            int priority = scanner.nextInt();
            processes[i] = new Process(i, burstTime, priority);
        }

        priorityScheduling(processes);
        scanner.close();
    }
}
