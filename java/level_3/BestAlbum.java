package level_3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.IntStream;

public class BestAlbum {

    public static void main(String[] args) {
        Solution s = new Solution();

        String[] genres = { "classic", "pop", "classic", "classic", "pop" };
        int[] plays = { 500, 600, 150, 800, 2500 };
        int[] result = s.solution(genres, plays);
        for (int r : result) {
            System.out.print(r + ", ");
        }
    }

    static class Solution {

        public int[] solution(String[] genres, int[] plays) {
            int[] answer = {};

            Map<String, Integer> allPlayTimeByGenreMap = new HashMap<String, Integer>();
            Map<String, NavigableSet<PlayData>> map = new HashMap<String, NavigableSet<PlayData>>();

            for (int i = 0; i < genres.length; i++) {
                String genre = genres[i];
                int play = plays[i];
                PlayData pd = new PlayData(i, play);

                NavigableSet<PlayData> playDataSet = map.get(genre);
                if (playDataSet == null) {
                    playDataSet = new TreeSet<PlayData>();
                    map.put(genre, playDataSet);
                }
                playDataSet.add(pd);

                int allPlayTime = 0;
                Integer currentPlayTime = allPlayTimeByGenreMap.get(genre);
                if (currentPlayTime != null) {
                    allPlayTime += currentPlayTime;
                }
                allPlayTime += play;
                allPlayTimeByGenreMap.put(genre, allPlayTime);
            }

            Set<Entry<String, Integer>> entrySet = allPlayTimeByGenreMap.entrySet();

            List<Entry<String, Integer>> entryList = new ArrayList<Entry<String, Integer>>(entrySet);

            Collections.sort(entryList, new Comparator<Entry<String, Integer>>() {

                @Override
                public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                    return o2.getValue().compareTo(o1.getValue());
                }

            });

            IntStream.Builder builder = IntStream.builder();
            for (Entry<String, Integer> entry : entryList) {
                String genre = entry.getKey();
                NavigableSet<PlayData> playDataSet = map.get(genre);
                if (playDataSet == null) {
                    continue;
                }
                for (int count = 0; count < 2; count++) {

                    PlayData playData = playDataSet.pollFirst();
                    if (playData == null) {
                        break;
                    }
                    int id = playData.getId();
                    builder.add(id);
                }
            }

            IntStream answerStream = builder.build();
            answer = answerStream.toArray();
            return answer;
        }

        class PlayData implements Comparable<PlayData> {
            private final int id;
            private final int play;

            public PlayData(int id, int play) {
                this.id = id;
                this.play = play;
            }

            public int getId() {
                return id;
            }

            public int getPlay() {
                return play;
            }

            @Override
            public int compareTo(PlayData o) {
                if (play > o.getPlay()) {
                    return -1;
                } else if (play < o.getPlay()) {
                    return 1;
                }

                if (id < o.getId()) {
                    return -1;
                } else if (id > o.getId()) {
                    return 1;
                }
                return 0;
            }

        }
    }
}