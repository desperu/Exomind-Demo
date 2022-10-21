package org.desperu.exominddemo.di.module.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.desperu.exominddemo.BuildConfig
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Koin module which provides dependencies related to network.
 */
val networkModule = module {

    /**
     * Provides the logging interceptor.
     */
    single<Interceptor> {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        logging
    }

    /**
     * Provides the standard OkHttpClient,
     * with logging interceptor in Debug build config.
     */
    single {
        val builder = OkHttpClient.Builder()
        if(BuildConfig.DEBUG) {
            builder.addInterceptor(get() as Interceptor)
        }
        builder.build()
    }

    /**
     * Provides the Retrofit instance, for API response.
     */
    factory<Retrofit> { (baseUrl: String) ->
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(get() as OkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}