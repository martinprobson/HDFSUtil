package net.martinprobson.hdfsutil.service;

import org.apache.hadoop.fs.Path;

import java.io.IOException;

public interface HdfsService {
    void copyLocalFileToHDFS(String source, String destination) throws IOException;

    String readFile(Path fileName);
}
