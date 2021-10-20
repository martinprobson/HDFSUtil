package net.martinprobson.hdfsutil.runner;

import net.martinprobson.hdfsutil.service.HdfsService;
import net.martinprobson.hdfsutil.service.HdfsServiceImpl;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class HdfsCommandLineRunner implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(HdfsCommandLineRunner.class);

    private final HdfsService hdfsService;

    public HdfsCommandLineRunner(HdfsService hdfsService) {
        this.hdfsService = hdfsService;
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.debug("Hello ");
        String res = hdfsService.readFile(new Path("/user/ubuntu/derby.log"));
        System.out.println(res);
        hdfsService.copyLocalFileToHDFS("a","b");
    }
}
