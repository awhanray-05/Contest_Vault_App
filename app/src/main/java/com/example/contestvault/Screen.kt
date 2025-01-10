package com.example.contestvault

import androidx.annotation.DrawableRes

sealed class Screen(val title: String, val route: String) {

    sealed class DrawerScreen(val dTitle: String , val dRoute: String, @DrawableRes val dIcon: Int) : Screen(dTitle, dRoute) {
        object CodeChef: DrawerScreen("Code Chef", "code_chef", R.drawable.icons8_codechef_48)
        object CodeForces: DrawerScreen("Code Forces", "code_forces", R.drawable.icons8_codeforces__programming_competitions_and_contests__programming_community__24)
        object LeetCode: DrawerScreen("Leet Code", "leet_code", R.drawable.icons8_level_up_your_coding_skills_and_quickly_land_a_job_24)
    }

}

val screenInDrawer = listOf(
    Screen.DrawerScreen.CodeForces,
    Screen.DrawerScreen.CodeChef,
    Screen.DrawerScreen.LeetCode,
)