package net.martinprobson.hdfsutil.service;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import net.martinprobson.hdfsutil.config.HdfsConfiguration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Optional;


@Service
public class HdfsServiceImpl implements HdfsService {

    private static final Logger LOG = LoggerFactory.getLogger(HdfsServiceImpl.class);

    private Optional<FileSystem> fs = Optional.empty();

    private final HdfsConfiguration hdfsConfiguration;

    public HdfsServiceImpl(HdfsConfiguration hdfsConfiguration) {
        this.hdfsConfiguration = hdfsConfiguration;
    }

    private FileSystem getFs() throws IOException {
        if (!fs.isPresent()) {
            org.apache.hadoop.conf.Configuration conf = hdfsConfiguration.getHadoopConfiguration();
            if (hdfsConfiguration.getHdfsFileSystem().equals(hdfsConfiguration.LOCAL_FILE_SYSTEM)) {
                fs = Optional.of(FileSystem.getLocal(conf));
            } else {
                fs = Optional.of(FileSystem.get(conf));
            }
        }
        return fs.get();
    }

    @Override
    public void copyLocalFileToHDFS(String source, String destination) throws IOException {
        LOG.debug("In copyLocalFileToHDFS config = " + hdfsConfiguration.getHdfsFileSystem());
    }

    @Override
    public String readFile(Path fileName) {
        StringBuilder line = new StringBuilder();
        try {
            FSDataInputStream in = getFs().open(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(in, Charset.defaultCharset()));
            line = new StringBuilder();
            String tmp;
            while ((tmp = br.readLine()) != null)
                line.append(tmp + "\n");
            br.close();
        } catch (IOException e) {
            LOG.error("Error reading file: " + fileName, e);
            System.exit(2);
        }
        return (line.toString());
    }
}
