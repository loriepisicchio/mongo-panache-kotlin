package care.resilience.adapter.persistence.entity

import care.resilience.apilib.annotation.NoArgsConstructor
import io.quarkus.mongodb.panache.common.MongoEntity
import io.quarkus.mongodb.panache.kotlin.PanacheMongoEntity

@MongoEntity
@NoArgsConstructor
data class UserMedicalStudyEntity(
    // WARNING: all properties of the data class must be defined as var in order for the mongo driver to be able to handle those entities. Otherwise, all fields just end-up null
    val patientMongoId: String,
    val patientFirstName: String,
    val patientLastName: String,
    val questionnaireName: String,
    val questionnaireTriggerId: String,
) : PanacheMongoEntity()
