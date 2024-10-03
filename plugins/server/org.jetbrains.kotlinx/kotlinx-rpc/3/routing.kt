import io.ktor.server.routing.*
import kotlinx.rpc.serialization.json
import kotlinx.rpc.transport.ktor.server.rpc

public fun Routing.configureRpcRouting() {
    rpc("/api") {
        rpcConfig {
            serialization {
                json()
            }
        }

        registerService<SampleService> { ctx -> SampleServiceImpl(ctx) }
    }
}
