import io.ktor.server.application.*
import kotlinx.rpc.transport.ktor.server.RPC

public fun Application.configureRouting() {
    install(RPC) {
        waitForServices = true
    }
}
