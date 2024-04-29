package com.medel.vivero_v1.home.presentation.detail

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import com.medel.vivero_v1.home.data.repository.FakeHomeRepository
import com.medel.vivero_v1.home.domain.usecases.detail.CreateProductUseCase
import com.medel.vivero_v1.home.domain.usecases.detail.DetailUseCases
import com.medel.vivero_v1.home.domain.usecases.detail.GetProductByIdUseCase
import com.medel.vivero_v1.home.domain.usecases.detail.UpdateProductUseCase
import com.medel.vivero_v1.home.domain.usecases.detail.ValidateFormUseCase
import com.medel.vivero_v1.home.presentation.detail.presentation.DetailViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull

import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DetailViewModelTest {
    private  lateinit var detailViewModelTest: DetailViewModel
    private lateinit var homeRepository : FakeHomeRepository


    private val dispatcher = StandardTestDispatcher()
    private val scope = TestScope(dispatcher)

    @Before
    fun setUp() {
        homeRepository = FakeHomeRepository()
        val useCase = DetailUseCases(
            createProductUseCase = CreateProductUseCase(homeRepository),
            validateFormUseCase = ValidateFormUseCase(),
            getProductByIdUseCase = GetProductByIdUseCase(homeRepository),
            updateProductUseCase = UpdateProductUseCase(homeRepository)
        )
        val savedStateHandle = SavedStateHandle()

        detailViewModelTest = DetailViewModel(savedStateHandle,useCase,dispatcher)
    }

    @Test
    fun `valid initial state `(){
        val state = detailViewModelTest.state
        assertEquals(
            DetailState(
                id = null,
                name  = "",
                detail = "",
                price ="",
                imageUrl = null,
                nameError = null,
                detailError = null,
                priceError = null,
                imageUrlError = null,
                isSaved = false,
                isLoading = false,
                isLoadingUpdate = false,
                msgError = null,
                isCreated = false,
                isUpdate = false,
                products = emptyList()
            ),
            state
        )
    }

    @Test
    fun `given a name , verify the state update name`(){
        val initialState = detailViewModelTest.state.name
        assertEquals(initialState,"")

        detailViewModelTest.onEvent(DetailEvent.NameChange("Product"))
        val updateState = detailViewModelTest.state.name
        assertEquals(
            "Product",
            updateState
        )
    }

    @Test
    fun `given invalid name, show name error`(){
        detailViewModelTest.onEvent(DetailEvent.NameChange(""))
        detailViewModelTest.onEvent(DetailEvent.DetailSave)
        val state = detailViewModelTest.state
        assertNotNull(state.nameError)

    }

    @Test
    fun `given a detail , verify the state update detail`(){
        val initialState = detailViewModelTest.state.detail
        assertEquals(initialState,"")

        detailViewModelTest.onEvent(DetailEvent.DetailChange("Product Detail"))
        val updateState = detailViewModelTest.state.detail
        assertEquals(
            "Product Detail",
            updateState
        )
    }

    @Test
    fun `given invalid detail, show detail error`(){
        detailViewModelTest.onEvent(DetailEvent.DetailChange("·$$$$"))
        detailViewModelTest.onEvent(DetailEvent.DetailSave)
        val state = detailViewModelTest.state
        assertNotNull(state.detailError)
    }


    @Test
    fun `given a price , verify the state update price`(){
        val initialState = detailViewModelTest.state.price
        assertEquals(initialState,"")

        detailViewModelTest.onEvent(DetailEvent.PriceChange("123.33"))
        val updateState = detailViewModelTest.state.price
        assertEquals(
            "123.33",
            updateState
        )
    }

    @Test
    fun `given invalid price, show name price`(){
        detailViewModelTest.onEvent(DetailEvent.PriceChange("12.$%$"))
        detailViewModelTest.onEvent(DetailEvent.DetailSave)
        val state = detailViewModelTest.state
        assertNotNull(state.priceError)
    }

    //https://stackoverflow.com/questions/40653625/uri-parse-always-returns-null-in-unit-test
    @Test
    fun `Valid create product, start loading`() = scope.runTest {
        detailViewModelTest.onEvent(DetailEvent.NameChange("Product"))
        detailViewModelTest.onEvent(DetailEvent.DetailChange("Product One"))
        detailViewModelTest.onEvent(DetailEvent.PriceChange("12"))

        detailViewModelTest.onEvent(DetailEvent.ImageUrlChange(Uri.parse("http://example.com")))
        detailViewModelTest.onEvent(DetailEvent.DetailSave)

        var state = detailViewModelTest.state

        assertNull(state.nameError)
        assertNull(state.detailError)
        assertNull(state.priceError)

        advanceUntilIdle()
        state = detailViewModelTest.state

        assert(!state.isLoading)
        assert(state.isCreated)
    }


    @Test
    fun `Valid create product but server error,  show error`() = scope.runTest{
        homeRepository.fakeError = true
        detailViewModelTest.onEvent(DetailEvent.NameChange("Product"))
        detailViewModelTest.onEvent(DetailEvent.DetailChange("Product One"))
        detailViewModelTest.onEvent(DetailEvent.PriceChange("12"))

        detailViewModelTest.onEvent(DetailEvent.ImageUrlChange(Uri.parse("http://example.com")))
        detailViewModelTest.onEvent(DetailEvent.DetailSave)

        var state = detailViewModelTest.state

        assertNull(state.nameError)
        assertNull(state.detailError)
        assertNull(state.priceError)

        advanceUntilIdle()
        state = detailViewModelTest.state

        assert(!state.isLoading)
        assertNotNull(state.msgError)
    }


    @Test
    fun `Valid update product, start loading`() = scope.runTest {
        detailViewModelTest.onEvent(DetailEvent.NameChange("Product"))
        detailViewModelTest.onEvent(DetailEvent.DetailChange("Product Update"))
        detailViewModelTest.onEvent(DetailEvent.PriceChange("12.23"))

        detailViewModelTest.onEvent(DetailEvent.ImageUrlChange(Uri.parse("http://example.com")))
        detailViewModelTest.onEvent(DetailEvent.DetailUpdate)

        var state = detailViewModelTest.state

        assertNull(state.nameError)
        assertNull(state.detailError)
        assertNull(state.priceError)

        advanceUntilIdle()
        state = detailViewModelTest.state

        assert(!state.isLoadingUpdate)

    }

    @Test
    fun `Valid update product but server error,  show error`() = scope.runTest{
        homeRepository.fakeError = true
        detailViewModelTest.onEvent(DetailEvent.NameChange("Product"))
        detailViewModelTest.onEvent(DetailEvent.DetailChange("Product Error sever"))
        detailViewModelTest.onEvent(DetailEvent.PriceChange("12"))

        detailViewModelTest.onEvent(DetailEvent.ImageUrlChange(Uri.parse("http://example.com")))
        detailViewModelTest.onEvent(DetailEvent.DetailUpdate)

        var state = detailViewModelTest.state

        assertNull(state.nameError)
        assertNull(state.detailError)
        assertNull(state.priceError)

        advanceUntilIdle()
        state = detailViewModelTest.state

        assert(!state.isLoadingUpdate)
        assertNotNull(state.msgError)
    }
}

