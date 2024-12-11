package com.mathias8dev.hellocmp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.mathias8dev.hellocmp.domain.utils.toTypedData
import dev.icerock.moko.parcelize.Parcelable
import dev.icerock.moko.parcelize.Parcelize
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import koinInject
import kotlinx.serialization.Serializable
import org.koin.core.qualifier.named


@Serializable
@Parcelize
data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
) : Parcelable

class HomeScreen : Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.current
        val httpClient: HttpClient = koinInject(qualifier = named("DefaultClient"))

        LaunchedEffect(httpClient) {
            Napier.d { "Hello world" }
            val updatedClient = httpClient.config {
                defaultRequest {
                    url("https://jsonplaceholder.typicode.com")
                }
            }

            val response = updatedClient.get("posts")
            val bodyString = response.bodyAsText()
            val responseObj = bodyString.toTypedData<List<Post>>()
            Napier.d { responseObj.toString() }
        }

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text("Hello home")
            Button(
                onClick = {
                    navigator?.push(PostListScreen())
                }
            ) {
                Text("Navigate to PostListScreen")
            }
        }
    }

}


