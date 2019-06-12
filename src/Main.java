import sun.reflect.generics.tree.Tree;

import java.util.*;
import java.util.logging.Level;

public class Main {

    public static void main(String[] args) {

        Solution solution = new Solution();
        Builder builder = new Builder();
        List<String> output= solution.fizzBuzz(15);
        System.out.println(output);
    }
}

class Solution {

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> resultList = new ArrayList<>();
        levelOrder(resultList, root, 0);
        return resultList;
        }

    public void levelOrder(List<List<Integer>> resultList, Node root, int level){

        if(root!=null){
            if(level>=resultList.size())
            {
                resultList.add(new ArrayList());
            }
            resultList.get(level).add(root.val);

            for(Node node:root.children){
                levelOrder(resultList, node,level+1);
            }
        }
    }

    public List<List<Integer>> levelOrderQueue(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root==null){
            return result;
        }
        Queue<Node> nodeQ = new LinkedList<>();
        Queue<Integer> levelQ = new LinkedList<>();
        ((LinkedList<Node>) nodeQ).add(root);
        ((LinkedList<Integer>) levelQ).add(1);
        List<Integer> currList = new ArrayList<>();
        while(!nodeQ.isEmpty()){
            Node currNode = nodeQ.poll();
            Integer currLevel = levelQ.poll();
            if(currNode.children!=null) {
                for (Node child : currNode.children) {
                    ((LinkedList<Node>) nodeQ).add(child);
                    ((LinkedList<Integer>) levelQ).add(currLevel+1);
                }
            }
            currList.add(currNode.val);
            if(levelQ.peek()!=currLevel){
                result.add(currList);
                currList = new ArrayList<>();
            }
        }
        return result;
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];
        List<Integer> l = new ArrayList<>();
        for(int i : nums2){
            l.add(i);
        }
        for(int i = 0; i<nums1.length; i++){
            result[i] = -1;
            int index = l.indexOf(nums1[i]);
            for(int j = index+1; j < nums2.length; j++){
                if(nums2[j] > nums1[i]){
                    result[i] = nums2[j];
                    break;
                }
            }
        }
        return result;
    }


    public int distributeCandies(int[] candies) {

        Set<Integer> hs = new HashSet<>();
        for(int i : candies){
            hs.add(i);
        }
        int max = candies.length/2;
        int kind = hs.size();
        return kind<max?kind:max;
    }

    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        HashSet<Integer> hs = new HashSet<>();
        for(int i : nums){
            if(hs.contains(i))
                hs.remove(i);
            else
                hs.add(i);

        }
        int result = -1;
        for(Integer i : hs){
            result = i;
        }
        return result;

