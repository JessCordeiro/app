package com.example.app.useCase


import com.example.app.data.FailureData
import com.example.app.resource.Resource
import com.example.app.util.NetworkCodes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

abstract class FlowUseCase <in P, R>() {

    suspend operator fun invoke(parameters: P? = null): Flow<Resource<R>> = execute(parameters)
        .catch{
            e ->
            emit(
                Resource.Failure(
                    FailureData(
                        code = NetworkCodes.GENERIC_ERROR,
                        message = e.localizedMessage
                    )
                )
            )
        }
    protected abstract suspend fun execute(parameters: P? = null): Flow<Resource<R>>
}