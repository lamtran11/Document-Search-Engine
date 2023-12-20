package a1_2001040110;

import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;
import java.nio.file.Files;

public class Engine {
    private Doc[] docList;

    // return the html representation of the htmlResult
    public String htmlResult(List<Result> htmlResult) {
        if (htmlResult == null) {
            return "";
        }
        StringBuilder htmlString = new StringBuilder();

        // return the html representation of the htmlResult
        for (Result htmlResultList : htmlResult) {
            htmlString.append(htmlResultList.htmlHighlight());
        }
        return htmlString.toString();
    }

    // load all docs from a directory
    public int loadDocs(String dirname) {
        try {
            //  return 0 if no docs loaded
            File filesFolder = new File(dirname);
            File[] listFiles = filesFolder.listFiles();

            // load all docs
            if (listFiles != null) {
                docList = new Doc[listFiles.length];

                // read all files
                for (int i = 0; i < docList.length; i++) {
                    docList[i] = new Doc(String.join("\n", Files.readAllLines(listFiles[i].toPath())));

                }
                return docList.length;
            }
            // return 0 if no docs loaded
        } catch (IOException e) {
            System.out.println("Error in loading file");
            e.printStackTrace();
        }
        return 0;
    }

    // search for the query in all docs
    public List<Result> search(Query q) {
        if (docList == null) {
            //  return an empty list if no docs loaded
            return Collections.emptyList();
        }
        // return a list of resultList
        List<Result> resultList = new ArrayList<>();

        for (Doc doc : docList) {
            //  return a list of matchList for each doc
            List<Match> matchList = q.matchAgainst(doc);

            //  continue if no matchList
            if (matchList.isEmpty()) continue;
            //  return a list of resultList for each doc
            resultList.add(new Result(doc, matchList));
        }
        //  sort the resultList by score
        resultList.sort(Comparator.naturalOrder());

        return resultList;
    }

    public Doc[] getDoc() {
        return docList;
    }


}


