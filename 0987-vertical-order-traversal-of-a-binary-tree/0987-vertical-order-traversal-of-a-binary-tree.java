class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> li = new ArrayList<>();
        HashMap<TreeNode,int[]> map = new HashMap<>();
        if(root == null) return li;
        map.put(root , new int[]{0,0});
        queue.offer(root);
        
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0 ; i<size ; i++){
                TreeNode node = queue.poll();
                if(node.left != null){
                    map.put(node.left , new int[]{map.get(node)[0]+1 , map.get(node)[1]-1});
                    queue.offer(node.left);
                }
                if(node.right != null){
                    map.put(node.right , new int[]{map.get(node)[0]+1 , map.get(node)[1]+1});
                    queue.offer(node.right);
                }
            }
        }
        List<int[]> nodes = new ArrayList<>();

        for (Map.Entry<TreeNode, int[]> entry : map.entrySet()) {

            TreeNode node = entry.getKey();
            int[] position = entry.getValue();

            nodes.add(new int[]{position[1],position[0],node.val});
        }
        nodes.sort((a,b) -> {
            if(a[0] != b[0]) return Integer.compare(a[0] , b[0]);
            if(a[1] != b[1]) return Integer.compare(a[1] , b[1]);
            return Integer.compare(a[2] , b[2]);
        });
         List<Integer> l = new ArrayList<>();
         int prev = nodes.get(0)[0];
        for(int[] node : nodes){
           if(node[0] != prev){
            li.add(l);
            l = new ArrayList<>();
            prev = node[0];
           }
           l.add(node[2]);

        }
        li.add(l);
        return li;
    }
}