package care.resilience.adapter.persistence

import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import care.resilience.DatabaseResource
import care.resilience.IntegrationTestProfile
import care.resilience.adapter.persistence.entity.UserMedicalStudyEntity
import io.quarkus.test.common.QuarkusTestResource
import io.quarkus.test.junit.QuarkusTest
import io.quarkus.test.junit.TestProfile
import jakarta.inject.Inject
import jakarta.transaction.Transactional
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

@QuarkusTest
@QuarkusTestResource(DatabaseResource::class)
@TestProfile(IntegrationTestProfile::class)
class UserMedicalStudyPanacheRepositoryTest {
    @Inject
    private lateinit var repository: UserMedicalStudyPanacheRepository

    @BeforeEach
    @Transactional
    fun cleanDb() {
        repository.deleteAll()
    }

    @Test
    fun testCreate() {
        val userMedicalStudy =
            UserMedicalStudyEntity(
                patientMongoId = "patientRpmId",
                patientFirstName = "patientFirstName",
                patientLastName = "patientLastName",
                questionnaireName = "questionnaireName",
                questionnaireTriggerId = UUID.randomUUID().toString(),
            )
        val res = repository.createUserMedicalStudy(userMedicalStudy)

        assertThat(res).isNotNull()
        assertThat(res.id).isNotNull()
        assertThat(repository.count()).isEqualTo(1)

        val entities = repository.listAll()
        assertThat(entities).containsExactly(res)
    }
}
