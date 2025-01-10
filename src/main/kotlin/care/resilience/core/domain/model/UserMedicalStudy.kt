package care.resilience.core.domain.model

import care.resilience.apilib.annotation.NoArgsConstructor
import java.util.UUID

@NoArgsConstructor
data class UserMedicalStudy(
    val id: String? = null,
    val patientMongoId: String,
    val patientFirstName: String,
    val patientLastName: String,
    val questionnaireName: String,
    val questionnaireTriggerId: UUID,
)
