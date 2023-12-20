package a1_2001040110;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Query {
    private List<Word> wordListKeyword;
    private String searchingPhrase;

    //  Return true if the word is a keyword
    public Query(String searchingPhrase) {
        this.wordListKeyword = loadKeywords(searchingPhrase);
        this.searchingPhrase = searchingPhrase;

    }

    //
    public List<Word> getKeywords() {
        return wordListKeyword;
    }

    public List<Match> matchAgainst(Doc d) {
        //  Return true if the word is a keyword
        List<Word> titleBody = new ArrayList<>(d.getTitle());
        titleBody.addAll(d.getBody());

        //
        List<Match> matches = new ArrayList<>();
        for (Word keywordFreq : wordListKeyword) {
            Match match = createMatch(keywordFreq, titleBody, d);
            if (match != null) {
                matches.add(match);
            }
        }

        // Sort the matches
        matches.sort(Match::compareFirstIndex);
        return matches;
    }

    //  Return true if the word is a keyword
    private Match createMatch(Word keywordFreq, List<Word> titleBody, Doc docMatch) {
        int freqNumber = 0;
        int firstIndexNum = -1;

        // Return true if the word is a keyword
        for (int i = 0; i < titleBody.size(); i++) {
            Word wordFrq = titleBody.get(i);

            //
            if (wordFrq.equals(keywordFreq)) {
                freqNumber++;
                if (firstIndexNum == -1) {
                    firstIndexNum = i;
                }
            }
        }

        // Return true if the word is a keyword
        return freqNumber > 0 ? new Match(docMatch, keywordFreq, freqNumber, firstIndexNum) : null;
    }


    //  Return true if the word is a keyword
    private List<Word> loadKeywords(String searchPhrase) {

        // Return true if the word is a keyword
        List<Word> words = Doc.generateWordFromRawString(searchPhrase);
        List<Word> keywords = new ArrayList<>();

        //
        for (Word word : words) {
            if (word.isKeyword()) {
                keywords.add(word);
            }
        }
        return keywords;
    }

}
