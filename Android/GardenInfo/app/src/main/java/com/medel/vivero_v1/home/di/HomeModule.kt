package com.medel.vivero_v1.home.di

import android.content.Context
import androidx.room.Room
import com.medel.vivero_v1.home.data.local.ProductDao
import com.medel.vivero_v1.home.data.local.ProductDatabase
import com.medel.vivero_v1.home.data.repository.HomeRepositoryImpl
import com.medel.vivero_v1.home.domain.repository.HomeRepository
import com.medel.vivero_v1.home.domain.usecases.detail.CreateProductUseCase
import com.medel.vivero_v1.home.domain.usecases.detail.DetailUseCases
import com.medel.vivero_v1.home.domain.usecases.detail.GetProductByIdUseCase
import com.medel.vivero_v1.home.domain.usecases.detail.UpdateProductUseCase
import com.medel.vivero_v1.home.domain.usecases.detail.ValidateFormUseCase
import com.medel.vivero_v1.home.domain.usecases.home.DeleteProductUseCase
import com.medel.vivero_v1.home.domain.usecases.home.GetProductUseCase
import com.medel.vivero_v1.home.domain.usecases.home.HomeUseCases
import com.medel.vivero_v1.local.data.LocalRepositoryImpl
import com.medel.vivero_v1.local.domain.repository.LocalRepository
import com.medel.vivero_v1.local.domain.usecase.GetProductLocalUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {
    @Singleton
    @Provides
    fun provideHomeUseCase ( repository: HomeRepository ): HomeUseCases{
        return HomeUseCases(
            getProductUseCase = GetProductUseCase(repository),
            deleteProductUseCase = DeleteProductUseCase(repository)
        )
    }

    @Singleton
    @Provides
    fun provideDetailUseCase(repository: HomeRepository) : DetailUseCases{
        return DetailUseCases(
            createProductUseCase = CreateProductUseCase(repository),
            validateFormUseCase = ValidateFormUseCase(),
            getProductByIdUseCase = GetProductByIdUseCase(repository),
            updateProductUseCase = UpdateProductUseCase(repository)
        )
    }

    @Singleton
    @Provides
    fun provideLocalHomeUseCase(repository: LocalRepository):GetProductLocalUseCase{
        return GetProductLocalUseCase(repository)
    }

    //ROOM
    /**
     * Accede a la base de datos local de productos
     */
    @Singleton
    @Provides
    fun provideProductDao(@ApplicationContext context : Context): ProductDao{
        return Room.databaseBuilder(
            context,
            ProductDatabase :: class.java,
            "infoGarden"
        ).build().dao
    }


    @Singleton
    @Provides
    fun provideRepository(dao : ProductDao): HomeRepository{
        return HomeRepositoryImpl(dao)
    }

    @Singleton
    @Provides
    fun provideLocalRepository(dao : ProductDao) : LocalRepository{
        return LocalRepositoryImpl(dao)
    }

}