package com.example.vivero.di

import android.app.Application
import androidx.room.Room
import com.example.vivero.data.local.ProductDao
import com.example.vivero.data.local.ProductDatabase
import com.example.vivero.data.remote.FirebaseDaoImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Intancias las depedencias para la aplicacion
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    /**
     * Instancia de FirebaseDatabase
     *
     * @return Instancia de FirebaseDatabase
     */
    @Singleton
    @Provides
    fun provideFirebaseDatabase() : FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    /**
     * Instancia de FirebaseAuth
     *
     * @return Instancia de FirebaseAuth
     */

    @Singleton
    @Provides
    fun provideFirebaseAuth() : FirebaseAuth{
        return FirebaseAuth.getInstance()
    }

    /**
     * Instancia de FirebaseStorage
     *
     * @return Instancia de FirebaseStorage
     */
    @Singleton
    @Provides
    fun povidefirebaseStorage () : FirebaseStorage{
        return FirebaseStorage.getInstance()
    }


    private const val DATABASE_NAME = "product_db"
    /**
     * Instacia de ProductDao para ROOM
     *
     * @param application La aplicacion de android
     *
     * @return Instancia de ProductDao
     */
    @Singleton
    @Provides
    fun provideRoomDao(application: Application) : ProductDao {
        val db = Room.databaseBuilder(application, ProductDatabase::class.java, DATABASE_NAME).build()
        return  db.dao
    }

    /**
     * Instancia de FirebaseDaoImpl, implementacion de fireabseDao
     * @param database Instancia de firebaseDatabase
     * @param firebaseAuth Instancia de firebaseAuth
     * @param store Instancia de FirebaseStorage
     *
     * @return Instancia de FirebaseDaoImpl
     */
    @Singleton
    @Provides
    fun provideFirebaseDao (database : FirebaseDatabase,firebaseAuth: FirebaseAuth,store : FirebaseStorage): FirebaseDaoImpl {
        return FirebaseDaoImpl(database,firebaseAuth,store)
    }

}