package be.linyang.kassa.Repository;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.stereotype.Repository;

@Repository
public class MongoRepo {
    final Morphia morphia = new Morphia();


    private void init()
    {
        // tell Morphia where to find your classes
        // can be called multiple times with different packages or classes
        morphia.mapPackage("be.linyang.kassa.Model");

        // create the Datastore connecting to the default port on the local host
        final Datastore datastore = morphia.createDatastore(new MongoClient(), "morphia_example");
        datastore.ensureIndexes();
    }
}
