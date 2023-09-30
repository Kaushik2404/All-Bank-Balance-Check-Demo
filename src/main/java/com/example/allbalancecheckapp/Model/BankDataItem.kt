package com.example.allbalancecheckapp.Model


import com.google.gson.annotations.SerializedName

data class BankDataItem(
    @SerializedName("header")
    val header:Boolean,
    @SerializedName("bankBalanceNo")
    val bankBalanceNo: Long,
    @SerializedName("bankName")
    val bankName: String,
    @SerializedName("bankType")
    val bankType: String,
    @SerializedName("blockAtmCard")
    val blockAtmCard: Long,
    @SerializedName("id")
    val id: Int,
    @SerializedName("miniStatement")
    val miniStatement: Long,
    @SerializedName("url")
    val url: String
)