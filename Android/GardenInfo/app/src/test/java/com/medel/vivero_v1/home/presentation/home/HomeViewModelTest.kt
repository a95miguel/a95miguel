package com.medel.vivero_v1.home.presentation.home

import com.medel.vivero_v1.home.data.repository.FakeHomeRepository
import com.medel.vivero_v1.home.domain.usecases.home.DeleteProductUseCase
import com.medel.vivero_v1.home.domain.usecases.home.GetProductUseCase
import com.medel.vivero_v1.home.domain.usecases.home.HomeUseCases
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest

import org.junit.Before
import org.junit.Test

class HomeViewModelTest {
    private lateinit var homeViewModel : HomeViewModel
    private lateinit var homeRepository : FakeHomeRepository

    private val dispatcher = StandardTestDispatcher()
    private val scope = TestScope(dispatcher)

    @Before
    fun setUp() {
        homeRepository = FakeHomeRepository()
        val useCase = HomeUseCases(
            getProductUseCase = GetProductUseCase(homeRepository),
            deleteProductUseCase = DeleteProductUseCase(homeRepository)
        )
        homeViewModel = HomeViewModel(useCase,dispatcher)
    }

    @Test
    fun `valid inital state is empty`(){
        val state = homeViewModel.state.value
        assertEquals(
            HomeState(
                products = emptyList(),
                expanded = false,
                fullImage = false,
                textSearch = "",
                showDialogExit = false,
                showDialogDelete = false,
                isLoading = true,
                isLoadingDelete = false,
                msgError = null,
                hasNavigated = false,
                delete = "",
                isChangeData = false
            ),
            state
        )
    }

    @Test
    fun `valid delete product`() = scope.runTest {
        homeViewModel.onEvent(HomeEvent.DeleteChange("1"))
        var state = homeViewModel.state.value
        assert(state.isLoadingDelete)

        advanceUntilIdle()

        state = homeViewModel.state.value

        assert(!state.isLoadingDelete)
        assert(!state.showDialogDelete)

    }

    @Test
    fun `valid delete but sever error`() = scope.runTest{
        homeRepository.fakeError = true
        homeViewModel.onEvent(HomeEvent.DeleteChange(""))
        var state = homeViewModel.state.value
        assert(state.isLoadingDelete)

        advanceUntilIdle()

        state = homeViewModel.state.value

        assert(!state.isLoading)
        assert(!state.isLoadingDelete)

        assertNotNull(state.msgError)
    }

}