//package piwords;
//import java.util.HashMap;
//import java.util.Map;
//
//public class AlphabetGenerator {
//    /**
//     * Given a numeric base, return a char[] that maps every digit that is
//     * representable in that base to a lower-case char.
//     *
//     * This method will try to weight each character of the alphabet
//     * proportional to their occurrence in words in a training set.
//     *
//     * This method should do the following to generate an alphabet:
//     *   1. Count the occurrence of each character a-z in trainingData.
//     *   2. Compute the probability of each character a-z by taking
//     *      (occurrence / total_num_characters).
//     *   3. The output generated in step (2) is a PDF of the characters in the
//     *      training set. Convert this PDF into a CDF for each character.
//     *   4. Multiply the CDF value of each character by the base we are
//     *      converting into.
//     *   5. For each index 0 <= i < base,
//     *      output[i] = (the first character whose CDF * base is > i)
//     *
//     * A concrete example:
//     * 	 0. Input = {"aaaaa..." (302 "a"s), "bbbbb..." (500 "b"s),
//     *               "ccccc..." (198 "c"s)}, base = 93
//     *   1. Count(a) = 302, Count(b) = 500, Count(c) = 198
//     *   2. Pr(a) = 302 / 1000 = .302, Pr(b) = 500 / 1000 = .5,
//     *      Pr(c) = 198 / 1000 = .198
//     *   3. CDF(a) = .302, CDF(b) = .802, CDF(c) = 1
//     *   4. CDF(a) * base = 28.086, CDF(b) * base = 74.586, CDF(c) * base = 93
//     *   5. Output = {"a", "a", ... (29 As, indexes 0-28),
//     *                "b", "b", ... (46 Bs, indexes 29-74),
//     *                "c", "c", ... (18 Cs, indexes 75-92)}
//     *
//     * The letters should occur in lexicographically ascending order in the
//     * returned array.
//     *   - {"a", "b", "c", "c", "d"} is a valid output.
//     *   - {"b", "c", "c", "d", "a"} is not.
//     *
//     * If base >= 0, the returned array should have length equal to the size of
//     * the base.
//     *
//     * If base < 0, return null.
//     *
//     * If a String of trainingData has any characters outside the range a-z,
//     * ignore those characters and continue.
//     *
//     * @param base A numeric base to get an alphabet for.
//     * @param trainingData The training data from which to generate frequency
//     *                     counts. This array is not mutated.
//     * @return A char[] that maps every digit of the base to a char that the
//     *         digit should be translated into.
//     */
//    public static char[] generateFrequencyAlphabet(int base,
//                                                   String[] trainingData) {
//        char[] BASIC_ALPHABET =
//                {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
//                        'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
//        Map<Character, Integer> freq = new HashMap<Character, Integer>();
//        for (char c : BASIC_ALPHABET){
//            freq.put(c,0);
//        }
//        int total = 0;
//        for (String s : trainingData){
//            if (s != "" ){
//                Integer count;
//                for (int i = 0; i < s.length(); i++) {
//                    if(s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
//                        count = freq.get(s.charAt(i));
//                        total++;
//                        freq.put(s.charAt(i), count + 1);
//                    }
//                }
//            }
//        }
//
//        double totalProb = 0;
//        char[] ans = new char[base];
//        int runningCounter = 0;
//        double currentStanding = 0;
//        for (char c : freq.keySet()){
//            currentStanding += Double.valueOf(freq.get(c));
//            totalProb = currentStanding * base /Double.valueOf(total);
//            for (; runningCounter < totalProb && runningCounter <
//                    base; runningCounter++){
//                ans[runningCounter] = c;
//            }
//        }
//        return ans;
//    }
//}

package piwords;

import java.util.HashMap;
import java.util.Map;

public class AlphabetGenerator {
    /**
     * Given a numeric base, return a char[] that maps every digit that is
     * representable in that base to a lower-case char.
     *
     * This method will try to weight each character of the alphabet
     * proportional to their occurrence in words in a training set.
     *
     * This method should do the following to generate an alphabet:
     *   1. Count the occurrence of each character a-z in trainingData.
     *   2. Compute the probability of each character a-z by taking
     *      (occurrence / total_num_characters).
     *   3. The output generated in step (2) is a PDF of the characters in the
     *      training set. Convert this PDF into a CDF for each character.
     *   4. Multiply the CDF value of each character by the base we are
     *      converting into.
     *   5. For each index 0 <= i < base,
     *      output[i] = (the first character whose CDF * base is > i)
     *
     * A concrete example:
     * 	 0. Input = {"aaaaa..." (302 "a"s), "bbbbb..." (500 "b"s),
     *               "ccccc..." (198 "c"s)}, base = 93
     *   1. Count(a) = 302, Count(b) = 500, Count(c) = 198
     *   2. Pr(a) = 302 / 1000 = .302, Pr(b) = 500 / 1000 = .5,
     *      Pr(c) = 198 / 1000 = .198
     *   3. CDF(a) = .302, CDF(b) = .802, CDF(c) = 1
     *   4. CDF(a) * base = 28.086, CDF(b) * base = 74.586, CDF(c) * base = 93
     *   5. Output = {"a", "a", ... (29 As, indexes 0-28),
     *                "b", "b", ... (46 Bs, indexes 29-74),
     *                "c", "c", ... (18 Cs, indexes 75-92)}
     *
     * The letters should occur in lexicographically ascending order in the
     * returned array.
     *   - {"a", "b", "c", "c", "d"} is a valid output.
     *   - {"b", "c", "c", "d", "a"} is not.
     *
     * If base >= 0, the returned array should have length equal to the size of
     * the base.
     *
     * If base < 0, return null.
     *
     * If a String of trainingData has any characters outside the range a-z,
     * ignore those characters and continue.
     *
     * @param base A numeric base to get an alphabet for.
     * @param trainingData The training data from which to generate frequency
     *                     counts. This array is not mutated.
     * @return A char[] that maps every digit of the base to a char that the
     *         digit should be translated into.
     */
    public static char[] generateFrequencyAlphabet(int base,
                                                   String[] trainingData) {
        if (base <0){
            return null;
        }
        //create hashmap for key: character and value: counter
        char[] BASIC_ALPHABET =
                {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                        'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        Map< Character, Integer> dict = new HashMap<Character, Integer>();
        for ( int i = 0; i < 26; i ++){
            dict.put(BASIC_ALPHABET[i],0 );
        }
        int total = 0;
        for (int j = 0; j < trainingData.length; j ++){
            for (int c =0; c < trainingData[j].length(); c ++ ){
                if (trainingData[j].charAt(c)>= 'a' && trainingData[j].charAt(c) <= 'z'){
                    //get the current counter
                    int counter = dict.get(trainingData[j].charAt(c));


                    dict.put(trainingData[j].charAt(c), counter + 1);
                    //create another counter to find total
                    total += 1;
                }
            }
        }

        double cdf  = 0;
        int count = 0;
        char [] answer = new char[base];
        for ( char c : dict.keySet()){
            cdf += Double.valueOf(dict.get(c));
            double prob = cdf/Double.valueOf(total);
//            cdf += prob;
            double mult = prob*base;
            for (; count < mult; count ++)// for (intialise counter; boolean comparison; increment), for(; counter<mult; counter++)
                //you dont want the counter to rest with every new letter
                answer[count] = c;
        }
        //unlike python, dont need to convert boolean numbers dont need to be int


        return answer;
    }
}