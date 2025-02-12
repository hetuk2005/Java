import java.util.Scanner;

public class FCFS_Scheduling1 {

    static class Process {
        int id;
        int arrivalTime;
        int burstTime;
        int waitingTime;
        int turnaroundTime;

        // Constructor
        public Process(int id, int arrivalTime, int burstTime) {
            this.id = id;
            this.arrivalTime = arrivalTime;
            this.burstTime = burstTime;
        }
    }

    // Calculate Waiting Time for each process
    public static void calculateWaitingTime(Process[] processes) {
        processes[0].waitingTime = 0; // First process waiting time is 0
        // Calculate waiting time for each subsequent process
        for (int i = 1; i < processes.length; i++) {
            processes[i].waitingTime = processes[i - 1].waitingTime + processes[i - 1].burstTime;
        }
    }

    // Calculate Turnaround Time for each process
    public static void calculateTurnaroundTime(Process[] processes) {
        for (int i = 0; i < processes.length; i++) {
            processes[i].turnaroundTime = processes[i].waitingTime + processes[i].burstTime;
        }
    }

    // Print Results
    public static void printResults(Process[] processes) {
        System.out.println("Process ID | Arrival Time | Burst Time | Waiting Time | Turnaround Time");
        System.out.println("-----------------------------------------------------------------------");
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        for (Process p : processes) {
            System.out.println(p.id + "      | " + p.arrivalTime + "       | " + p.burstTime + "          | " + p.waitingTime + "           | " + p.turnaroundTime);
            totalWaitingTime += p.waitingTime;
            totalTurnaroundTime += p.turnaroundTime;
        }

        double avgWaitingTime = (double) totalWaitingTime / processes.length;
        double avgTurnaroundTime = (double) totalTurnaroundTime / processes.length;

        System.out.println("\nAverage Waiting Time: " + avgWaitingTime);
        System.out.println("Average Turnaround Time: " + avgTurnaroundTime);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter The Number Of Processes: ");
        int n = scanner.nextInt();

        Process[] processes = new Process[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter Arrival Time For Process " + (i + 1) + ": ");
            int arrivalTime = scanner.nextInt();
            System.out.print("Enter Burst Time For Process " + (i + 1) + ": ");
            int burstTime = scanner.nextInt();

            processes[i] = new Process(i + 1, arrivalTime, burstTime);
        }

        // Sort processes by Arrival Time (FCFS assumes that processes are executed in arrival order)
        // Sorting the processes array based on arrival time
        java.util.Arrays.sort(processes, (p1, p2) -> Integer.compare(p1.arrivalTime, p2.arrivalTime));

        calculateWaitingTime(processes);
        calculateTurnaroundTime(processes);
        printResults(processes);

        scanner.close();
    }
}