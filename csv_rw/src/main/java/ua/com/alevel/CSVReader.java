package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CSVReader implements CSVReaderI {

    private final BufferedReader reader;
    private int numbOfColumns = -1;

    public CSVReader(Reader reader) {
        this.reader = new BufferedReader(reader);
    }

    @Override
    public List<String[]> readAll() throws IOException {
        List<String[]> list = new ArrayList<>();
        while (reader.ready()) {
            list.add(readNext());
        }
        return list;
    }

    @Override
    public String[] readNext() throws IOException {
        String line = reader.readLine();
        line = line.replace("\"", "");
        String[] splitLines = line.split(",");
        if (numbOfColumns == -1) {
            numbOfColumns = splitLines.length;
        }
        if (splitLines.length != numbOfColumns) {
            throw new RuntimeException("line = \"" + line + "\" isn't corresponded to header's line");
        }
        return splitLines;
    }
}
