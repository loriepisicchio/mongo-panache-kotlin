package care.resilience

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager
import org.testcontainers.containers.MongoDBContainer
import java.util.*

class DatabaseResource : QuarkusTestResourceLifecycleManager {
    private val database =
        MongoDBContainer("mongo")
            .withExposedPorts(27017)

    override fun start(): Map<String?, String?> {
        database.start()
        return mapOf(
            Pair("quarkus.mongodb.connection-string", database.connectionString),
            Pair("quarkus.mongodb.database", "omapi"),
        )
    }

    override fun stop() {
        database.stop()
    }
}
