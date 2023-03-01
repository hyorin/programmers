package level_2;

import java.util.Stack;
import java.util.stream.IntStream;

public class FunctionDevelop {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] result = s.solution(new int[] { 93, 30, 55 }, new int[] { 1, 30, 5 });
        for (int r : result) {
            System.out.print(r + ", ");
        }
    }

    static class Solution {
        public int[] solution(int[] progresses, int[] speeds) {
            int[] answer = {};
            IntStream.Builder answerBuilder = IntStream.builder();

            int currentDays = 0;
            Stack<Integer> release = new Stack<Integer>();
            for (int i = 0; i < progresses.length; i++) {
                int progress = progresses[i];
                int speed = speeds[i];
                int needDays = ((100 - progress) / speed) + ((100 - progress) % speed > 0 ? 1 : 0);

                if (currentDays < needDays) {
                    currentDays = needDays;

                    int size = release.size();
                    if (size > 0) {
                        answerBuilder.add(size);
                        release.clear();
                    }
                }
                release.add(i);
            }

            int size = release.size();
            if (size > 0) {
                answerBuilder.add(size);
                release.clear();
            }

            answer = answerBuilder.build().toArray();
            return answer;
        }
    }
}
