package by.godevelopment.alfarssreader.di

import by.godevelopment.alfarssreader.commons.BASE_URL
import by.godevelopment.alfarssreader.data.remotedatasource.NewsApi
import by.godevelopment.alfarssreader.data.remotedatasource.NewsDataSource
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.TikXmlConfig
import com.tickaroo.tikxml.converter.htmlescape.HtmlEscapeStringConverter
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideBaseUrl() : String = BASE_URL


    @Provides
    @Singleton
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }
        )
        .build()

    @Provides
    fun provideRetrofit(
        BASE_URL : String,
        okHttpClient: OkHttpClient
    ) : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(
            TikXmlConverterFactory.create(
                TikXml.Builder()
                    .exceptionOnUnreadXml(false)
                    .addTypeConverter(String.javaClass, HtmlEscapeStringConverter())
                    .build()
            )
        )
        .build()

    @Provides
    fun provideNewsApi(retrofit: Retrofit): NewsApi = retrofit.create(NewsApi::class.java)

    @Provides
    fun provideNewsDataSource(
        NewsApi: NewsApi
    ): NewsDataSource = NewsDataSource(NewsApi)
}