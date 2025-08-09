package com.ioffeivan.core.network.di

import com.ioffeivan.core.network.BuildConfig
import com.ioffeivan.core.network.interceptor.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModuleBase {

    @Provides
    fun provideBaseOkHttpClientBuilder(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
    }

    @Provides
    fun provideBaseRetrofitBuilder(
        converterFactory: Converter.Factory,
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BACKEND_BASE_URL)
            .addConverterFactory(converterFactory)
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
            .apply {
                if (BuildConfig.DEBUG) {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            }
    }

    @Singleton
    @Provides
    fun provideConverterFactory(json: Json): Converter.Factory {
        val contentType = "application/json".toMediaType()
        return json.asConverterFactory(contentType)
    }

    @Singleton
    @Provides
    fun provideJson() = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }
}

@InstallIn(SingletonComponent::class)
@Module
object NetworkModuleAuthorized {

    @Authorized
    @Singleton
    @Provides
    fun provideAuthorizedRetrofitBuilder(
        baseRetrofitBuilder: Retrofit.Builder,
        @Authorized client: OkHttpClient,
    ): Retrofit {
        return baseRetrofitBuilder
            .client(client)
            .build()
    }

    @Authorized
    @Singleton
    @Provides
    fun provideAuthorizedOkHttpClient(
        baseOkHttpBuilder: OkHttpClient.Builder,
        authInterceptor: AuthInterceptor,
    ): OkHttpClient {
        return baseOkHttpBuilder
            .addInterceptor(authInterceptor)
            .build()
    }
}

@InstallIn(SingletonComponent::class)
@Module
object NetworkModuleUnauthorized {

    @Unauthorized
    @Singleton
    @Provides
    fun provideUnauthorizedRetrofitBuilder(
        baseRetrofitBuilder: Retrofit.Builder,
        @Unauthorized client: OkHttpClient,
    ): Retrofit {
        return baseRetrofitBuilder
            .client(client)
            .build()
    }

    @Unauthorized
    @Singleton
    @Provides
    fun provideUnauthorizedOkHttpClient(
        baseOkHttpBuilder: OkHttpClient.Builder,
    ): OkHttpClient {
        return baseOkHttpBuilder
            .build()
    }
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Authorized

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Unauthorized