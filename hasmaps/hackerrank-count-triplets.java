import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/** https://www.hackerrank.com/challenges/count-triplets-1/problem */
public class Solution {

    static long countTriplets(List<Long> arr, long r) {
        long triplets = 0;

        HashMap<Long, Long> highElems = new HashMap<Long, Long>();
        HashMap<Long, Long> midHighCombinations = new HashMap<Long, Long>();

        for (int i = arr.size() - 3; i >= 0; i--) {
            long currHigh = arr.get(i + 2);
            highElems.put(
                currHigh,
                highElems.getOrDefault(currHigh, 0L) + 1L
            );

            long currMid = arr.get(i + 1);
            long currCombinations = midHighCombinations.getOrDefault(currMid, 0L);
            long newCombinations = highElems.getOrDefault(currMid * r, 0L);
            midHighCombinations.put(
                currMid,
                currCombinations + newCombinations
            );

            long currLow = arr.get(i);
            long currFullCombinations = midHighCombinations.getOrDefault(
                currLow * r,
                0L
            );
            triplets += currFullCombinations;
        }

        return triplets;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Long::parseLong)
            .collect(toList());

        long ans = countTriplets(arr, r);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

