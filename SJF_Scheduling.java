import java.util.Arrays;
import java.util.Scanner;

public class SJF_Scheduling {

    // Method to calculate and display scheduling details
    public static void sjfScheduling(int[][] processes) {
        int n = processes.length; // Number of processes

        // Arrays to store burst time, waiting time, turnaround time, and completion time
        int[] burstTime = new int[n];
        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];
        int[] completionTime = new int[n];

        // Copy burst times
        for (int i = 0; i < n; i++) {
            burstTime[i] = processes[i][0];
        }

        // Array to track completion status of processes
        boolean[] isCompleted = new boolean[n];
        Arrays.fill(isCompleted, false);

        int currentTime = 0; // Initialize current time
        int completed = 0; // Number of completed processes

        while (completed < n) {
            int idx = -1;
            int minBurstTime = Integer.MAX_VALUE;

            // Find the process with the shortest burst time that is not completed
            for (int i = 0; i < n; i++) {
                if (!isCompleted[i] && burstTime[i] < minBurstTime) {
                    minBurstTime = burstTime[i];
                    idx = i;
                }
            }

            if (idx != -1) {
                // Update times for the selected process
                completionTime[idx] = currentTime + burstTime[idx];
                turnaroundTime[idx] = completionTime[idx]; // Since arrival time is 0
                waitingTime[idx] = turnaroundTime[idx] - burstTime[idx];

                // Mark the process as completed
                isCompleted[idx] = true;
                completed++;

                // Move current time to the end of process execution
                currentTime += burstTime[idx];
            }
        }

        // Calculate average waiting time and turnaround time
        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;
        for (int i = 0; i < n; i++) {
            totalWaitingTime += waitingTime[i];
            totalTurnaroundTime += turnaroundTime[i];
        }

        double avgWaitingTime = totalWaitingTime / n;
        double avgTurnaroundTime = totalTurnaroundTime / n;

        // Print process details
        System.out.println("ProcessID\tBurstTime\tCompletionTime\tWaitingTime\tTurnaroundTime");
        for (int i = 0; i < n; i++) {
            System.out.printf("%d\t\t%d\t\t%d\t\t%d\t\t%d%n",
                               i + 1, processes[i][0],
                               completionTime[i], waitingTime[i], turnaroundTime[i]);
        }

        System.out.printf("%nAverage Waiting Time: %.2f%n", avgWaitingTime);
        System.out.printf("Average Turnaround Time: %.2f%n", avgTurnaroundTime);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input number of processes
        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();

        // Validate number of processes
        if (n <= 0) {
            System.out.println("Number of processes must be greater than 0.");
            return;
        }

        // Create array to hold processes
        int[][] processes = new int[n][1]; // Only one column for burst times

        // Input burst time for each process
        for (int i = 0; i < n; i++) {
            System.out.printf("Enter burst time for process %d: ", i + 1);
            processes[i][0] = scanner.nextInt(); // Burst Time

            // Validate burst time
            if (processes[i][0] <= 0) {
                System.out.println("Burst time must be greater than 0.");
                return;
            }
        }

        // Run the SJF scheduling
        sjfScheduling(processes);

        scanner.close();
    }
}
