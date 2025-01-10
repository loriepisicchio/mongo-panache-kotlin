package care.resilience.apilib.annotation

@MustBeDocumented
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class NoArgsConstructor
// Annotation used to generate an additional zero-argument constructor for classes
// The generated constructor is synthetic, so it can't be directly called from Java or Kotlin, but it can be called using reflection (useful for shapeshift for instance)

/*
 How to use it ?
 ---------------
 - Annotate your class with this annotation
 - Add kotlin("plugin.noarg") in build.gradle
 - Add configuration in build.gradle
   noArg {
      annotation("care.resilience.apilib.annotation.NoArgsConstructor")
   }
 */
