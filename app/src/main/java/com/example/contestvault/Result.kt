package com.example.contestvault

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Result(
    val userRank: Int,
    val username: String,
    val userInstitute: String,
    val userTotalScore: String,
    val userLastAcceptedTime: String,
    val userProblemsSolved: List<Int?>,
): Parcelable

data class ResultResponse(val resultList: List<Result>)