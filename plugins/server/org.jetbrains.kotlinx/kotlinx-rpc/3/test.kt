import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.rpc.RPCClient
import kotlinx.rpc.serialization.json
import kotlinx.rpc.transport.ktor.client.RPC
import kotlinx.rpc.transport.ktor.client.rpc
import kotlinx.rpc.transport.ktor.client.rpcConfig
import kotlinx.rpc.withService
import kotlin.test.*

class ApplicationRpcTest {
    @Test
    fun testRpc() = testApplication {
        application {
            configureRouting()
        }

        val ktorClient = createClient {
            install(RPC)
        }

        val rpcClient = ktorClient.rpc("/api") {
            rpcConfig {
                serialization {
                    json()
                }
            }
        }

        val service = rpcClient.withService<SampleService>()

        val response = service.hello(Data("client"))

        assertEquals("Server: client", response)
    }
}
