import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        ArrayList<String> test = new ArrayList<>();
        test.add("cat");
        test.add("dog");
        test.add("mouse");
        test.add("mouse");
        test.add("mouse");
        test.add("mouse");
        test.add("mouse");
        test.add("mouse");
        System.out.println(test.size());
        for (int i = 1; i < test.size(); i++){
            System.out.println(i);
            System.out.println(test.get(i));
        }

    }
}
