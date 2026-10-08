class Solution {
    public int ladderLength(String st, String end, List<String> list) {

        //using patter storing method
        //needed thing 1.set 2.2*map(one for vis and second for pattern) 3.Queue as usual bfs

        Set<String> set = new HashSet<>();

        for (String s : list)
            set.add(s);

        if (!set.contains(end))
            return 0;

        Map<String, Boolean> vis = new HashMap<>();
        vis.put(st, true);

        Map<String, List<String>> pattern = new HashMap<>();

        Queue<String> que = new LinkedList<>();
        que.offer(st);

        for (String s : list) {

            //for pattern crteing

            //synatx for computeIfAbsebt(key , function) as in place of fxn using lamda expression
            for (int i = 0; i < s.length(); i++) {

                String word = s.substring(0, i) + "*" + s.substring(i + 1);
                pattern.computeIfAbsent(word, k -> new ArrayList<>()).add(s);

            }

        }

        int lvl = 1;
        while (!que.isEmpty()) {

            int size = que.size();

            for (int i = 0; i < size; i++) {

                String s = que.poll();

                if (s.equals(end))
                    return lvl;

                for (int j = 0; j < s.length(); j++) {
                    String pat = s.substring(0, j) + "*" + s.substring(j + 1);
                    for (String nei : pattern.getOrDefault(pat, List.of())) {
                        if (!vis.getOrDefault(nei, false)) {
                            vis.put(nei, true);
                            que.offer(nei);
                        }
                    }
                }
            }

            lvl++;
        }

        return 0;
    }
}