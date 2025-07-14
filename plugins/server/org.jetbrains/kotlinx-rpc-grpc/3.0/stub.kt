// DO NOT DELETE, not included in generation, but is used for code highlighting

class ClientGreeting {
    var name: String = ""

    companion object {
        operator fun invoke(body: ClientGreeting.() -> Unit): ClientGreeting = ClientGreeting().apply(body)
    }
}

class ServiceGreeting {
    var content: String = ""

    companion object {
        operator fun invoke(body: ServiceGreeting.() -> Unit): ServiceGreeting = ServiceGreeting().apply(body)
    }
}

interface SampleService {
    suspend fun greeting(name: ClientGreeting): ServiceGreeting
}
