package com.medel.vivero_v1.home.domain.usecases.detail

import androidx.room.RoomOpenHelper
import junit.framework.TestCase.assertEquals
import org.junit.Test


class ValidateFormUseCaseTest {

    @Test
    fun `valid inputs`(){
        val validateFormUseCase = ValidateFormUseCase()
        val name = "Product name"
        val detail = "Product num 1"
        val price = "245.43"
        val result = validateFormUseCase(name,detail,price)

        assertEquals(
            FormValidationResult(
                ValidationResult.Valid,
                ValidationResult.Valid,
                ValidationResult.Valid
            ),
            result
        )
    }

    @Test
    fun `invalid name`() {
        val validateFormUseCase = ValidateFormUseCase()
        val name = "122new"
        val detail = "Product 12"
        val price = "12.20"
        val result = validateFormUseCase(name,detail,price)

        assertEquals(
            FormValidationResult(
                ValidationResult.Invalid("No puede contener números u otros caracteres"),
                ValidationResult.Valid,
                ValidationResult.Valid
            ),
            result
        )
    }

    @Test
    fun `empty inputs`(){
        val validateFormUseCase = ValidateFormUseCase()
        val name = ""
        val detail = "Product 12"
        val price = ""
        val result = validateFormUseCase(name,detail,price)
        assertEquals(
            FormValidationResult(
                ValidationResult.Invalid("Ingrese el nombre"),
                ValidationResult.Valid,
                ValidationResult.Invalid("Ingrese el precio")
            ),
            result
        )
    }

    @Test
    fun `invalid detail`(){
        val validateFormUseCase = ValidateFormUseCase()
        val name = "Product One"
        val detail = "Product ·$%$%()"
        val price = "120"
        val result = validateFormUseCase(name,detail,price)

        assertEquals(
            FormValidationResult(
                ValidationResult.Valid,
                ValidationResult.Invalid("No puede ingresar caracteres"),
                ValidationResult.Valid
            ),
            result
        )
    }

    @Test
    fun `invalid price`(){
        val validateFormUseCase = ValidateFormUseCase()
        val name = "Product One"
        val detail = "Product Good"
        val price = "120 !!()"
        val result = validateFormUseCase(name,detail,price)

        assertEquals(
            FormValidationResult(
                ValidationResult.Valid,
                ValidationResult.Valid,
                ValidationResult.Invalid("Valide el precio")
            ),
            result
        )
    }
}