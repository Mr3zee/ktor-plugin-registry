Use kotlinx.rpc integration with Ktor to create your services the Kotlin way. 

## Usage

First, create your `RPC` service and define some methods:
```kotlin
@Serializable
data class Data(val value: String)

interface SampleService : RPC {
    suspend fun hello(data: Data): String
}
```

In your server code define how to respond by simply implementing the service:

```kotlin
class SampleServiceImpl(override val coroutineContext: CoroutineContext) : SampleService {
    override suspend fun hello(data: Data): String {
        return "Server: ${data.value}"
    }
}
```

Then, define simple server routing:

```kotlin
install(RPC) {
    waitForServices = true
}

routing {
    rpc("/api") {
        rpcConfig {
            serialization {
                json()
            }
        }

        registerService<SampleService> { ctx -> SampleServiceImpl(ctx) }
    } 
}
```

To connect to the server, use the following client setup:

```kotlin
val rpcClient = HttpClient { installRPC() }.rpc {
    url("ws://localhost:8080/api")

    rpcConfig {
        serialization {
            json()
        }
    }
}

rpcClient.withService<SampleService>().hello(Data("Client"))
```

You can also check out our [first steps guide](https://ktor.io/docs/tutorial-first-steps-with-kotlin-rpc.html)
for a detailed explanation of the components.
