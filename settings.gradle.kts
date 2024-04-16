plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "poc-hexa-ddd-clean"
include("poc-domain")
include("poc-application")
include("poc-adapter-h2")
include("poc-adapter-event")
include("poc-port-person-api")
include("poc-infra")
include("poc-infra:sns-adapter")
findProject(":poc-infra:sns-adapter")?.name = "sns-adapter"
include("poc-infra:api-port")
findProject(":poc-infra:api-port")?.name = "api-port"
include("poc-infra:h2-adapter")
findProject(":poc-infra:h2-adapter")?.name = "h2-adapter"
