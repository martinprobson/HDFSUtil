package net.martinprobson.hdfsutil.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HdfsConfiguration {

    public final String LOCAL_FILE_SYSTEM = "file:///";

    @Value("${hdfs.filesystem}")
    private String hdfsFileSystem;

    public String getHdfsFileSystem() {
        return hdfsFileSystem;
    }

    @Bean
    public org.apache.hadoop.conf.Configuration getHadoopConfiguration() {
        org.apache.hadoop.conf.Configuration conf = new org.apache.hadoop.conf.Configuration();
        conf.addResource("core-site.xml");
        conf.addResource("hdfs-site.xml");
        return conf;
    }
}
