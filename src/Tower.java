import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Tower {
    public static void main(String[] args) {
        int[] heights = new int[]{1, 5, 3, 6, 7, 6, 5};
        int[] answer = solution(heights);

        for(int i=0; i<answer.length; i++) {
            System.out.println(answer[i]);
        }
    }

    public static int[] solution(int[] heights) {
        Stack<Integer> heightsStack = new Stack<>();
        Stack<Integer> resultStack = new Stack<>();

        for(int i=0; i<heights.length; i++) { heightsStack.push(heights[i]); }

        while(!heightsStack.isEmpty()) {
            boolean selection = false;
            for (int i=heightsStack.size()-1; i>0; i--) {
                if(heightsStack.get(heightsStack.size()-1) < heightsStack.get(i-1)) {
                    resultStack.push(i);
                    selection = true;
                    break;
                }
            }
            if(!selection) {resultStack.push(0);}
            heightsStack.pop();
        }

        List<Integer> result = new ArrayList<>();
        while(!resultStack.isEmpty()) {result.add(resultStack.pop());}
        return Arrays.stream(result.toArray(new Integer[]{})).mapToInt(Integer::intValue).toArray();
    }
}
