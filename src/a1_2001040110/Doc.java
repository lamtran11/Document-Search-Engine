package a1_2001040110;

import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.List;
import java.util.Arrays;

public class Doc {
    private String contentInput;

    public static List<Word> generateWordFromRawString(String stringWord) {
        String[] wordArray = stringWord.split("\\s+");
        List<Word> wordsList = new ArrayList<>();

        Arrays.asList(wordArray).forEach(word -> wordsList.add(Word.createWord(word)));

        return wordsList;
    }

    public List<Word> getBody() {
        // Split the content into contentParts using newline character
        String[] contentParts = contentInput.split("\\n");

        // Get the body from the second part and create words from it
        return generateWordFromRawString(contentParts[1]);
    }

    public Doc(String contentInput) {
        this.contentInput = contentInput;
    }


    // Compare this snippet from src\a1_2001040110\Engine.java:
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        // Check if the object is an instance of Doc
        if (!(o instanceof Doc)) {
            return false;
        }

        Doc otherContent = (Doc) o;

        // Compare titles
        boolean detectHavingSameWordsTitle = compareContentWords(this.getTitle(), otherContent.getTitle());

        // Compare bodies
        boolean detectHavingSameWordsBody = compareContentWords(this.getBody(), otherContent.getBody());

        // Return true if the two docs have the same title and body
        return detectHavingSameWordsTitle && detectHavingSameWordsBody;
    }

    //
    private boolean compareContentWords(List<Word> content1, List<Word> content2) {
        // Compare the size of the two contents
        if (content1.size() != content2.size()) {
            return false;
        }

        // Compare the words of the two contents
        for (int i = 0; i < content1.size(); i++) {
            if (!content1.get(i).equals(content2.get(i))) {
                return false;
            }
        }

        return true;
    }

    public List<Word> getTitle() {
        // Split the content into contentParts using newline character
        String[] contentParts = contentInput.split("\\n");

        // Get the title from the first part and create words from it
        return generateWordFromRawString(contentParts[0]);
    }


}
