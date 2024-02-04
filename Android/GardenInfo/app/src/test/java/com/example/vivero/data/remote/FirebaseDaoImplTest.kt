package com.example.vivero.data.remote

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class FirebaseDaoImplTest {
    @MockK
    private lateinit var firebaseDatabase: FirebaseDatabase

    @MockK
    private lateinit var firebaseAuth: FirebaseAuth

    @MockK
    private lateinit var firebaseStorage: FirebaseStorage

    @MockK
    private lateinit var firebaseDaoImpl: FirebaseDaoImpl

    @Before
    fun onBefore() {
        // Inicializamos la anotación MockkK
        MockKAnnotations.init(this)
        firebaseDaoImpl = FirebaseDaoImpl(firebaseDatabase, firebaseAuth, firebaseStorage)
        every { firebaseDatabase.getReference("Plantas") } returns mockk()
    }

    @After
    fun onAfter() {
        // Limpias todas las instancias
        unmockkAll()
    }

    @Test
    fun `Iniciar sesión de forma anónima`() {
        // Given
        coEvery { firebaseAuth.signInAnonymously().await().user } returns mockk()

        // When
        val result = runBlocking { firebaseDaoImpl.signInAnonymously() }

        // Then
        assertTrue(result != null)
    }
}
