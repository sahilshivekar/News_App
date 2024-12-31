package com.learn.mynewsapp.di

import androidx.room.Room
import com.learn.mynewsapp.data.local.db.ArticleDao
import com.learn.mynewsapp.data.local.db.NewsDatabase
import com.learn.mynewsapp.data.local.db.NewsTypeConverter
import com.learn.mynewsapp.data.repository.LocalUserManagerImpl
import com.learn.mynewsapp.data.remote.NewsApi
import com.learn.mynewsapp.data.repository.NewsRepositoryImpl
import com.learn.mynewsapp.domain.repository.LocalUserManager
import com.learn.mynewsapp.domain.repository.NewsRepository
import com.learn.mynewsapp.domain.usecases.app_entry.AppEntryUseCases
import com.learn.mynewsapp.domain.usecases.app_entry.ReadAppEntry
import com.learn.mynewsapp.domain.usecases.app_entry.SaveAppEntry
import com.learn.mynewsapp.domain.usecases.news.DeleteArticle
import com.learn.mynewsapp.domain.usecases.news.GetLocalArticle
import com.learn.mynewsapp.domain.usecases.news.GetLocalArticles
import com.learn.mynewsapp.domain.usecases.news.GetNews
import com.learn.mynewsapp.domain.usecases.news.NewsUseCases
import com.learn.mynewsapp.domain.usecases.news.SearchNews
import com.learn.mynewsapp.domain.usecases.news.UpsertArticle
import com.learn.mynewsapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import android.app.Application as Application

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ): AppEntryUseCases =
        AppEntryUseCases(
            readAppEntry = ReadAppEntry(localUserManager),
            saveAppEntry = SaveAppEntry(localUserManager)
        )

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()


    @Provides
    @Singleton
    fun provideNewsApi(retrofit: Retrofit): NewsApi = retrofit.create(NewsApi::class.java)


    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi,
        articleDao: ArticleDao
    ): NewsRepository = NewsRepositoryImpl(newsApi, articleDao)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository
    ): NewsUseCases =
        NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            getLocalArticles = GetLocalArticles(newsRepository),
            getLocalArticle = GetLocalArticle(newsRepository)
        )

    @Provides
    @Singleton
    fun provideRoomDatabase(
        application: Application
    ): NewsDatabase =
        Room
            .databaseBuilder(
                context = application,
                klass = NewsDatabase::class.java,
                name = "news_db"
            )
            .addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .build()


    @Provides
    @Singleton
    fun provideArticleDao(
        newsDatabase: NewsDatabase
    ): ArticleDao {
        return newsDatabase.articleDao
    }
}