//        int result = 0;
//        for (int num: nums) {
//            result ^= num;
//        }
//        return result;
    }

    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int[] hMax = new int[grid.length];
        int[] vMax = new int[grid[0].length];

        for(int i = 0; i < grid[0].length; i++) {
            int xMax = 0;
            for(int j = 0; j < grid.length; j++) {
                if (grid[j][i] > xMax)
                    xMax = grid[j][i];

            }
            vMax[i] = xMax;
        }
        for(int i = 0; i < grid.length; i++) {
            int yMax = 0;
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j]>yMax)
                    yMax = grid[i][j];
            }
            hMax[i] = yMax;
        }
        int change = 0;
        for(int i = 0; i<grid.length; i++){
            for(int j = 0; j<grid[i].length; j++){
                change+=(hMax[j]<vMax[i]?hMax[j]:vMax[i])-grid[i][j];
            }
        }
        return change;
    }

    public TreeNode trimBST(TreeNode root, int L, int R) {
        if(root == null)
            return null;
        if(root.val<L){
            root.right = trimBST(root.right,L,R);
            return root.right;
        }
        if(root.val>R){
            root.left = trimBST(root.left,L,R);
            return root.left;
        }
        root.left = trimBST(root.left,L,R);
        root.right = trimBST(root.right,L,R);
        return root;
    }

    public String[] uncommonFromSentences(String A, String B) {

        Map<String, Integer> map = new HashMap<>();
        String[] a = A.split(" ");
        String[] b = B.split(" ");

        for(String s : a){
            if(map.containsKey(s))
                map.replace(s, map.get(s)+1);
            else
                map.put(s,1);
        }
        for(String s : b){
            if(map.containsKey(s))
                map.replace(s, map.get(s)+1);
            else
                map.put(s,1);
        }
        List<String> l = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            if(entry.getValue() == 1)
                l.add(entry.getKey());
        }
        String[] result = new String[l.size()];
        return l.toArray(result);
    }

    public int maxDepth(TreeNode root) {
        if(root == null)
            return 0;

        int leftD = maxDepth(root.left);
        int rightD = maxDepth(root.right);
        return leftD>rightD?leftD+1:rightD+1;
    }

    public int islandPerimeter(int[][] grid) {
        int counter = 0;
        for(int i = 0; i<grid.length; i++){
            for(int j = 0; j<grid[0].length; j++){
                if(grid[i][j]==0)
                    continue;
                if(i-1<0||grid[i-1][j]==0)
                    counter++;
                if(i+1>grid.length-1||grid[i+1][j]==0)
                    counter++;
                if(j-1<0||grid[i][j-1]==0)
                    counter++;
                if(j+1>grid[0].length-1||grid[i][j+1]==0)
                    counter++;
            }
        }
        return counter;
    }

    public int calPoints(String[] ops) {
        int valid1 = 0;
        int valid2 = 0;
        int sum = 0;
        for(int i = 0; i<ops.length; i++){
            String o = ops[i];
            switch (o){
                case "C":
                    sum-=Integer.parseInt(ops[valid2]);
                    ops[valid2] = "I";
                    valid2 = valid1;
                    if(valid1>0)
                        valid1--;
                    while(valid1>0&&ops[valid1]=="I")
                        valid1--;
                    ops[i] = "I";
                    break;
                case "D":
                    ops[i] = Integer.toString(Integer.parseInt(ops[valid2])*2);
                    sum+=Integer.parseInt(ops[i]);
                    valid1 = valid2;
                    valid2=i;
                    break;
                case "+":
                    System.out.println(i);
                    System.out.println(valid1+": "+ops[valid1]);
                    System.out.println(valid2+": "+ops[valid2]);
                    ops[i] = Integer.toString(Integer.parseInt(ops[valid1])+Integer.parseInt(ops[valid2]));
                    sum+=Integer.parseInt(ops[i]);
                    valid1 = valid2;
                    valid2=i;
                    break;
                default:
                    sum += Integer.parseInt(o);
                    valid1 = valid2;
                    valid2=i;
                    break;

            }
        }
        return sum;
    }

    public boolean isToeplitzMatrix(int[][] matrix) {
        int row=matrix.length,col=matrix[0].length;
        for(int i=0;i<row;i++)
        {
            for(int j=0;j<col;j++)
            {
                if(j!=0&&i!=0)
                {
                    if(matrix[i][j]!=matrix[i-1][j-1])
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }


    public int findComplement(int num) {
        int length = Long.toBinaryString(num).length();
        return (int)((long)Math.pow(2,length)-1)-num;
    }

    public String removeDuplicates(String S) {
        int i = 1;
        int pos = 0;
        StringBuilder sb = new StringBuilder(S);
        while(i<sb.length()){
            if(sb.charAt(i)==sb.charAt(i-1)){
                int head = i-2;
                int tail = i+1;
                while((head>=0&&tail<sb.length())&&(sb.charAt(head)==sb.charAt(tail))) {
                    head--; tail++;
                }
                sb.replace(head+1, tail, "");
                i=head+1;
            }
            i++;
        }
        return sb.toString();
    }

    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> queue= new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        for(int i : stones)
            queue.add(i);
        while(queue.size()>1){
            int newStone = Math.abs(queue.poll()-queue.poll());
            if(newStone!=0)
                queue.add(newStone);
        }
        return queue.isEmpty()?0:queue.poll();
    }

    public String[] findWords(String[] words) {
        Map<Character, Integer> keyBoard = new HashMap<>();
        keyBoard.put('Q',1);
        keyBoard.put('W',1);
        keyBoard.put('E',1);
        keyBoard.put('R',1);
        keyBoard.put('T',1);
        keyBoard.put('Y',1);
        keyBoard.put('U',1);
        keyBoard.put('I',1);
        keyBoard.put('O',1);
        keyBoard.put('P',1);
        keyBoard.put('A',2);
        keyBoard.put('S',2);
        keyBoard.put('D',2);
        keyBoard.put('F',2);
        keyBoard.put('G',2);
        keyBoard.put('H',2);
        keyBoard.put('J',2);
        keyBoard.put('K',2);
        keyBoard.put('L',2);
        keyBoard.put('Z',3);
        keyBoard.put('X',3);
        keyBoard.put('C',3);
        keyBoard.put('V',3);
        keyBoard.put('B',3);
        keyBoard.put('N',3);
        keyBoard.put('M',3);

        List<String> result = new ArrayList<>();
        for(String word : words){
            String wordU = word.toUpperCase();
            boolean oneline = true;
            for(int i = 1; i<wordU.length(); i++){
                if(keyBoard.get(wordU.charAt(i))!=keyBoard.get(wordU.charAt(i-1))){
                    oneline = false;
                    break;
                }
            }
            if(oneline)
                result.add(word);
        }
        String[] result2 = new String[result.size()];
        return result.toArray(result2);
    }

    public int[] numberOfLines(int[] widths, String S) {
        int[] result = new int[2];
        int lineCount = 1;
        int currWidth = 0;
        for(int i = 0; i<S.length(); i++){
            int charWidth = widths[S.charAt(i)-97];
            if(currWidth+charWidth>100){
                lineCount++;
                currWidth=0;
            }
            currWidth+=charWidth;
        }
        result[0] = lineCount;
        result[1] = currWidth;
        return result;
    }

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> vals1 = new ArrayList<>();
        List<Integer> vals2 = new ArrayList<>();
        recursion(root1, vals1);
        recursion(root2, vals2);
        if(vals1.size()!=vals2.size()) return false;
        for(int i = 0; i<vals1.size(); i++){
            if(vals1.get(i)!=vals2.get(i))
                return false;
        }
        return true;

    }
    public void recursion(TreeNode node, List<Integer> vals){
        if(node == null)
            return;
        recursion(node.left);
        if(node.left == null && node.right==null)
            vals.add(node.val);
        recursion(node.right);
    }

    public void reverseString(char[] s) {
        for(int i = 0; i<s.length/2; i++){
            char tmp = s[i];
            s[i] = s[s.length-1-i];
            s[s.length-1-i] = tmp;
        }
    }

    public int[] shortestToChar(String S, char C) {

        List<Integer> mI = new ArrayList<>();
        int[] result = new int[S.length()];
        for(int i = 0; i < S.length(); i++){
            if(S.charAt(i) == C)
                mI.add(i);
        }
        for(int i = 0; i < mI.get(0); i++){
            result[i] = mI.get(0)-i;
        }
        for(int i = 1; i < mI.size(); i++){
            int interval = mI.get(i)-mI.get(i-1);
            for(int j = 1; j<interval; j++)
                if(j<=interval/2)
                    result[mI.get(i-1)+j] = j;
                else
                    result[mI.get(i-1)+j] = interval-j;
        }
        for(int i = mI.get(mI.size()-1)+1; i<S.length(); i++){
            result[i] = i - mI.get(mI.size()-1);
        }
        return result;
    }

    public int[][] transpose(int[][] A) {
        int row = A.length;
        int col = A[0].length;
        int[][] result = new int[col][row];
        for(int i = 0; i<row; i++){
            for(int j = 0; j<col; j++){
                result[j][i] = A[i][j];
            }
        }
        return result;
    }

    public int[] sumEvenAfterQueries(int[] A, int[][] queries) {
        int evenSum = 0;
        for(int i : A){
            if(i%2==0) evenSum += i;
        }
        int[] result = new int[queries.length];
        for(int i = 0; i<queries.length; i++){
            int oldNum = A[queries[i][1]];
            int newNum = oldNum+queries[i][0];
            if(Math.abs(oldNum)%2==0) evenSum = (newNum%2 == 0)?evenSum+queries[i][0]:evenSum-oldNum;
            if(Math.abs(oldNum)%2==1) evenSum = (newNum%2 == 0)?evenSum+newNum:evenSum;
            result[i] = evenSum;
            A[queries[i][1]] = newNum;
        }
        return result;
    }

    public ListNode middleNode(ListNode head) {
        ListNode currNode = head;
        ListNode middle = head;
        int i = 0;
        while(currNode.next != null){
            currNode = currNode.next;
            i++;
            if(i%2==1)
                middle = middle.next;
        }
        return middle;
    }

    public int fixedPoint(int[] A) {
        int i = 0;
        while(i<A.length){
            if(A[i]==i)
                break;
            i++;
        }
        return (i==A.length)?-1:i;
    }

    public String reverseWords(String s) {
        String[] words= s.split(" ");
        String result = "";
        for(int i = 0; i<words.length-1; i++){
            StringBuilder sb = new StringBuilder(words[i]);
            result = result + sb.reverse().toString() + " ";
        }
        StringBuilder sb = new StringBuilder(words[words.length-1]);
        result = result + sb.reverse().toString();
        return result;
    }

    public List<TreeNode> list = new ArrayList<>();

    public TreeNode increasingBST(TreeNode root) {
        recursion(root);
        TreeNode leftmost = list.get(0);
        TreeNode currNode = leftmost;
        for(int i = list.size()-2; i>=0; i--){
            list.get(i).right = list.get(i+1);
        }
        return list.get(0);
    }

    public void recursion(TreeNode node){
        if(node == null)
            return;
        recursion(node.left);
        TreeNode newNode = new TreeNode(node.val);
        list.add(newNode);
        recursion(node.right);
    }

    public int smallestRangeI(int[] A, int K) {
        if(A.length<2)
            return 0;
        int max = 0;
        int min = 99999999;
        for(int i : A){
            if(i>max) max=i;
            if(i<min) min=i;
        }
        int range = max-min-Math.abs(2*K);
        return range>0?range:0;
    }

    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<>();
        for(String s : cpdomains){
            String[] tmp = s.split(" ");
            int times = Integer.parseInt(tmp[0]);
            String domain = tmp[1];
            String[] domainArr = domain.split("\\.");
            String subDomain = "";
            for(int i = domainArr.length-1; i>=0; i--){
                if(i==domainArr.length-1)
                    subDomain = domainArr[i];
                else
                    subDomain = domainArr[i]+"."+subDomain;
                if(map.containsKey(subDomain))
                    map.replace(subDomain, map.get(subDomain)+times);
                else
                    map.put(subDomain, times);
            }
        }

        List<String> result = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            result.add(entry.getValue().toString()+" "+entry.getKey());
        }
        return result;
    }

    public int maxDepth(Node root) {

        if(root == null){
            return 0;
        }
        if(root.children==null){
            return 1;
        }
        int max = 0;
        for(Node children: root.children){
            int currD = maxDepth(children);
            if(currD>max)
                max = currD;
        }
        return max+1;
    }

    public int maxDepthStack(Node root) {

        if(root == null)
            return 0;

        Stack<Node> nodes = new Stack<>();
        Stack<Integer> depths = new Stack<>();
        nodes.push(root);
        depths.push(1);

        int maxD = 0;
        while(!nodes.isEmpty()){
            Node currNode = nodes.pop();
            Integer currDepth = depths.pop();
            if(currDepth>maxD)
                maxD = currDepth;
            if(currNode.children==null)
                continue;
            for(Node n: currNode.children){
                nodes.push(n);
                depths.push(currDepth+1);
            }
        }

        return maxD;

    }

    public int projectionArea(int[][] grid) {
        int xArea = 0, yArea = 0, zArea = 0;
        for(int i = 0; i < grid[0].length; i++)
        {
            int xMax = 0;
            for(int j = 0; j < grid.length; j++){
                if(grid[j][i]>xMax)
                    xMax = grid[j][i];
                if(grid[j][i]!=0)
                    zArea++;
            }
            xArea+=xMax;
        }
        for(int i = 0; i < grid.length; i++)
        {
            int yMax = 0;
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j]>yMax)
                    yMax = grid[i][j];
            }
            yArea+=yMax;
        }
        return xArea+yArea+zArea;
    }

    public List<String> commonChars(String[] A) {
        ArrayList<String> first = new ArrayList<String>(Arrays.asList(A[0].split("")));
        Collections.sort(first);

        for(int i = 0; i<first.size(); i++) {
            String tmp = "";
            for (int j = 1; j < A.length; j++) {
                tmp = A[j];
                A[j] = A[j].replaceFirst(first.get(i), "");
                if(tmp.equals(A[j])){
                    first.remove(i);
                    i--;
                    break;
                }
            }
        }
        return first;
    }

    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[][] result = new int[R*C][2];
        int index = 0;
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                result[index][0] = i;
                result[index][1] = j;
                index++;
            }
        }
        Arrays.sort(result, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                Integer c1 = Math.abs(r0-o1[0])+Math.abs(c0-o1[1]);
                Integer c2 = Math.abs(r0-o2[0])+Math.abs(c0-o2[1]);
                return c1.compareTo(c2);
            }
        });
        return result;
    }

    public int numRookCaptures(char[][] board) {
        int i, j=0;
        for(i = 0; i<board.length; i++){
            boolean found = false;
            for(j = 0; j < board[i].length; j++){
                if(board[i][j]=='R'){
                    found = true;
                    break;
                }
            }
            if(found)
                break;
        }
        int count = 0;
        for(int u = i-1; u >= 0; u--){
            if(board[u][j]=='B')
                break;
            if(board[u][j]=='p'){
                count++;
                break;
            }
        }
        for(int d = i+1; d < board.length; d++){
            if(board[d][j]=='B')
                break;
            if(board[d][j]=='p'){
                count++;
                break;
            }
        }
        for(int l = j-1; l >= 0; l--){
            if(board[i][l]=='B')
                break;
            if(board[i][l]=='p'){
                count++;
                break;
            }
        }
        for(int r = j+1; r < board[0].length; r++){
            if(board[i][r]=='B')
                break;
            if(board[i][r]=='p'){
                count++;
                break;
            }
        }
        return count;
    }

    public int fib(int N) {
        if(N==0)
            return 0;
        if(N==1)
            return 1;

        return fib(N-1)+fib(N-2);
    }

    public int[] sortArrayByParityII(int[] A) {
        int[] result = new int[A.length];
        int evenPos = 0;
        int oddPos = 1;

        for(int i: A){
            if(i%2==0){
                result[evenPos] = i;
                evenPos+=2;
            }
            else{
                result[oddPos] = i;
                oddPos+=2;
            }
        }
        return result;
    }

    public boolean isUnivalTree(TreeNode root) {
        if(root == null){
            return true;
        }

        if(root.right!=null&&root.right.val!=root.val)
            return false;

        if(root.left!=null&&root.left.val!=root.val)
            return false;

        else
            return isUnivalTree(root.left)&&isUnivalTree(root.right);
    }

    public List<Integer> postorder(Node root) {
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }

        Queue<Node> q = new LinkedList<>();
        ((LinkedList<Node>) q).push(root);

        while(!q.isEmpty()){
            Node node = q.poll();
            result.add(node.val);
            for(int i = 0; i<node.children.size(); i++){
                ((LinkedList<Node>) q).push(node.children.get(i));
            }
        }
        Collections.reverse(result);
        return result;
    }



    public List<Integer> preorder(Node root) {
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }

        Stack stack = new Stack();
        stack.push(root);

        while(!stack.isEmpty()){
            Node node = (Node)stack.pop();
            result.add(node.val);
            for(int i = node.children.size()-1; i>=0; i--){
                stack.push(node.children.get(i));
            }
        }
        return result;
    }

    public TreeNode searchBST(TreeNode root, int val) {
        TreeNode ptr = root;
        while(ptr!=null){
            if(val==ptr.val)
                return ptr;
            if(val<ptr.val)
                ptr = ptr.left;
            else if(val>ptr.val)
                ptr = ptr.right;

        }
        return ptr;
    }


    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for(int i=0; i<nums.length; i+=2){
            sum = sum+nums[i];
        }
        return sum;
    }

    public int minDeletionSize(String[] A) {
        int arrLen = A.length;
        int strLen = A[0].length();
        if(arrLen==0 || strLen==0)
            return 0;

        int count = 0;
        for(int i=0; i<strLen; i++){
            for(int j = 0; j<arrLen-1; j++){
                if(!(A[j].charAt(i)<=A[j+1].charAt(i))){
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    public int heightChecker(int[] heights) {

        int[] ascendH = heights.clone();
        Arrays.sort(ascendH);

        int count = 0;
        for (int i = 0; i < heights.length; i++) {
            if (heights[i] != ascendH[i]) {
                count++;
            }
        }
        return count;


    }



    public int peakIndexInMountainArray(int[] A) {
        int len = A.length;
        int i = len/2;
        int offset = len/4;

        boolean found = false;
        while(!found){
            if(A[i-1]<A[i] && A[i]>A[i+1]){
                found=true;
                break;
            }
            else if(A[i-1]<A[i]){
                i=i+offset;
            }
            else{
                i=i-offset;
            }
            offset=offset/2;
            if(offset==0)
                offset = 1;
        }
        return A[i];
    }

    public int[] diStringMatch(String S) {
        int len = S.length();
        int[] result = new int[len+1];
        int low = 0, high = len;

        for(int i= 0; i<len; i++){
            if(S.charAt(i)=='I')
                result[i] = low++;
            else
                result[i] = high--;
        }

        result[len] = low;

        return result;
    }

    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> result = new ArrayList<>();
        for(int i = left; i<=right; i++){
            String iStr = String.valueOf(i);
            boolean flag = true;
            for(int j = 0; j< iStr.length(); j++){
                if((iStr.charAt(j)-48)==0) {
                    flag = false;
                    break;
                }
                if(i%(iStr.charAt(j)-48)!=0) {
                    flag = false;
                    break;
                }
            }
            if(flag){
                result.add(i);
            }
        }
        return result;
    }



    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1==null){
            return t2;
        }

        if(t2==null){
            return t1;
        }

        t1.val = t1.val+t2.val;

        t1.left = mergeTrees(t1.left,t2.left);
        t1.right = mergeTrees(t1.right,t2.right);


        return t1;

    }

    public void printTree(TreeNode node){

        if(node == null){
            return;
        }

        printTree(node.left);
        System.out.println(node.val);
        printTree(node.right);
    }



    public int hammingDistance(int x, int y) {

        String bx = Integer.toBinaryString(x);
        String by = Integer.toBinaryString(y);

        int LenDiff = by.length() - bx.length();

        int counter = 0;
        if(LenDiff<0){
            for(int i = 1; i <= by.length(); i++){
                if(bx.charAt(bx.length()-i)!=by.charAt(by.length()-i)){
                    counter++;
                }
            }
            for(int i = 0; i<Math.abs(LenDiff);i++){
                if(bx.charAt(i)=='1'){
                    counter++;
                }
            }
        }
        else if(LenDiff>0){
            for(int i = 1; i <= bx.length(); i++){
                if(bx.charAt(bx.length()-i)!=by.charAt(by.length()-i)){
                    counter++;
                }
            }
            for(int i = 0; i<LenDiff;i++){
                if(by.charAt(i)=='1'){
                    counter++;
                }
            }
        }

        else{
            for(int i = 0; i < bx.length(); i++){
                if(bx.charAt(i)!=by.charAt(i)){
                    counter++;
                }
            }
        }

        return counter;
    }

    public boolean judgeCircle(String moves) {

        char[] ch = moves.toCharArray();
        int horizontal = 0;
        int vertical = 0;
        for(char c: ch){
            switch (c){
                case 'U':
                    vertical++;
                    break;
                case 'D':
                    vertical--;
                    break;
                case 'R':
                    horizontal++;
                    break;
                case 'L':
                    horizontal--;
                    break;
            }
        }

        return (horizontal==0 && vertical==0)? true: false;
    }

    public int numUniqueEmails(String[] emails) {

        ArrayList<String> trueEmails = new ArrayList<>();

        for(String s: emails){
            if(s.contains("@")){
                String localName = s.substring(0, s.indexOf("@"));
                if(localName.contains("+"))
                    localName = localName.substring(0, localName.indexOf("+"));
                localName=localName.replace(".","");

                String fullAddress = localName + s.substring(s.indexOf("@"));

                if(!trueEmails.contains(fullAddress))
                    trueEmails.add(fullAddress);
            }
            else
                continue;
        }
        return trueEmails.size();
    }


    public int repeatedNTimes(int[] A) {
        Map<Integer,Integer> map = new HashMap<>();

        for(int i = 0; i < A.length; i++){
            if(map.containsKey(A[i])){
                int newTime = map.get(A[i])+1;
                if(newTime==A.length/2)
                    return A[i];
                map.replace(A[i], newTime);
            }
            else{
                map.put(A[i], 1);
            }
        }
        return 0;
    }

    public int[] sortedSquares(int[] A) {

        int nIndex = 0;
        int pIndex = 0;
        for(int i = 0; i < A.length; i++){
            if(A[i]<0)
                nIndex=i;

            A[i] = A[i]*A[i];
        }

        pIndex = nIndex+1;

        int[] result = new int[A.length];
        int pos=0;

        while(pIndex<A.length && nIndex>=0){

            if(A[pIndex]<A[nIndex]){
                result[pos] = A[pIndex];
                pIndex++;
            }
            else{
                result[pos] = A[nIndex];
                nIndex--;
            }

            pos++;
        }

        if(pIndex==A.length){
            for(int i = nIndex; i>=0; i--){
                result[pos] = A[i];
                pos ++;
            }
        }
        else{
            for(int i = pIndex; i<A.length; i++){
                result[pos] = A[i];
                pos ++;
            }
        }



        return result;
    }

    public int[][] flipAndInvertImage(int[][] A) {
        int row = A.length;
        int col = A[0].length;

        int[][] result = new int[row][col];

        for(int i =0; i<row; i++){
            for(int j = col-1; j>=0; j--) {
                result[i][col-j-1] = (A[i][j]==0)?1:0;
            }
        }

        return result;
    }

    public int uniqueMorseRepresentations(String[] words) {
        String[] morse = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};

        ArrayList<String> codes = new ArrayList<>();

        for(String s: words){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < s.length(); i++){
                sb.append(morse[s.charAt(i)-97]);
            }
            if(!codes.contains(sb.toString()))
                codes.add(sb.toString());
        }

        for(String s: codes){
            System.out.println(s);
        }

        return codes.size();
    }



    public int[] sortArrayByParity(int[] A) {
        int[] intList = new int[A.length];

        int oddI = A.length-1;
        int evenI = 0;

        for(int i: A){
            if(i%2==0) {
                intList[evenI]=i;
                evenI=evenI+1;
            }
            else{
                intList[oddI] = i;
                oddI = oddI-1;
            }
        }
        return intList;
    }


}