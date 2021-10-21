package net.martinprobson.hdfsutil.runner;

import net.martinprobson.hdfsutil.service.HdfsService;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class HdfsCommandLineRunner implements CommandLineRunner {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(HdfsCommandLineRunner.class);

    private final HdfsService hdfsService;

    public HdfsCommandLineRunner(HdfsService hdfsService) {
        this.hdfsService = hdfsService;
    }

    @Override
    public void run(String... args) throws Exception {
        String res = hdfsService.readFile(new Path("/Users/martin.robson/Documents/GitHub/HDFSUtil/README.md"));
        System.out.println(res);
        hdfsService.copyLocalFileToHDFS("a","b");
    }
}
