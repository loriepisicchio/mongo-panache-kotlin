package care.resilience

import io.quarkus.test.junit.QuarkusTestProfile

class IntegrationTestProfile : QuarkusTestProfile {
    override fun getConfigProfile(): String = "integrationTest"
}
