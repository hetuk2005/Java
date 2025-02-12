import java.util.Scanner;

public class CorrectedSJFScheduling {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input the number of processes
        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();

        // Arrays to hold process data
        int[] processIds = new int[n];
        int[] arrivalTimes = new int[n];
        int[] burstTimes = new int[n];
        int[] completionTimes = new int[n];
        int[] turnAroundTimes = new int[n];
        int[] waitingTimes = new int[n];
        boolean[] isCompleted = new boolean[n];

        // Input Arrival Time and Burst Time for each process
        for (int i = 0; i < n; i++) {
            System.out.print("Enter Arrival Time and Burst Time for Process " + (i + 1) + ": ");
            processIds[i] = i + 1;
            arrivalTimes[i] = sc.nextInt();
            burstTimes[i] = sc.nextInt();
        }

        // Scheduling logic
        int currentTime = 0, completed = 0;

        while (completed < n) {
            int shortestIndex = -1;
            int shortestBurst = Integer.MAX_VALUE;

            // Find the shortest job among the arrived processes
            for (int i = 0; i < n; i++) {
                if (!isCompleted[i] && arrivalTimes[i] <= currentTime && burstTimes[i] < shortestBurst) {
                    shortestBurst = burstTimes[i];
                    shortestIndex = i;
                }
            }

            if (shortestIndex == -1) {
                // If no process has arrived yet, move time forward
                currentTime++;
            } else {
                // Process the selected shortest job
                currentTime += burstTimes[shortestIndex];
                completionTimes[shortestIndex] = currentTime;
                turnAroundTimes[shortestIndex] = completionTimes[shortestIndex] - arrivalTimes[shortestIndex];
                waitingTimes[shortestIndex] = turnAroundTimes[shortestIndex] - burstTimes[shortestIndex];
                isCompleted[shortestIndex] = true;
                completed++;
            }
        }

        // Display results
        System.out.println("\nProcess\tArrival\tBurst\tCompletion\tTurn Around\tWaiting");
        double totalTurnAroundTime = 0, totalWaitingTime = 0;

        for (int i = 0; i < n; i++) {
            System.out.printf("%d\t%d\t%d\t%d\t\t%d\t\t%d\n",
                    processIds[i], arrivalTimes[i], burstTimes[i],
                    completionTimes[i], turnAroundTimes[i], waitingTimes[i]);

            totalTurnAroundTime += turnAroundTimes[i];
            totalWaitingTime += waitingTimes[i];
        }

        // Print averages
        System.out.printf("\nAverage Turn Around Time: %.2f\n", totalTurnAroundTime / n);
        System.out.printf("Average Waiting Time: %.2f\n", totalWaitingTime / n);

        sc.close();
    }
}