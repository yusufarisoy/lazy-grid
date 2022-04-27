package com.yusufarisoy.lazygridapp.util

interface CoroutineUseCase<Params, Result> {
    suspend fun run(params: Params): Result
}
