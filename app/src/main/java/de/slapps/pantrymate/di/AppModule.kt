package de.slapps.pantrymate.di

import de.slapps.pantrymate.data.PantryRepository
import de.slapps.pantrymate.data.UserRepository
import de.slapps.pantrymate.data.local.AppDataStore
import de.slapps.pantrymate.data.remote.AuthInterceptor
import de.slapps.pantrymate.data.remote.PantryService
import de.slapps.pantrymate.data.remote.UserService
import de.slapps.pantrymate.ui.viewmodels.ItemDetailsViewModel
import de.slapps.pantrymate.ui.viewmodels.LoginViewModel
import de.slapps.pantrymate.ui.viewmodels.PantryContentViewModel
import de.slapps.pantrymate.ui.viewmodels.PantrySelectionViewModel
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

val appModule = module {
    singleOf(::UserRepository)
    singleOf(::PantryRepository)

    viewModelOf(::LoginViewModel)
    viewModelOf(::PantrySelectionViewModel)
    viewModelOf(::PantryContentViewModel)
    viewModelOf(::ItemDetailsViewModel)

    single { AppDataStore(androidContext()) }
    single { AuthInterceptor(get()) }
    single { getOkHttp(get()) }
    single { getRetrofit(get()) }
    single { get<Retrofit>().create(UserService::class.java) }
    single { get<Retrofit>().create(PantryService::class.java) }
}

private fun getOkHttp(authInterceptor: AuthInterceptor): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    return OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()
}

private fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
    val json = Json {
        ignoreUnknownKeys = true
    }
    return Retrofit.Builder()
        .baseUrl("https://your.base.url/api/") // TODO: Replace with your base URL
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()
}
