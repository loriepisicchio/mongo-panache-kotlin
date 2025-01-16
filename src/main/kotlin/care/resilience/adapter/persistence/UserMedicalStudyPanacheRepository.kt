package care.resilience.adapter.persistence

import care.resilience.adapter.persistence.entity.UserMedicalStudyEntity
import care.resilience.core.domain.port.persistence.UserMedicalStudyRepositoryPort
import io.quarkus.mongodb.panache.kotlin.PanacheMongoRepository
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import java.util.*

@ApplicationScoped
class UserMedicalStudyPanacheRepository :
    PanacheMongoRepository<UserMedicalStudyEntity>,
    UserMedicalStudyRepositoryPort {
    @Transactional
    override fun createUserMedicalStudy(userMedicalStudy: UserMedicalStudyEntity): UserMedicalStudyEntity {
        persist(userMedicalStudy)
        return userMedicalStudy
    }

    @Transactional
    override fun updateUserMedicalStudy(userMedicalStudy: UserMedicalStudyEntity): UserMedicalStudyEntity {
        update(userMedicalStudy)
        return userMedicalStudy
    }

    override fun findExistingUserMedicalStudy(
        patientRpmId: String,
        questionnaireTriggerId: UUID,
    ): UserMedicalStudyEntity? =
        find("patientMongoId", patientRpmId, "questionnaireTriggerId", questionnaireTriggerId)
            .firstResult()
}
