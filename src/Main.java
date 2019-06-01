import java.util.*;

public class Main {

    public static void main(String[] args) {

        Solution solution = new Solution();
        Builder builder = new Builder();


    }
}

class Solution {
    

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

class Builder{
    public TreeNode buildTree1(){

        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(2);
        TreeNode node11 = new TreeNode(5);
        node1.left = node11;
        root.left = node1;
        root.right = node2;

        return root;

    }

    public TreeNode buildTree2(){

        TreeNode root = new TreeNode(2);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(3);
        TreeNode node12 = new TreeNode(4);
        TreeNode node22 = new TreeNode(7);
        node1.right = node12;
        node2.right = node22;
        root.left = node1;
        root.right = node2;

        return root;
    }

    public Node buildNTree(){
        Node n7 = new Node(7, null);
        Node n8 = new Node(8, null);
        List<Node> c4 = new ArrayList<>();
        c4.add(n7); c4.add(n8);

        Node n5 = new Node(5, null);
        Node n6 = new Node(6, c4);
        List<Node> c3 = new ArrayList<>();
        c3.add(n5); c3.add(n6);

        Node n3 = new Node(3, c3);
        Node n2 = new Node(2, c3);
        Node n4 = new Node(4, null);
        List<Node> c1 = new ArrayList<>();
        c1.add(n3); c1.add(n2); c1.add(n4);
        Node n1 = new Node(1, c1);
        return n1;
    }
}

class TreeNode{

    int val;

    TreeNode left;

    TreeNode right;

    TreeNode(int v){
        val = v;
    }


}

class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
}