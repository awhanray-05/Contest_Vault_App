package com.example.contestvault.screens

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import com.example.contestvault.R

sealed class Screen(val title: String, val route: String) {

    sealed class DrawerScreen(val dTitle: String, val dRoute: String, @DrawableRes val dIcon: Int) : Screen(dTitle, dRoute) {
        object Profile: DrawerScreen("Search Profile", "search_profile", R.drawable.baseline_search_24)
        object ContestData: DrawerScreen("Contest Data", "lists", R.drawable.baseline_view_list_24)
        object AddProfile: DrawerScreen("Add Profile", "add_profile", R.drawable.baseline_person_add_alt_1_24)
    }

    sealed class BottomScreen(val bTitle: String , val bRoute: String, @DrawableRes val bIcon: Int) : Screen(bTitle, bRoute) {
        object CodeChef: BottomScreen("CodeChef", "code_chef", R.drawable.icons8_codechef_48)
        object CodeForces: BottomScreen("Codeforces", "code_forces",
            R.drawable.icons8_codeforces__programming_competitions_and_contests__programming_community__24
        )
        object LeetCode: BottomScreen("Leetcode", "leet_code",
            R.drawable.icons8_level_up_your_coding_skills_and_quickly_land_a_job_24
        )
    }

}

val screensInDrawer = listOf(
    Screen.DrawerScreen.Profile,
    Screen.DrawerScreen.ContestData,
    Screen.DrawerScreen.AddProfile
)

val screensInBottom = listOf(
    Screen.BottomScreen.CodeChef,
    Screen.BottomScreen.CodeForces,
    Screen.BottomScreen.LeetCode
)