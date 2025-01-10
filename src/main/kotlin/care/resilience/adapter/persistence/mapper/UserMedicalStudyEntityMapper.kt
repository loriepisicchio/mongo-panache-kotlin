package care.resilience.adapter.persistence.mapper

import care.resilience.adapter.persistence.entity.UserMedicalStudyEntity
import care.resilience.core.domain.model.UserMedicalStudy
import dev.krud.shapeshift.ShapeShiftBuilder
import dev.krud.shapeshift.builder.MappingDefinitionBuilder
import dev.krud.shapeshift.transformer.base.MappingTransformer
import dev.krud.shapeshift.transformer.base.MappingTransformerContext
import org.bson.types.ObjectId
import java.util.*

object UserMedicalStudyEntityMapper {
    private val shapeShift =
        ShapeShiftBuilder()
            .withTransformer(ObjectIdTransformer())
            .withTransformer(ObjectIdStringTransformer())
            .withTransformer(QuestionnaireTriggerIdTransformer())
            .withTransformer(QuestionnaireTriggerIdStringTransformer())
            .withMapping(
                MappingDefinitionBuilder(UserMedicalStudy::class.java, UserMedicalStudyEntity::class.java)
                    .autoMap()
                    .mapField("questionnaireTriggerId", "questionnaireTriggerId")
                    .withTransformer(QuestionnaireTriggerIdTransformer::class.java)
                    .mapField("id", "id")
                    .withTransformer(ObjectIdStringTransformer::class.java)
                    .build(),
            ).withMapping(
                MappingDefinitionBuilder(UserMedicalStudyEntity::class.java, UserMedicalStudy::class.java)
                    .autoMap()
                    .mapField("questionnaireTriggerId", "questionnaireTriggerId")
                    .withTransformer(QuestionnaireTriggerIdStringTransformer::class.java)
                    .mapField("id", "id")
                    .withTransformer(ObjectIdTransformer::class.java)
                    .build(),
            ).build()

    fun UserMedicalStudy.toEntity() = shapeShift.map<UserMedicalStudy, UserMedicalStudyEntity>(this)

    fun UserMedicalStudyEntity.toModel() = shapeShift.map<UserMedicalStudyEntity, UserMedicalStudy>(this)
}

class ObjectIdTransformer : MappingTransformer<ObjectId, String> {
    override fun transform(context: MappingTransformerContext<out ObjectId>): String? = context.originalValue?.toString()
}

class ObjectIdStringTransformer : MappingTransformer<String, ObjectId> {
    override fun transform(context: MappingTransformerContext<out String>): ObjectId? = context.originalValue?.let { ObjectId(it) }
}

class QuestionnaireTriggerIdTransformer : MappingTransformer<UUID, String> {
    override fun transform(context: MappingTransformerContext<out UUID>): String? = context.originalValue?.toString()
}

class QuestionnaireTriggerIdStringTransformer : MappingTransformer<String, UUID> {
    override fun transform(context: MappingTransformerContext<out String>): UUID? = context.originalValue?.let { UUID.fromString(it) }
}
