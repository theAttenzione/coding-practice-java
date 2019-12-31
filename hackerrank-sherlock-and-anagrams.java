import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

/** https://www.hackerrank.com/challenges/sherlock-and-anagrams/problem */
public class Solution {

    private static class Position {
        private int start;
        private int end;
        public Position(int start, int end) {
            this.start = start;
            this.end = end;
        }
        public String toString() {
            return this.start + "-" + this.end;
        }
    }

    static int sherlockAndAnagrams(String s) {
        int numberOfAnagrams = 0;
        char[] chars = s.toCharArray();

        HashMap<String, Integer> substringsFrequency = new HashMap<String, Integer>();
        HashMap<String, Boolean> visitedPositions = new HashMap<String, Boolean>();

        for (int i = 0; i < chars.length - 1; i++) {
            for (int j = 1; j < chars.length - i; j++) {
                String substring = getSortedSymbols(chars, i, i + j);
                Position position = new Position(i, i + j - 1);
                Integer frequency = substringsFrequency.getOrDefault(substring, 0);
                visitedPositions.put(position.toString(), true);
                substringsFrequency.put(substring, frequency + 1);
            }
        }

        for (int i = chars.length - 1; i > 0; i--) {
            for (int j = 0; j < chars.length - (chars.length - i); j++) {
                String substring = getSortedSymbols(chars, i - j, i + 1);
                Position position = new Position(i - j, i);
                Integer frequency = substringsFrequency.getOrDefault(substring, 0);
                Boolean visited = visitedPositions.putIfAbsent(position.toString(), true);
                if (visited == null) {
                    substringsFrequency.put(substring, frequency + 1);
                }
            }
        }

        for (Integer frequency : substringsFrequency.values()) {
            if (frequency == 1) continue;
            int combinations = (frequency * (frequency - 1)) / 2;
            numberOfAnagrams += combinations;
        }

        return numberOfAnagrams;
    }

    private static String getSortedSymbols(char[] chars, int from, int to) {
        char[] subarray = Arrays.copyOfRange(chars, from, to);
        Arrays.sort(subarray);
        return Arrays.toString(subarray);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            int result = sherlockAndAnagrams(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
