import io.ktor.server.application.Application
import kotlinx.rpc.grpc.ktor.server.grpc
import kotlinx.rpc.registerService

class SampleServiceImpl : SampleService {
    override suspend fun greeting(name: ClientGreeting): ServiceGreeting {
        return ServiceGreeting { content = "Hello, ${name.name}!" }
    }
}

fun Application.install() {
    grpc {
        registerService<SampleService> { SampleServiceImpl() }
    }
}
