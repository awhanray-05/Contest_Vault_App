package com.example.contestvault.ui

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
import androidx.compose.material.icons.filled.Menu
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.contestvault.MainViewModel_1
import com.example.contestvault.R
import com.example.contestvault.Screen
import com.example.contestvault.screenInDrawer
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
fun ResultScreen(results: List<com.example.contestvault.Result>) {
    LazyColumn {
        items(results) {it ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = 4.dp
                ) {
                    Column() {
                        Text(results.get(0).username)
                        Text(results.get(0).userInstitute)
                        Text(results.get(0).userRank.toString())
                        Text(results.get(0).userTotalScore)
                        Text(results.get(0).userLastAcceptedTime)
                        Text(results.get(0).userProblemsSolved.toString())
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