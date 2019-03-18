import java.util.HashMap;

public class NoCompletionPlayer {
    public static void main(String[] args) {

        String[] participant = new String[]{"leo","kiki","kiki"};
        String[] completion = new String[]{"leo","kiki"};

        solution(participant, completion);
    }

    public static String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> hashMap = new HashMap<>();

        for(String s : participant) { hashMap.put(s, (hashMap.getOrDefault(s, 0)) + 1); };
        for(String s : completion) { hashMap.put(s, hashMap.get(s) - 1); };

        for(String str : hashMap.keySet()) {
            if(hashMap.get(str)!=0) {
                answer = str;
            }
        }
        return answer;
    }
}
