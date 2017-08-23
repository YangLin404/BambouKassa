package be.linyang.kassa.Repository.config;

import java.io.File;
import java.io.IOException;

import com.mongodb.Mongo;
import de.flapdoodle.embed.mongo.config.Storage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MongoConfig {

    @Bean(destroyMethod="close", name = "embeddedMongo")
    public Mongo mongo() throws IOException {

        String dbDir = System.getProperty("user.home") +
                File.separator +
                "mongodb";
        Storage replication = new Storage(dbDir,null,0);

        return new MyEmbeddedMongoBuilder()
                .replication(replication)
                .version("3.4.0")
                .bindIp("127.0.0.1")
                .port(27017)
                .build();
    }
}