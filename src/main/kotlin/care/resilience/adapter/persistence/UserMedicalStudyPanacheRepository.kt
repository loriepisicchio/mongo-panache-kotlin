package care.resilience.adapter.persistence

import care.resilience.adapter.persistence.entity.UserMedicalStudyEntity
import care.resilience.adapter.persistence.mapper.UserMedicalStudyEntityMapper.toEntity
import care.resilience.adapter.persistence.mapper.UserMedicalStudyEntityMapper.toModel
import care.resilience.core.domain.model.UserMedicalStudy
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
    override fun createUserMedicalStudy(userMedicalStudy: UserMedicalStudy): UserMedicalStudy {
        val entity = userMedicalStudy.toEntity()
        persist(entity)
        return entity.toModel()
    }

    @Transactional
    override fun updateUserMedicalStudy(userMedicalStudy: UserMedicalStudy): UserMedicalStudy {
        val updatedEntity = userMedicalStudy.toEntity()
        update(updatedEntity)
        return updatedEntity.toModel()
    }

    override fun findExistingUserMedicalStudy(
        patientRpmId: String,
        questionnaireTriggerId: UUID,
    ): UserMedicalStudy? =
        find("patientMongoId", patientRpmId, "questionnaireTriggerId", questionnaireTriggerId)
            .firstResult()
            ?.toModel()
}
