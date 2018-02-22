import java.util.Scanner;

class BigInteger{
    private int arr[];
    private boolean isNegative = false;
    private int digits=0, size;
    private static final int ten =10, hundred=100, thousand=1000, tenThousand=10000;
    public enum Number{
        Zero, One, Ten, Hundred,
        Thousand, TenThousand;
    }

    BigInteger(){
        arr = new int[20];
        size=20;
    }
    BigInteger(int size){
        arr = new int[size];
        this.size = size;
    }
    BigInteger(int size ,int number){
        arr = new int[size];
        this.size = size;
        add(number);
    }
    BigInteger(Number number){
        arr = new int[20];
        size = 20;
        add(number);
    }

    //Adding A number
    public void add(int number){
        while (number >= thousand && digits >= 4){
            add(Number.Thousand);
            number -= thousand;
        }
        while (number >= hundred && digits >= 3){
            add(Number.Hundred);
            number-=hundred;
        }
        while (number >= ten && digits >= 2){
            add(Number.Ten);
            number-=ten;
        }
        while (number-- > 0){
            add(Number.One);
        }
    }

    private void add(Number number){
        if(number == Number.Thousand){
            addAt(3);
        } else if(number == Number.Hundred){
            addAt(2);
        } else if(number == Number.Ten){
            addAt(1);
        } else if(number == Number.One)
            addAt(0);
    }

    private void addAt(int index){
        if(isNegative){
            isNegative = false;
            subtractAt(index);
            isNegative = (digits==1 && arr[digits-1] == 0) ? false : true;
            return;
        }

        arr[index]++;
        for( ; ; index++){
            if(index+1 > digits)
                digits = index+1;

            if(arr[index] % 10 == arr[index])
                break;

            if(size - digits < 5)
                grow();

            arr[index+1] += arr[index]/10;
            arr[index] = arr[index] % 10;
        }
    }

    private void grow(){
        int value[] = new int[arr.length * 2];
        for(int index = 0 ; index<digits ; index++){
            value[index] = arr[index];
        }
        size = 2 * size;
        arr = value;
    }


    //Subtracting a Number
    public void subtract(int number){
        while (number >= tenThousand && digits >= 5){
            subtract(Number.TenThousand);
            number -= tenThousand;
        }
        while (number >= thousand && digits >= 4){
            subtract(Number.Thousand);
            number -= thousand;
        }
        while (number >= hundred && digits >= 3){
            subtract(Number.Hundred);
            number -= hundred;
        }
        while (number >= ten && digits >= 2){
            subtract(Number.Ten);
            number -= ten;
        } while (number-- > 0){
            subtract(Number.One);
        }
    }

    public void subtract(BigInteger bigInteger){

    }

    private void subtract(Number number){
        if(number == Number.TenThousand)
            subtractAt(4);
        else if(number == Number.Thousand)
            subtractAt(3);
        else if(number == Number.Hundred)
            subtractAt(2);
        else if(number == Number.Ten)
            subtractAt(1);
        else subtractAt(0);
    }

    private void subtractAt(int index){
        if( (digits == 1 && arr[0] == 0) || (isNegative)){
            isNegative = true;
            addAt(index);
            return;
        }

        for( ; index < digits ; index++){
            if(arr[index] > 0) {
                arr[index]--;
                break;
            } else {
                arr[index] = 9;
            }
        }

        if(arr[digits-1] == 0 && digits != 1)
            digits--;
    }

    //Multiplying Number
    public void multiply(int number){
        while (number-- > 1){
            multiply(this);
        }
    }

    public void multiply(BigInteger bigInteger){

    }

    //Displaying Functions
    public int getDigits(){
        return digits;
    }

    public void display(){
        if(isNegative) System.out.print("-");
        for(int index=digits-1 ; index>=0 ; index--){
            System.out.print(arr[index]);
        }
    }
}

public class BigFactorial {
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        BigInteger integer = new BigInteger();
        integer.add(5);
        integer.display();
        System.out.println();

        System.out.println(integer.getDigits());
        integer.subtract(123);
        integer.display();
        System.out.println();

        integer.add(10);
        integer.display();
        System.out.println();

        int ans = integer.getDigits();
        System.out.println(ans);
    }

}
