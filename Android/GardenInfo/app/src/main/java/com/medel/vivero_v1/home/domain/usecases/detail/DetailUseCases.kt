package com.medel.vivero_v1.home.domain.usecases.detail

data class DetailUseCases(
    val createProductUseCase: CreateProductUseCase,
    val validateFormUseCase: ValidateFormUseCase,
    val getProductByIdUseCase: GetProductByIdUseCase,
    val updateProductUseCase: UpdateProductUseCase

)
