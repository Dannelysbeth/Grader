import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // write your code here
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        List<Integer> maximus = new ArrayList<>();
        List<Integer> seeds = new ArrayList<>();
        Set<Integer> tempSet = new HashSet<>();

        for (int i = a; i <= b; i++) {
            Random random = new Random(i);
            for (int j = 0; j < n; j++) {
                int randomNum = random.nextInt(k);
                tempSet.add(randomNum);
            }
            int tempMax = tempSet.stream().max(Integer::compare).get();
            maximus.add(tempMax);
            seeds.add(i);
            tempSet.clear();
        }
        int min = maximus.stream().min(Integer::compare).get();
        int index = maximus.indexOf(min);
        int seed = seeds.get(index);
        System.out.println(seed);
        System.out.println(min);

    }
}