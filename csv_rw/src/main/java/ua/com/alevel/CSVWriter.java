package ua.com.alevel;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class CSVWriter implements CSVWriterI {

    private Writer writer;
    private int numOfColumns = -1;

    public CSVWriter(Writer writer) {
        this.writer = writer;
    }

    @Override
    public void writeAll(List<String[]> lines) throws IOException {
        StringBuilder sb = new StringBuilder();
        if (numOfColumns == -1) {
            numOfColumns = lines.get(0).length;
        }
        for (String[] line : lines) {
            boolean isWritten = writeLineToStringBuilder(line, sb);
            if(!isWritten){
                break;
            }
        }
        writer.write(sb.toString());
    }

    @Override
    public void writeNext(String[] line) throws IOException {
        StringBuilder sb = new StringBuilder();
        if (numOfColumns == -1) {
            numOfColumns = line.length;
        }
        if(numOfColumns == line.length){
            writeLineToStringBuilder(line, sb);
        }
        writer.write(sb.toString());
    }

    private boolean writeLineToStringBuilder(String[] line, StringBuilder stringBuilder) throws IOException {
        if (line.length == numOfColumns) {
            for (int i = 0; i < line.length; i++) {
                stringBuilder.append("\"").append(line[i]).append("\"");
                stringBuilder.append(i == line.length - 1 ? "\n" : ",");
            }
            return true;
        }
        return false;
    }


}
