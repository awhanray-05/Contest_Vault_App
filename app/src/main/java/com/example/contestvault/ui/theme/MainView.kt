package com.example.contestvault.ui

/*
import androidx.compose.material.icons.filled.Menu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contestvault.viewModels.MainViewModel_1
import com.example.contestvault.R
import com.example.contestvault.screens.Screen
import com.example.contestvault.data.Result
import com.example.contestvault.screens.screenInDrawer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(modifier: Modifier, viewState: MainViewModel_1.ResultState) {

    // Opening and closing the drawer is a Co-routine method or an asynchronous method
    val scope: CoroutineScope = rememberCoroutineScope()
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.height(90.dp),
                title = { Text("Home", fontSize = 25.sp, modifier = Modifier.padding(top = 25.dp), fontStyle = FontStyle.Italic) },
                navigationIcon = {
                    IconButton(onClick = {
                        // Open Drawer
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu",
                            tint = Color.White,
                            modifier = Modifier.padding(top = 18.dp)
                        )
                    }
                },
                backgroundColor = colorResource(R.color.app_bar_color),
                elevation = 30.dp
            )
        },
        scaffoldState = scaffoldState,
        drawerContent = {
            LazyColumn(modifier = Modifier.fillMaxSize().background(color = colorResource(R.color.drawer_color)).padding(top = 40.dp, start = 16.dp)) {
                items(screenInDrawer) {
                    item ->
                    DrawerItem(false, item) {
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                    }
                }
            }
        }
    ) {
//        Text("Text", Modifier.fillMaxSize(1f).padding(it).background(colorResource(R.color.app_background)))
        Box(modifier = Modifier.fillMaxSize().padding(it).background(colorResource(R.color.app_background))) {
            when{
                viewState.loading -> {
                    CircularProgressIndicator(modifier.align(Alignment.Center))
                }
                viewState.error != null -> {
                    Text(
                        text = "ERROR occurred: ${viewState.error}",
                        color = Color.Red,
                        modifier = Modifier.padding(top = 100.dp, start = 16.dp, bottom = 8.dp, end = 8.dp)
                    )
                }
                else -> {
                    // Display the Results
                    ResultScreen(viewState.list)
                }
            }
        }
    }
}

@Composable
fun ResultScreen(results: List<Result>) {
    LazyColumn {
        items(results) {it ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = 4.dp
                ) {
                    Column() {
                        Text("Start")
                        results.get(0).username?.let { it1 -> Text(it1) }
                        results.get(0).userInstitute?.let { it1 -> Text(it1) }
                        Text(results.get(0).userRank.toString())
                        results.get(0).userTotalScore?.let { it1 -> Text(it1) }
                        results.get(0).userLastAcceptedTime?.let { it1 -> Text(it1) }
                        Text(results.get(0).userProblemsSolved.toString())
                        Text("End")
                    }
                }
            }
    }
}

@Composable
fun DrawerItem(
    selected: Boolean,
    item: Screen.DrawerScreen,
    onDrawerItemClicked: () -> Unit
) {
    val txtStyle = if(selected) FontWeight.ExtraBold else FontWeight.Medium
    val txtColor = if(selected) Color.Black else Color.DarkGray
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp)
//            .background(color = background, shape = RoundedCornerShape(16.dp))
            .clickable {
                onDrawerItemClicked()
            }
    ) {
        Icon(
            painter = painterResource(id = item.dIcon),
            contentDescription = item.dTitle,
            modifier = Modifier.padding(end = 16.dp, top = 4.dp).size(20.dp),
            tint = LocalContentColor.current,
        )
        Text(text = item.dTitle, fontWeight = txtStyle, color = txtColor, modifier = Modifier.padding(top = 2.dp), fontSize = 20.sp)
    }
}
 */



//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material.Scaffold
//import androidx.compose.material.TopAppBar
//import androidx.compose.material3.CircularProgressIndicator
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.contestvault.viewModels.MainViewModel_1
//import com.example.contestvault.R
//import com.example.contestvault.Result
//import kotlinx.coroutines.launch
//
//@Composable
//fun MainView(modifier: Modifier, viewState: MainViewModel_1.ResultState) {
//    val scope = rememberCoroutineScope()
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("Home", fontSize = 20.sp) },
//                backgroundColor = colorResource(R.color.app_bar_color),
//                navigationIcon = {
//                    IconButton(onClick = { /* Handle drawer open */ }) {
//                        Icon(imageVector = androidx.compose.material.icons.Icons.Default.Menu, contentDescription = "Menu")
//                    }
//                }
//            )
//        }
//    ) { paddingValues ->
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(paddingValues)
//                .background(colorResource(R.color.app_background))
//        ) {
//            when {
//                viewState.loading -> {
//                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
//                }
//                viewState.error != null -> {
//                    Text(
//                        text = "Error: ${viewState.error}",
//                        color = Color.Red,
//                        modifier = Modifier.align(Alignment.Center)
//                    )
//                }
//                else -> {
//                    ResultScreen(viewState.list)
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun ResultScreen(results: List<Result>) {
//    if (results.isEmpty()) {
//        Text("No results found", Modifier.padding(16.dp))
//    } else {
//        LazyColumn {
//            items(results) { result ->
//                ResultCard(result)
//            }
//        }
//    }
//}
//
//@Composable
//fun ResultCard(result: Result) {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp)
//            .background(Color.LightGray)
//    ) {
//        Text("Username: ${result.username ?: "N/A"}")
//        Text("Institute: ${result.userInstitute ?: "N/A"}")
//        Text("Rank: ${result.userRank}")
//        Text("Score: ${result.userTotalScore ?: "N/A"}")
//        Text("Last Accepted Time: ${result.userLastAcceptedTime ?: "N/A"}")
//        Text("Problems Solved: ${result.userProblemsSolved}")
//    }
//}
