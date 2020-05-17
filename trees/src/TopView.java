import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TopView {

    static class Property{
        int data, index;
        Property(int data, int index){
            this.data = data;
            this.index = index;
        }
    }

    private static HashMap<Integer, Integer> positionMap = new HashMap<>();

    public static void main(String[] args) {
        Tree<Integer> root = new Tree<>();
        root.input();
        root.print();

        topView(root, 0);
        print();
    }

    private static void print(){
//        ArrayList<Property> list = new ArrayList<Property>();
//        for(Map.Entry<Integer, Integer> entry : positionMap.entrySet()){
//            Property obj = new Property(entry.getValue(), entry.getKey());
//            list.add(obj);
//        }
//        list = mergeSort(list);
//        for(int i=0 ; i<list.size() ; i++){
//            System.out.print(list.get(i).data + " ");
//        }


        for(int i=-250 ; i<=250 ; i++){
            if(positionMap.containsKey(i))
                System.out.print(positionMap.get(i) + " ");
        }
    }

    private static ArrayList<Property> mergeSort(ArrayList<Property> list){
        if(list.size() <= 1)
            return list;

        ArrayList<Property> smallList1 = new ArrayList<>();
        ArrayList<Property> smallList2 = new ArrayList<>();
        int i;

        for(i=0 ; i<list.size()/2 ; smallList1.add(list.get(i)), i++);
        for( ; i<list.size() ; smallList2.add(list.get(i)), i++);

        ArrayList<Property> ans1 = mergeSort(smallList1);
        ArrayList<Property> ans2 = mergeSort(smallList2);

        return merge(ans1, ans2);
    }

    private static ArrayList<Property> merge(ArrayList<Property> list1, ArrayList<Property> list2){
        ArrayList<Property> ans = new ArrayList<>();
        int i, j, t;

        for(i=0, j=0 ; i <= list1.size() && j <= list2.size() ; ){

            if(i == list1.size()){
                for(t=0 ; t<list2.size() - j ; ans.add(list2.get(j+t)), t++);
                break;
            } else if(j == list2.size()){
                for(t=0 ; t<list1.size() - i ; ans.add(list1.get(i+t)), t++);
                break;
            }

            if(list1.get(i).index <= list2.get(j).index){
                ans.add(list1.get(i));
                i++;
            } else {
                ans.add(list2.get(j));
                j++;
            }
        }

        return ans;
    }

    private static void topView(Tree root, int position){
        if(root == null)
            return;

        if(!positionMap.containsKey(position)){
            positionMap.put(position, root.data);
        }

        topView(root.left, position-1);
        topView(root.right, position+1);
    }
}
