package com.example.contestvault.data.codechef

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CodeChefUserData(
    val success: Boolean,
    val status: Int,
    val profile: String,
    val name: String,
    val currentRating: Int,
    val highestRating: Int,
    val countryFlag: String,
    val countryName: String,
    val globalRank: Int,
    val countryRank: Int,
    val stars: String
): Parcelable
