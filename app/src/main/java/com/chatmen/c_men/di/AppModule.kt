package com.chatmen.c_men.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.chatmen.c_men.CMenDatabase
import com.chatmen.c_men.core.data.util.dispatcher_provider.DispatcherProvider
import com.chatmen.c_men.core.data.util.dispatcher_provider.DispatcherProviderImpl
import com.chatmen.c_men.core.util.Constants
import com.google.gson.Gson
import com.squareup.sqldelight.android.AndroidSqliteDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.websocket.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideSharedPreferences(app: Application): SharedPreferences =
        app.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideGson(): Gson = Gson()

    @Singleton
    @Provides
    fun provideHttpClient(sharedPreferences: SharedPreferences): HttpClient = HttpClient(CIO) {
        install(Logging) {
            level = LogLevel.ALL
        }

        install(JsonFeature) {
            serializer = GsonSerializer {
                setPrettyPrinting()
            }
        }
        install(WebSockets)

        defaultRequest {
            contentType(ContentType.Application.Json)
            val token = sharedPreferences.getString(Constants.JWT_TOKEN_KEY, "")
            header("Authorization", "bearer $token")
        }
    }

    @Provides
    fun provideSqlDelightDriver(app: Application): AndroidSqliteDriver = AndroidSqliteDriver(
        schema = CMenDatabase.Schema,
        context = app,
        name = Constants.DATABASE_NAME
    )

    @Singleton
    @Provides
    fun provideCMenDatabase(driver: AndroidSqliteDriver): CMenDatabase = CMenDatabase(driver)

    @Singleton
    @Provides
    fun provideDispatchers(): DispatcherProvider = DispatcherProviderImpl()
}