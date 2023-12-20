package a1_2001040110;

public class Match implements Comparable<Match> {
    private int freqNumber;
    private int firstIndex;
    private Doc docMatch;
    private Word wordMatch;

    private int secondIndex;

    //
    public Match(Doc d, Word w, int freq, int firstIndex) {
        this.freqNumber = freq;
        this.firstIndex = firstIndex;
        this.docMatch = d;
        this.wordMatch = w;

    }

    //
    public int getFreq() {
        return freqNumber;
    }

    //
    public int getFirstIndex() {
        return firstIndex;
    }

    public Word getWord() {
        return wordMatch;
    }

    @Override
    public int compareTo(Match o) {
        return compareFirstIndex(o);
    }

    public int getSecondIndex() {
        return secondIndex;
    }

    //  public int compareByFirstIndex
    public int compareFirstIndex(Match otherDoc) {
        if (otherDoc == null) {
            return 1; //
        }
        int firstIndexComparison = Integer.compare(this.getFirstIndex(), otherDoc.getFirstIndex());
        if (firstIndexComparison != 0) {
            return firstIndexComparison; // if first indices are not equal
        }

        return Integer.compare(this.getSecondIndex(), otherDoc.getSecondIndex());
    }
}


