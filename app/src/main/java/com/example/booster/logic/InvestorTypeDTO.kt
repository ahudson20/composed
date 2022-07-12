package com.example.booster.logic

import com.google.gson.annotations.SerializedName

data class FundDetailsDTO(
    @SerializedName("name") val name: String? = null,
    @SerializedName("fundName") val fundName: String? = null,
    @SerializedName("fundDetails") val fundDetails: List<String>? = null,
    @SerializedName("fundInvestmentMix") val fundInvestmentMix: List<FundMix>? = null
)

data class FundMix(
    @SerializedName("sectionTitle") val sectionTitle: String? = null,
    @SerializedName("percentage") val percentage: Int? = null,
    @SerializedName("assetType") val assetType: String? = null
)