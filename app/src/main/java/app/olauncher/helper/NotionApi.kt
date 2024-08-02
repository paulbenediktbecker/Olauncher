package app.olauncher.helper
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import kotlinx.serialization.Serializable
import io.ktor.serialization.kotlinx.json.*

fun main() = runBlocking {
    val dbId = 1234
    val text = "naaa"
    val status = "high"
    val url = "https://api.notion.com/v1/pages" // Ensure this endpoint is correct
    val token = "DFGH"

    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }

    val data = mapOf(
        "parent" to mapOf("database_id" to dbId),
        "properties" to mapOf(
            "Task" to mapOf(
                "title" to listOf(
                    mapOf(
                        "text" to mapOf(
                            "content" to text
                        )
                    )
                )
            ),
            "Kategorie" to mapOf(
                "select" to mapOf(
                    "name" to status
                )
            )
        )
    )

    val response: HttpResponse = client.post(url) {
        headers {
            append(HttpHeaders.Authorization, "Bearer $token")
            append(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            append("Notion-Version", "2021-05-13")
        }
        contentType(ContentType.Application.Json)
        setBody(data)
    }

    println(response.status)
    println(response.bodyAsText())

    client.close()
}
