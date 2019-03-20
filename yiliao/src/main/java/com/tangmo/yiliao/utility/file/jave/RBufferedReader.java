package com.tangmo.yiliao.utility.file.jave;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

class RBufferedReader extends BufferedReader {
    private ArrayList lines = new ArrayList();

    public RBufferedReader(Reader in) {
        super(in);
    }

    public String readLine() throws IOException {
        return this.lines.size() > 0 ? (String)this.lines.remove(0) : super.readLine();
    }

    public void reinsertLine(String line) {
        this.lines.add(0, line);
    }
}
