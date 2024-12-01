package vn.kietnguyendev.tymexhometest.data.di

import com.vipulasri.kachetor.KachetorStorage
import io.ktor.client.HttpClient
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import vn.kietnguyendev.tymexhometest.data.network.IUserApi
import vn.kietnguyendev.tymexhometest.data.network.UserApi
import vn.kietnguyendev.tymexhometest.data.repository.UserRepositoryImpl
import vn.kietnguyendev.tymexhometest.domain.repository.UserRepository

val dataModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(json = Json { ignoreUnknownKeys = true }, contentType = ContentType.Any)
            }
            install(HttpCache) {
                publicStorage(KachetorStorage(10 * 1024 * 1024))
            }
        }
    }

    single<IUserApi> {
        UserApi(get())
    }

    single<UserRepository> {
        UserRepositoryImpl(get())
    }
}