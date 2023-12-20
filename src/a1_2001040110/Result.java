package a1_2001040110;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Result implements Comparable<Result> {
    private Doc docResult;
    private List<Match> listMatches;


    private boolean isMarkWord(Word wordResult) {
        return isMatchedWord(wordResult, listMatches);
    }

    //  Return true if the word is a keyword
    private boolean isMatchedWord(Word wordResult, List<Match> matches) {
        for (Match match : matches) {
            if (match.getWord().equals(wordResult)) {
                return true;
            }
        }
        return false;
    }

    //  Compare based on criteria: match count, total freq, average firstIndex
    private String generateHtml(List<Word> wordList, Function<Word, String> outputWordFormatter) {
        return wordList.stream()
                .map(outputWordFormatter)
                .collect(Collectors.joining(" "));
    }


    //  Return the average firstIndex of all matches
    public double getAverageFirstIndex() {
        double totalIndex = 0;
        double countIndex = listMatches.size();

        if (countIndex == 0) {
            return 0;
        }

        for (Match listMatch : listMatches) {
            totalIndex += listMatch.getFirstIndex();
        }

        return totalIndex / countIndex;
    }

    //  Compare based on criteria: match count, total freq, average firstIndex
    public String htmlHighlight() {
        String titleHtmlOutput = generateHtml(docResult.getTitle(), this::formatTitleWord);
        //  Compare based on criteria: match count,
        String bodyHtmlOutput = generateHtml(docResult.getBody(), this::formatBodyWord);
        return String.format("<h3>%s</h3><p>%s</p>", titleHtmlOutput, bodyHtmlOutput);
    }

    //  Compare based on criteria: match count, total freq, average first index
    @Override
    public int compareTo(Result o) {
        int comparingResultOutput = compareResults(this, o);
        // Reverse the comparison result for descending order
        return -comparingResultOutput;
    }

    //  Compare based on criteria: match count, total freq, average firstIndex
    private int compareResults(Result result1, Result result2) {
        int compareSize = Integer.compare(result1.getMatches().size(), result2.getMatches().size());
        if (compareSize != 0) {
            return compareSize;
        }

        int compareTotalFrequency = Integer.compare(result1.getTotalFrequency(), result2.getTotalFrequency());
        if (compareTotalFrequency != 0) {
            return compareTotalFrequency;
        }

        return Double.compare(result1.getAverageFirstIndex(), result2.getAverageFirstIndex());
    }


    public Doc getDoc() {
        return docResult;
    }

    public List<Match> getMatches() {
        return listMatches;
    }

    //  Return the total frequency of all matches
    public int getTotalFrequency() {
        int totalFreq = 0;
        int indexFreq = 0;
        //  Return the total frequency of all matches
        while (indexFreq < listMatches.size()) {
            totalFreq += listMatches.get(indexFreq).getFreq();
            indexFreq++;
        }
        return totalFreq;
    }

    public Result(Doc d, List<Match> matches) {
        this.docResult = d;
        this.listMatches = matches;
    }

    //  Compare based on criteria: match count, total freq, average firstIndex
    private String formatBodyWord(Word wordFormat) {
        return isMarkWord(wordFormat)
                //  Compare based on criteria: match count, total freq, average firstIndex
                ? String.format("%s<b>%s</b>%s", wordFormat.getPrefix(), wordFormat.getText(), wordFormat.getSuffix())
                : wordFormat.toString();
    }

    //  Compare based on criteria: match count, total freq, average firstIndex
    private String formatTitleWord(Word wordFormat) {
        return isMarkWord(wordFormat)
                ? String.format("%s<u>%s</u>%s", wordFormat.getPrefix(), wordFormat.getText(), wordFormat.getSuffix())
                : wordFormat.toString();
    }



}
