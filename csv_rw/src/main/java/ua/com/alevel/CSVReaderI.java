package ua.com.alevel;

import java.io.IOException;
import java.util.List;

public interface CSVReaderI {

    List<String[]> readAll() throws IOException;

    String[] readNext() throws IOException;
}
