package com.powakaz.feature_finance.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.powakaz.core_network.model.NetworkResult
import com.powakaz.feature_finance.domain.constants.FinanceConstants
import com.powakaz.feature_finance.domain.model.FinanceDashboard
import com.powakaz.feature_finance.domain.model.FinanceDay
import com.powakaz.feature_finance.domain.model.WalletType
import com.powakaz.feature_finance.domain.usecase.GetFinancialDashboardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class FinDashboardUiState(
    val userBalance: String = "",
    val weekBalance: String = "",
    val progressWeekBalance: Float = 0f,
    val cashBalance : String = "",
    val cardBalance : String = "",
    val listDays : List<FinanceDay> = listOf()
)

@HiltViewModel
class FinanceDashboardViewModel @Inject constructor(getFinancialDashboardUseCase: GetFinancialDashboardUseCase) :
    ViewModel() {

    private val _uiState = MutableStateFlow(FinDashboardUiState())
    val uiState = _uiState.asStateFlow()


    init {
        viewModelScope.launch {
            when (val finDashboardData = getFinancialDashboardUseCase(1)) {
                is NetworkResult.Success<FinanceDashboard> -> {
                    _uiState.update {
                        FinDashboardUiState(
                            userBalance = finDashboardData.data.totalBalance.toInt().toString(),
                            weekBalance = finDashboardData.data.weekBalance.toInt().toString(),
                            progressWeekBalance = finDashboardData.data.weekBalance / FinanceConstants.WEEKLY_MAX_BUDGET,
                            cardBalance = finDashboardData.data.userWalletList.find { it.userId == 1 && it.type == WalletType.CARD }!!.balance.toInt().toString(),
                            cashBalance = finDashboardData.data.userWalletList.find { it.userId == 1 && it.type == WalletType.CASH }!!.balance.toInt().toString(),
                            listDays = finDashboardData.data.financeDays
                        )
                    }
                }

                is NetworkResult.Error -> {

                }
                is NetworkResult.Exception -> {

                }
            }


        }
    }
}