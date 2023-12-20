package a1_2001040110;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;
import java.io.BufferedReader;
import java.io.FileReader;

public class Word {
    // Compare this snippet from src\a1_2001040110\Word.java:
    public static Set<String> stopWords = new HashSet<>();
    private String rawTextInput;

    private Word(String rawTextInput) {
        this.rawTextInput = rawTextInput;
    }


    // Compare this snippet from src\a1_2001040110\Word.java:
    private int getIndexOfFirstChar() {

        //
        return IntStream.range(0, rawTextInput.length())
                .filter(i -> Character.isAlphabetic(rawTextInput.charAt(i)))
                .findFirst()
                .orElse(0);
    }

    public static boolean loadStopWords(String getFileName) {
        try {
            // Read the stop words from the file and add them to the set
            BufferedReader bufferedReader = new BufferedReader(new FileReader(getFileName));
            //
            bufferedReader.lines().forEach(stopWords::add);
            return true;
            //  return true if the file is loaded successfully
        } catch (IOException e) {
            return false;
        }
    }

    // Compare this snippet from src\a1_2001040110\Word.java:
    @Override
    public boolean equals(Object equalObject) {
        if (equalObject instanceof Word) {
            String otherWordInput = ((Word) equalObject).getText();
            // Compare the text of the word with the text of the other word
            return checkEqual(this.getText(), otherWordInput);
        }
        return false;
    }

    public String getSuffix() {

        if (rawTextInput.endsWith("'s")) {
            return "'s";
        }

        //Check if it is invalid word
        if (!checkValidWord()) {
            return "";
        }

        // Find the index of the last alphabet character
        int indexLastChar = findLastCharIndex(rawTextInput);

        return rawTextInput.substring(indexLastChar + 1);
    }

    // Compare this s
    private int findLastCharIndex(String input) {
        int indexLastChar = -1;
        for (int i = 0; i < input.length(); i++) {
            if (Character.isAlphabetic(input.charAt(i))) {
                indexLastChar = i;
            }
        }
        return indexLastChar;
    }

    private boolean checkValidWord() {
        // Check if the word matches the regexString pattern
        String regexString = "\\W*[a-zA-Z]+([-'][a-zA-Z]+)*\\W*";
        return rawTextInput.matches(regexString);
    }

    public String getPrefix() {

        if (checkValidWord()) {
            int indexFirstChar = getIndexOfFirstChar();

            return extractTheText(0, indexFirstChar);
        } else {
            return "";
        }
    }

    private String extractTheText(int prefixLength, int i) {
        return rawTextInput.substring(prefixLength, i);
    }

    private int getPrefixLength() {
        // If the word starts with 's, then the prefix is 's

        return getPrefix().length();
    }

    private int getSuffixLength() {
        // If the word ends with 's, then the suffix is 's
        return getSuffix().length();
    }

    public String getText() {

        int prefixLength = getPrefixLength();
        // check if the word is a stop word
        int suffixLength = getSuffixLength();

        // Extract the text without prefix and suffix
        StringBuilder sb = new StringBuilder();
        for (int i = prefixLength; i < rawTextInput.length() - suffixLength; i++) {
            sb.append(rawTextInput.charAt(i));
        }
        return sb.toString();
    }

    public static Word createWord(String rawTextInput) {
        return new Word(rawTextInput);
    }

    public boolean isKeyword() {
        return checkValidWord() && isNotStopWord(rawTextInput);
    }

    private boolean isNotStopWord(String rawTextInput) {
        return !stopWords.contains(rawTextInput.toLowerCase()) && !rawTextInput.contains(" ");
    }

    public boolean checkEqual(String str1, String str2) {
        return str1.equalsIgnoreCase(str2);
    }

    public boolean checkEqual(String otherWord) {
        return this.getText().equalsIgnoreCase(otherWord);
    }
    //
    @Override
    public String toString() {
        return rawTextInput;
    }
}
