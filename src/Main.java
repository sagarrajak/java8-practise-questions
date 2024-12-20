import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> num = Arrays.asList(12, 2, 3, 2, 4, 5, 12, 2, 3, 3, 3);

        Map<Integer, Integer> m = new HashMap<>();

        for (Integer x: num) {
            if(!m.containsKey(x)) m.put(x, 0);
            m.put(x, m.get(x) + 1);
        }
        List<int[]> lst = new ArrayList<>();

        for (int i=0; i<num.size(); i++) {
            lst.add(new int[]{num.get(i), i});
        }

        lst.sort((lhs, rhs) -> {
            if (Objects.equals(m.get(lhs[0]), m.get(rhs[0]))) {
                return  lhs[1] - rhs[1];
            }
            return  -m.get(lhs[0]) + m.get(rhs[0]);
         });

        List<Integer> list = lst.stream().map(x -> x[0]).toList();

        System.out.println(list.toString());
    }
}