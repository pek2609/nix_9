package ua.com.alevel;

import java.io.IOException;
import java.util.List;

public interface CSVWriterI {

    void writeAll(List<String[]> lines) throws IOException;

    void writeNext(String[] line) throws IOException;
}
