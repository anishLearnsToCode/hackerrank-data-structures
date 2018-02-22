import java.util.Scanner;

//Exceptions
class PriorityQueueUnderFlowException extends Exception {
    PriorityQueueUnderFlowException(){
        System.out.println("PriorityQueueUnderFlowException");
    }
}


class Process{
    private int data;
    private int priority;
    public Process next;

    Process(){}
    Process(int priority){
        this.priority = priority;
    }
    Process(int data, int priority){
        this.data = data;
        this.priority = priority;
    }

    public int getPriority(){
        return priority;
    }
    public int getData(){
        return data;
    }
}


class LinkedList{
    private Scanner in = new Scanner(System.in);
    private Process head;
    private int size=0;

    LinkedList(){
        head = new Process();
    }

    public void addElement(){
        addProcess();
    }

    public void addElements(){
        System.out.print("How many data points do you wish to Add : ");
        int numberPoints = in.nextInt();
        while (numberPoints-- > 0){

        }
    }

    private void addProcess(){
        System.out.print("Enter Data : ");
        int data = in.nextInt();
        System.out.print("Enter Priority : ");
        int priority = in.nextInt();
        Process process = new Process(data, priority);
        if(size == 0)
            head = process;
        else head.next = process;

        size++;
    }

    public void addProcess(Process process){
        if(process.getPriority() < head.getPriority()){
            process.next = head;
            head = process;
        }
        Process temp = head;
        while (temp.next != null && process.getPriority() > temp.next.getPriority()){
            temp = temp.next;
        }
        process.next = temp.next;
        temp.next = process;

        size++;
    }

    public void display() throws PriorityQueueUnderFlowException, InterruptedException {
        if(size == 0)
            throw new PriorityQueueUnderFlowException();

        Process process = head;
        while (process != null){
            print(process);
            Thread.sleep(1000);
            process = process.next;
        }
    }

    private void print(Process process){
        System.out.println("( " + process.getData() + " , " + process.getPriority() + " )");
    }

    public Process deQueue(){
        Process process = new Process(head.getData(), head.getPriority());
        head = head.next;
        size--;
        return process;
    }

    public Process peek(){
        return new Process(head.getData(), head.getPriority());
    }

    public int getSize(){
        return size;
    }
}

class PriorityQueue{
    private LinkedList priorityQueue;
    private Scanner in = new Scanner(System.in);

    PriorityQueue(){
        priorityQueue = new LinkedList();
    }

    public void addElement(){
        if(priorityQueue.getSize() == 0){
            priorityQueue.addElement();
        } else {
            Process process = getProcess();
            priorityQueue.addProcess(process);
        }
    }

    public void addElements(){
        System.out.print("Enter the number of processes you wish to Add : ");
        int numberProcesses = in.nextInt();
        while (numberProcesses -- > 0){
            addElement();
        }
    }

    private Process getProcess(){
        System.out.print("Enter Data : ");
        int data = in.nextInt();
        System.out.print("Enter Priority : ");
        int priority = in.nextInt();
        return new Process(data, priority);
    }

    public Process doTask() throws PriorityQueueUnderFlowException{
        if(priorityQueue.getSize() == 0)
            throw new PriorityQueueUnderFlowException();
        return priorityQueue.deQueue();
    }

    public Process currentTask() throws PriorityQueueUnderFlowException{
        if(priorityQueue.getSize() == 0)
            throw new PriorityQueueUnderFlowException();
        return priorityQueue.peek();
    }

    public int numberOfQueuedTasks(){
        return priorityQueue.getSize();
    }

    public void displayTasks() throws PriorityQueueUnderFlowException, InterruptedException{
        priorityQueue.display();
    }
}

public class PriorityQueueImplementation {
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws PriorityQueueUnderFlowException, InterruptedException {
        PriorityQueue priorityQueue = new PriorityQueue();
        int choice;

        do {
            System.out.println("Select A Option :- ");
            System.out.println("1) Add Process");
            System.out.println("2) Add Multiple Processes");
            System.out.println("3) Complete Process");
            System.out.println("4) See Ongoing Process");
            System.out.println("5) Number Of Queued Processes ");
            System.out.println("6) Display Tasks");
            System.out.println("7) Exit");
            choice = in.nextInt();

            switch (choice){
                case 1 :
                    priorityQueue.addElement();
                    break;
                case 2 :
                    priorityQueue.addElements();
                    break;
                case 3 :
                    Process task = priorityQueue.doTask();
                    print(task);
                    break;
                case 4 :
                    task = priorityQueue.currentTask();
                    print(task);
                    break;
                case 5 :
                    print(priorityQueue.numberOfQueuedTasks());
                    break;
                case 6 :
                    priorityQueue.displayTasks();
                    break;
            }

        } while (choice != 7);
    }

    private static void print(Process process){
        System.out.println("Process Data : " + process.getData());
        System.out.println("Process Priority : " + process.getPriority());
    }

    private static void print(int size){
        System.out.println("Queued Processes : " + size);
    }
}
