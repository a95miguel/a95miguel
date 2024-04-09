package com.medel.vivero_v1.home.domain.usecases.detail

/**
 * Casos de uso que valido los datos del formulario de producto
 */
class ValidateFormUseCase {
    operator fun invoke(name: String, detail: String, price: String): FormValidationResult {
        val nameResult = validateName(name)
        val detailResult = validateDetail(detail)
        val priceResult = validatePrice(price)

        return FormValidationResult(nameResult, detailResult, priceResult)
    }

    private fun validateName(name: String): ValidationResult {
        //Expresion que valida solo letras y espacios
        val regexName = Regex("^[\\p{L}\\s]+\$")
        return when {
            name.isEmpty() -> ValidationResult.Invalid("Ingrese el nombre")
            !name.matches(regexName) -> ValidationResult.Invalid("No puede contener números u otros caracteres")
            else -> ValidationResult.Valid
        }
    }

    private fun validateDetail(detail: String): ValidationResult {
        //Expresion que valida solo letras , espacios y numeros
        val regexDetail = Regex("^[\\p{L}0-9\\s]*$")
        return if (!detail.matches(regexDetail)) {
            ValidationResult.Invalid("No puede ingresar caracteres")
        } else {
            ValidationResult.Valid
        }
    }

    private fun validatePrice(price: String): ValidationResult {
        //Expresion que valida solo numeros o decimales con dos numeros despues del punto
        val regexPrice = Regex("^[0-9]+([.][0-9]{2})?\$")
        return when {
            price.isEmpty() -> ValidationResult.Invalid("Ingrese el precio")
            !price.matches(regexPrice) -> ValidationResult.Invalid("Valide el precio")
            else -> ValidationResult.Valid
        }
    }

}

sealed class ValidationResult{
    object Valid : ValidationResult()
    data class Invalid(val errorMessage : String) : ValidationResult()
}

data class FormValidationResult(
    val nameResult: ValidationResult,
    val detailResult: ValidationResult,
    val priceResult: ValidationResult
)