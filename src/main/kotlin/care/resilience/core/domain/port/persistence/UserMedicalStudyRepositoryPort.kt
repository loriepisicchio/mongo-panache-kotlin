package care.resilience.core.domain.port.persistence

import care.resilience.core.domain.model.UserMedicalStudy
import java.util.*

interface UserMedicalStudyRepositoryPort {
    fun createUserMedicalStudy(userMedicalStudy: UserMedicalStudy): UserMedicalStudy

    fun updateUserMedicalStudy(userMedicalStudy: UserMedicalStudy): UserMedicalStudy

    fun findExistingUserMedicalStudy(
        patientRpmId: String,
        questionnaireTriggerId: UUID,
    ): UserMedicalStudy?
}
