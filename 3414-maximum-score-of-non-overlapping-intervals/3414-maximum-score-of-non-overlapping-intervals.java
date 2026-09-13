class Solution {
    static class Interval {
        int right;
        int left;
        int weight;
        int index;

        Interval(int right, int left, int weight, int index) {
            this.right = right;
            this.left = left;
            this.weight = weight;
            this.index = index;
        }
    }

    static class State {
        long score;
        List<Integer> indices;

        State(long score, List<Integer> indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        Interval[] arr = new Interval[n];

        for (int i = 0; i < n; i++) {
            int left = intervals.get(i).get(0);
            int right = intervals.get(i).get(1);
            int weight = intervals.get(i).get(2);

            arr[i] = new Interval(right, left, weight, i);
        }

        Arrays.sort(arr, (a, b) -> {
            if (a.right != b.right) {
                return Integer.compare(a.right, b.right);
            }

            if (a.left != b.left) {
                return Integer.compare(a.left, b.left);
            }

            return Integer.compare(a.index, b.index);
        });

        int[] rightEnds = new int[n];

        for (int i = 0; i < n; i++) {
            rightEnds[i] = arr[i].right;
        }

        State[] previous = new State[n + 1];

        for (int i = 0; i <= n; i++) {
            previous[i] = new State(0, new ArrayList<>());
        }

        for (int selectedCount = 1; selectedCount <= 4; selectedCount++) {
            State[] current = new State[n + 1];
            current[0] = new State(0, new ArrayList<>());

            for (int i = 1; i <= n; i++) {
                State skip = current[i - 1];

                int previousCount = lowerBound(
                    rightEnds,
                    i - 1,
                    arr[i - 1].left
                );

                State old = previous[previousCount];

                List<Integer> newIndices = new ArrayList<>(old.indices);
                newIndices.add(arr[i - 1].index);
                Collections.sort(newIndices);

                State take = new State(
                    old.score + arr[i - 1].weight,
                    newIndices
                );

                current[i] = better(skip, take);
            }

            previous = current;
        }

        int[] answer = new int[previous[n].indices.size()];

        for (int i = 0; i < answer.length; i++) {
            answer[i] = previous[n].indices.get(i);
        }

        return answer;
    }

    private State better(State a, State b) {
        if (a.score != b.score) {
            return a.score > b.score ? a : b;
        }

        int size = Math.min(a.indices.size(), b.indices.size());

        for (int i = 0; i < size; i++) {
            int x = a.indices.get(i);
            int y = b.indices.get(i);

            if (x != y) {
                return x < y ? a : b;
            }
        }

        return a.indices.size() <= b.indices.size() ? a : b;
    }

    private int lowerBound(int[] arr, int end, int target) {
        int left = 0;
        int right = end;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}