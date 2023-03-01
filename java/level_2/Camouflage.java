package level_2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Camouflage {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new String[][] {{"yellow_hat", "headgear"},
                {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}}));
    }

    static class Solution {

        public int solution(String[][] clothes) {
            int answer = 0;

            Map<String, Set<String>> clothesByCategoryMap = new HashMap<String, Set<String>>();
            for (String[] cloth : clothes) {
                Set<String> clothSet = clothesByCategoryMap.get(cloth[1]);
                if (clothSet == null) {
                    clothSet = new HashSet<String>();
                    clothesByCategoryMap.put(cloth[1], clothSet);
                }
                clothSet.add(cloth[0]);
            }

            answer = 1;
            Set<String> keySet = clothesByCategoryMap.keySet();
            for (String key : keySet) {
                Set<String> clothSet = clothesByCategoryMap.get(key);
                answer *= (clothSet.size() + 1);
            }

            return answer - 1;
        }
    }

}
