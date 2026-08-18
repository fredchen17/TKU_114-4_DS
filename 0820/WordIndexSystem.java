import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordIndexSystem {
    public static void main(String[] args) {
        String[] sentences = {
            "Java is a powerful programming language.",
            "Python is also a popular language, but Java is widely used in enterprise.",
            "Object-oriented programming in Java makes code reusable and clean."
        };

        Map<String, Integer> wordCountMap = new HashMap<>();
        Set<String> uniqueWordsSet = new HashSet<>();

        System.out.println("=== 原始輸入句子 ===");
        for (String sentence : sentences) {
            System.out.println("- " + sentence);
            
            String cleanSentence = sentence.replaceAll("[,.]", "");
            String[] words = cleanSentence.split("\\s+");

            for (String word : words) {
                if (word.isEmpty()) {
                    continue;
                }
                String lowerWord = word.toLowerCase();

                uniqueWordsSet.add(lowerWord);
                wordCountMap.put(lowerWord, wordCountMap.getOrDefault(lowerWord, 0) + 1);
            }
        }

        System.out.println("\n=== 1. 不重複單字集合 (Set, 共 " + uniqueWordsSet.size() + " 個) ===");
        System.out.println(uniqueWordsSet);

        System.out.println("\n=== 2. 所有單字出現次數統計 (Map) ===");
        for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
            System.out.printf("%-15s : %d 次\n", entry.getKey(), entry.getValue());
        }

        System.out.println("\n=== 3. 出現至少兩次 (>= 2) 的高頻單字 ===");
        List<String> frequentWords = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
            if (entry.getValue() >= 2) {
                frequentWords.add(entry.getKey());
                System.out.printf("單字: %-12s | 次數: %d 次\n", entry.getKey(), entry.getValue());
            }
        }

        if (frequentWords.isEmpty()) {
            System.out.println("無符合條件的單字。");
        }
    }
}