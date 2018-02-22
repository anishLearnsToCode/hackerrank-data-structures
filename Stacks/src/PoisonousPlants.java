import java.util.ArrayList;
import java.util.Scanner;

public class PoisonousPlants {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int size = in.nextInt();
        Plant[] arr = new Plant[size];
        input(arr);
        int days = numberOfDaysPlantsSurvive(arr);
        System.out.println(days);
    }

    private static int numberOfDaysPlantsSurvive(Plant plants[]){
        if(plants.length <= 1)
            return 0;

        int ans = 0, pivotIndex=0;
        plants[0].days = -1;
        ArrayList<Plant> plantList = new ArrayList<>();
        if(plants[1].data > plants[0].data) {
            plantList.add(plants[1]);
            plants[1].days = 0;
        } else {
            plants[1].days = -1;
            pivotIndex = 1;
        }

        for(int index=2 ; index<plants.length ; index++){
            if(plants[index].data > plants[index-1].data) {
                plants[index].days = 0;
                ans = Math.max(ans, plants[index].days);
                if(plantList.size() != 0) plantList.set(0, plants[index]);
                else plantList.add(plants[index]);
            } else {
                if(plants[index].data > plants[pivotIndex].data){
                    if(plantList.size() - 1 > plants[index-1].days){
                        if(plants[index].data > plantList.get(plantList.size()-1).data){
                            plants[index].days = plants[index-1].days + 1;
                            ans = Math.max(ans, plants[index].days);
                            plantList.set(plants[index].days, plants[index]);
                        } else {
                            plants[index].days = plantList.size();
                            ans = Math.max(ans, plants[index].days);
                            plantList.add(plants[index]);
                        }
                    } else {
                        plants[index].days = plants[index-1].days + 1;
                        ans = Math.max(ans, plants[index].days);
                        plantList.add(plants[index]);
                    }
                } else {
                    plantList = new ArrayList<>();
                    pivotIndex = index;
                }
            }
        }

        return ans == 0 ? ans : ans + 1;
    }

    private static void input(Plant arr[]){
        for(int index=0 ; index<arr.length ; index++){
            long data = in.nextInt();
            arr[index] = new Plant(data);
        }
    }
}


class Plant{
    long data;
    boolean willItDie;
    int days;

    Plant(){
        willItDie = false;
    }
    Plant(long data){
        this.data = data;
        willItDie = false;
    }
    Plant(long data, boolean willItDie){
        this.data = data;
        this.willItDie = willItDie;
    }
}