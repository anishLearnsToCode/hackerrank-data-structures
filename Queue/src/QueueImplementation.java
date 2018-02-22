import java.util.Scanner;

class QueueOverFlowException extends Exception{
    QueueOverFlowException(){}
    QueueOverFlowException(int index){
        System.out.println("QueueOverFlowException : " + index);
    }
}

class QueueUnderFlowException extends Exception{
    QueueUnderFlowException(){}
    QueueUnderFlowException(int index){
        System.out.println("QueueUnderFlowException : " + index);
    }
}

class QueueIndexOutOfBoundsException extends Exception{
    QueueIndexOutOfBoundsException(){}
    QueueIndexOutOfBoundsException(int index){
        System.out.println("QueueIndexOutOfBoundsException : " + index);
    }
}

class Queue{
    private Scanner in = new Scanner(System.in);
    private int size=0;
    private int arr[];
    private int head =0, tail=-1, length;
    private final double LOAD_FACTOR = 0.8;
    private final int MAX_SIZE = 500;

    Queue(){
        arr = new int[10];
        length = arr.length;
    }
    Queue(int size){
        arr = new int[size];
        length = size;
    }

    //Insertion Methods
    public void insertSingle() throws QueueOverFlowException{
        if(size + 1 >= MAX_SIZE)
            throw new QueueOverFlowException(size+1);
        insert();
    }

    public void insertMultiple() throws QueueOverFlowException{
        System.out.print("Enter no. of elements you wish to enter : ");
        int numberOfElements = in.nextInt();
        if(numberOfElements + size >= MAX_SIZE)
            throw new QueueOverFlowException(numberOfElements + size);

        while (numberOfElements-- > 0){
           insert();
        }
    }

    private void insert(){
        System.out.print("Enter Data : ");
        int data = in.nextInt();
        insertData(data);
    }

    private void insertData(int data){
        arr[(tail+1) % length] = data;
        tail++;
        size++;
        if((double)size / arr.length >= LOAD_FACTOR)
            grow();
    }

    private void grow(){
        int temp[] = new int[2 * arr.length];
        addTo(temp);
        arr = temp;
        tail = size - 1;
        length = 2*length;
        head = 0;
    }

    private void addTo(int temp[]){
        for(int index=head, tempIndex=0 ; tempIndex < size ; index++, tempIndex++) {
            temp[tempIndex] = arr[index % length];
        }
    }

    //Removal and Peeking methods
    public int deQueue() throws QueueUnderFlowException{
        if(size == 0)
            throw new QueueUnderFlowException();
        int data = arr[head % length];
        head++;
        size--;
        return data;
    }

    public int peek(){
        return arr[head % length];
    }

    //Displaying Methods
    public void printQueue(){
        for(int index=0, queueIndex=head ; index<size ; index++, queueIndex++){
            System.out.print(arr[queueIndex%length] + " ");
        }
    }

    public int get(int index) throws QueueIndexOutOfBoundsException{
        if((head + index) % length >= size)
            throw new QueueIndexOutOfBoundsException((head + index) % length);
        return arr[ (head + index) % length];
    }

    public int queueSize(){
        System.out.println(length);
        return size;
    }
}

public class QueueImplementation {
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws QueueOverFlowException, QueueUnderFlowException, QueueIndexOutOfBoundsException{
        Queue queue = new Queue();
        int option;

        do {
            System.out.println("\n\nSelect any of the following options : ");
            System.out.println("1) EnQueue Single Element");
            System.out.println("2) EnQueue Multiple Elements\n");

            System.out.println("3) DeQueue Element ");
            System.out.println("4) Peek Head Element\n");

            System.out.println("5) Print Queue");
            System.out.println("6) Get Element At");
            System.out.println("7) Queue Size");
            System.out.println("8) Exit");
            option = in.nextInt();

            switch (option){
                case 1 :
                    queue.insertSingle();
                    break;
                case 2 :
                    queue.insertMultiple();
                    break;
                case 3 :
                    print(queue.deQueue());
                    break;
                case 4 :
                    print(queue.peek());
                    break;
                case 5 :
                    queue.printQueue();
                    break;
                case 6 :
                    System.out.print("Enter Index : ");
                    int index = in.nextInt();
                    print("The element at " + index + " is : ", queue.get(index));
                    break;
                case 7 :
                    print("Queue Size, No. of elements = ", queue.queueSize());
            }
        }while (option != 8);
    }

    private static void print(int data){
        print("", data);
    }

    private static void print(String string, int data){
        System.out.println(string + data);
    }
}
