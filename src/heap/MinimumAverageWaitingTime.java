package heap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class MinimumAverageWaitingTime {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        Queue<Customer> customers = new PriorityQueue<>(Comparator.comparing(Customer::getArrivalTime));
        while (length-- > 0) {
            int arrivalTime = scanner.nextInt();
            int processingTime = scanner.nextInt();
            customers.add(new Customer(arrivalTime, processingTime));
        }
        System.out.println(minimumAverageWaitingTime(customers));
    }

    private static long minimumAverageWaitingTime(Queue<Customer> customers) {
        Queue<Customer> processQueue = new PriorityQueue<>(
                Comparator.comparing(Customer::getProcessingTime)
        );
        processQueue.add(new Customer(0, 0));

        int totalCustomers = customers.size();
        int currentTime = 0;
        long waitingTime = 0;

        while (!processQueue.isEmpty()) {
            Customer customer = processQueue.poll();
            currentTime += customer.processingTime ;
            waitingTime += currentTime - customer.arrivalTime;

            while (!customers.isEmpty() && customers.peek().arrivalTime <= currentTime) {
                processQueue.add(customers.poll());
            }

            if (processQueue.isEmpty() && !customers.isEmpty()) {
                currentTime = customers.peek().arrivalTime;
                processQueue.add(new Customer(currentTime, 0));
            }
        }

        return waitingTime / totalCustomers;
    }

    private static class Customer {
        int arrivalTime;
        int processingTime;

        Customer(int arrivalTime, int processingTime) {
            this.arrivalTime = arrivalTime;
            this.processingTime = processingTime;
        }

        public int getArrivalTime() {
            return arrivalTime;
        }

        public int getProcessingTime() {
            return processingTime;
        }

        @Override
        public String toString() {
            return "Customer{" +
                    "arrivalTime=" + arrivalTime +
                    ", processingTime=" + processingTime +
                    '}';
        }
    }
}
