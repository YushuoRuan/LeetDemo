import sun.security.util.BitArray;


import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Solution solution = new Solution();

//        TreeNode tree1 = solution.buildTree1();
//        TreeNode tree2 = solution.buildTree2();
//
//        TreeNode treeMerge = solution.mergeTrees(tree1, tree2);
//
//        solution.recursion(treeMerge);

        List<Integer> s = solution.selfDividingNumbers(1,22);

        for(Integer i : s){
            System.out.println(i);
        }

    }

}

class TreeNode{

    int val;

    TreeNode leftChild;

    TreeNode rightChild;

    TreeNode(int v){
        val = v;
    }


}

class Solution {

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

    public TreeNode buildTree1(){

        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(2);
        TreeNode node11 = new TreeNode(5);
        node1.leftChild = node11;
        root.leftChild = node1;
        root.rightChild = node2;

        return root;

    }

    public TreeNode buildTree2(){

        TreeNode root = new TreeNode(2);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(3);
        TreeNode node12 = new TreeNode(4);
        TreeNode node22 = new TreeNode(7);
        node1.rightChild = node12;
        node2.rightChild = node22;
        root.leftChild = node1;
        root.rightChild = node2;

        return root;
    }


    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {

        Queue<TreeNode> q1 = new LinkedList<>();
        Queue<TreeNode> q2 = new LinkedList<>();
        q1.add(t1);
        q2.add(t2);

        while (!q1.isEmpty() && !q2.isEmpty()){

            ((LinkedList<TreeNode>) q1).pop();

        }

        return null;

    }

    public void recursion(TreeNode node){

        if(node==null){
            return;
        }


        recursion(node.leftChild);
        System.out.println(node.val);
        recursion(node.rightChild);

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