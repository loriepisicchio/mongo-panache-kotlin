package care.resilience.core.domain.port.persistence

import care.resilience.adapter.persistence.entity.UserMedicalStudyEntity
import java.util.*

interface UserMedicalStudyRepositoryPort {
    fun createUserMedicalStudy(userMedicalStudy: UserMedicalStudyEntity): UserMedicalStudyEntity

    fun updateUserMedicalStudy(userMedicalStudy: UserMedicalStudyEntity): UserMedicalStudyEntity

    fun findExistingUserMedicalStudy(
        patientRpmId: String,
        questionnaireTriggerId: UUID,
    ): UserMedicalStudyEntity?
}
