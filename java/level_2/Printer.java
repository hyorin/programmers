package level_2;

import java.util.ArrayDeque;
import java.util.NavigableSet;
import java.util.Queue;
import java.util.TreeSet;

public class Printer {
    public static void main(String[] args) {
        Solution s = new Solution();
        int index = s.solution(new int[] { 2, 1, 3, 2 }, 2);
        int index2 = s.solution(new int[] { 1, 1, 9, 1, 1, 1 }, 0);
        System.out.println(index);
        System.out.println(index2);
    }

    static class PrintJob implements Comparable<PrintJob> {
        final int priority;
        final int location;

        public PrintJob(int priority, int location) {
            this.priority = priority;
            this.location = location;
        }

        public int getPriority() {
            return priority;
        }

        public int getLocation() {
            return location;
        }

        @Override
        public int compareTo(PrintJob o) {
            if (priority < o.getPriority()) {
                return -1;
            } else if (priority > o.getPriority()) {
                return 1;
            }

            if (location < o.getLocation()) {
                return -1;
            } else if (location > o.getLocation()) {
                return 1;
            }
            return 0;
        }
    }

    static class Solution {
        public int solution(int[] priorities, int location) {
            int answer = 0;

            NavigableSet<PrintJob> prioritySet = new TreeSet<PrintJob>();
            Queue<PrintJob> printJobQueue = new ArrayDeque<PrintJob>();
            for (int i = 0; i < priorities.length; i++) {
                PrintJob pj = new PrintJob(priorities[i], i);
                prioritySet.add(pj);
                printJobQueue.add(pj);
            }

            while (!printJobQueue.isEmpty()) {
                PrintJob printJob = printJobQueue.poll();
                PrintJob last = prioritySet.last();
                if (last.getPriority() > printJob.getPriority()) {
                    printJobQueue.add(printJob);
                    continue;
                }

                prioritySet.pollLast();
                answer++;

                int processedLocation = printJob.getLocation();
                if (location != processedLocation) {
                    continue;
                }

                break;
            }

            return answer;
        }
    }
}