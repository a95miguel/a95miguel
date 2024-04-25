package com.medel.vivero_v1.authentication.presentation

import com.medel.vivero_v1.authentication.data.repository.FakeAuthenticationRepository
import com.medel.vivero_v1.authentication.domain.usecase.LoginUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class LoginViewModelTest {
    private lateinit var loginViewModel : LoginViewModel
    private lateinit var authenticationRepository : FakeAuthenticationRepository

    private val dispacher = StandardTestDispatcher()
    private val scope = TestScope(dispacher)

    @Before
    fun setUp() {
        authenticationRepository = FakeAuthenticationRepository()
        val useCase = LoginUseCase(authenticationRepository)
        loginViewModel = LoginViewModel(useCase,dispacher)
    }

    /**
     * Valida los estados inicial
     */
    @Test
    fun `valid unital state` (){
        val state  = loginViewModel.state
        assertEquals(
            LoginState(
                isLoggedIn = false,
                isLoading = false
            ),
            state
        )
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `valid login` ()= scope.runTest{
        loginViewModel.onEvent(LoginEvent.Login)
        var state  = loginViewModel.state
        assert(state.isLoading)

        advanceUntilIdle() //Avanza las coroutinas

        state = loginViewModel.state

        assert(state.isLoggedIn)
        assert(!state.isLoading)
    }
}