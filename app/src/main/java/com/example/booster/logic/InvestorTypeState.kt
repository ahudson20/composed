package com.example.booster.logic

import com.dsc.form_builder.*

data class InvestorViewModelState(
    val isLoading: Boolean = true,
    val fundDetail: List<FundDetailsDTO>? = null,
    val errorState: ErrorState? = null,
    val formState: FormState<BaseState<out Any>>
) {
    fun toUiState(): InvestorUiState {
        return when {
            isLoading -> InvestorUiState.Loading(errorState = errorState)
            else -> InvestorUiState.InvestorDetails(
                isLoading = isLoading,
                fundDetail = fundDetail!!,
                formState = formState
            )
        }
    }
}

sealed interface InvestorUiState {
    val errorState: ErrorState?

    data class Loading(
        override val errorState: ErrorState? = null,
    ) : InvestorUiState

    data class InvestorDetails(
        val isLoading: Boolean = true,
        val fundDetail: List<FundDetailsDTO>,
        override val errorState: ErrorState? = null,
        val formState: FormState<BaseState<out Any>>
    ) : InvestorUiState
}

sealed class ErrorState {
    object UnableToLoadData : ErrorState()
    object UnableToParseData : ErrorState()
}