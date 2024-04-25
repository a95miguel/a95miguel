package com.medel.vivero_v1.authentication.data.repository

import com.medel.vivero_v1.authentication.domain.repository.AuthenticationRepository

class FakeAuthenticationRepository : AuthenticationRepository{

    override suspend fun login(): Result<Unit> {
        return Result.success(Unit)
    }

    override fun getUserId(): String? {
        return "USER_ID"
    }

    override suspend fun logout() {

    }

}