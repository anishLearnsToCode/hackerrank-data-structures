import java.util.Scanner;

public class TruckTour {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int size = in.nextInt();
        PetrolPump[] pumps = new PetrolPump[size];
        for(int index=0 ; index<pumps.length ; index++){
            pumps[index] = new PetrolPump();
        }
        input(pumps);
        int ans = travelIndex(pumps);
        System.out.println(ans);
    }

    private static void input(PetrolPump[] pumps){
        for(int index=0 ; index<pumps.length ; index++){
            pumps[index].petrol   = in.nextLong();
            pumps[index].distance = in.nextLong();
        }
    }

    private static int travelIndex(PetrolPump[] pumps){
        if(pumps[0].petrol >= pumps[0].distance){
            return 0;
        }
        System.out.println("First Fail");
        pumps[0].isPossible = false;
        pumps[0].deficit = pumps[0].distance - pumps[0].petrol;

        int ans=-1;

        pumps[1].deficit = pumps[1].distance - pumps[1].petrol + pumps[0].deficit;
        pumps[1].isPossible = pumps[1].deficit <= 0 ;
        if(pumps[1].isPossible) ans = pumps.length-1;

        for(int index=pumps.length-2 ; index>=0 ; index--){
            pumps[index].deficit = (pumps[index].distance - pumps[index].petrol) - pumps[index + 1].deficit;
            pumps[index].isPossible = pumps[index].deficit <= 0;
            if(pumps[index].isPossible) ans = index;
        }

        return ans;
    }
}


class PetrolPump{
    long petrol;
    long distance;
    boolean isPossible = false;
    long deficit;

    PetrolPump(){}
    PetrolPump(long petrol, long distance, long deficit){
        this.petrol = petrol;
        this.distance = distance;
        this.deficit = deficit;
    }
    PetrolPump(long petrol, long distance, boolean isPossible, long deficit){
        this.petrol = petrol;
        this.distance = distance;
        this.isPossible = isPossible;
        this.deficit = deficit;
    }
}