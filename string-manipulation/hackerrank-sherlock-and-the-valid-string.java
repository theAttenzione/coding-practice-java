import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

/** https://www.hackerrank.com/challenges/sherlock-and-valid-string/problem */
public class Solution {

    static String isValid(String s) {
        char[] chars = s.toCharArray();
        HashMap<Character, Integer> charFrequency = new HashMap<Character, Integer>();

        for (int i = 0; i < chars.length; i++) {
            charFrequency.put(
                chars[i],
                charFrequency.getOrDefault(chars[i], 0) + 1
            );
        }

        Integer targetFrequency = null;
        Integer deletions = 0;
        ArrayList<Integer> frequencies = new ArrayList<Integer>(charFrequency.values());
        for (int i = 0; i < frequencies.size(); i++) {
            int frequency = frequencies.get(i);
            if (targetFrequency == null) {
                targetFrequency = frequency;
                continue;
            }
            if ((i == 1 && targetFrequency - frequency == 1) || (targetFrequency == 1 && frequency != 1)) {
                targetFrequency = frequency;
                deletions++;
                continue;
            }

            if (targetFrequency != 1 && frequency == 1) {
                deletions++;
                continue;
            }
            if (targetFrequency != frequency) {
                deletions += Math.abs(targetFrequency - frequency);
                continue;
            }
        }

        return deletions <= 1 ? "YES" : "NO";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        String result = isValid(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
