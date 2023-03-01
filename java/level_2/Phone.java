package level_2;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * 
 */
public class Phone {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new String[] { "123", "456", "789" }));
    }

    static class Solution {
        public boolean solution(String[] phone_book) {
            boolean answer = true;

            Set<String> phoneBookSet = new HashSet<String>();
            for (String phone : phone_book) {
                phoneBookSet.add(phone);
            }

            for (String phone : phone_book) {
                for (int i = 1; i < phone.length(); i++) {
                    if (phoneBookSet.contains(phone.substring(0, i))) {
                        return false;
                    }
                }
            }
            return answer;
        }

    }

}
