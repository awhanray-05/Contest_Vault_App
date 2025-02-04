package com.example.contestvault.screens.codechef

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.contestvault.data.codechef.CodeChefUserData
import com.example.contestvault.screens.Screen
import com.example.contestvault.screens.screensInBottom
import com.example.contestvault.screens.screensInDrawer
import com.example.contestvault.viewModels.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CodeChefUserView(
        viewModel: MainViewModel = viewModel()
    ) {

    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    // The below 3 lines allow us to find out on which "View" we currently are
    val controller: NavController = rememberNavController()
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val currentdrawerscreen =  remember { viewModel.currentDrawerScreen }
    val currentbottombarscreen = remember { viewModel.currentBottomBarScreen }

    var titleStart by remember { mutableStateOf(currentdrawerscreen.title) }
    var titleEnd by remember { mutableStateOf(currentbottombarscreen.title) }

    val ccDataViewModel: MainViewModel = viewModel()
    val viewState = ccDataViewModel.cCuserDataState

    var userHandle by remember { mutableStateOf("") }

    val bottomBar: @Composable () -> Unit = {
        if((currentdrawerscreen is Screen.DrawerScreen.Profile && currentbottombarscreen is Screen.BottomScreen) || (currentdrawerscreen is Screen.DrawerScreen.ContestData && currentbottombarscreen is Screen.BottomScreen)) {
            BottomNavigation(
                modifier = Modifier
                    .wrapContentSize()
                    .shadow(elevation = 15.dp)
            ) {
                screensInBottom.forEach { item ->
                    val isSelected = item.bRoute == currentRoute
                    val color = if(isSelected) Color.Cyan else Color.White

                    BottomNavigationItem(
                        selected = isSelected,
                        onClick = {
                            // TODO
//                            controller.navigate(item.bRoute)
//                            viewModel.setCurrentBottomBarScreen(item)
//                            titleEnd = item.bTitle
                        },
                        icon = {
                            Icon(painter = painterResource(item.bIcon), contentDescription = item.bTitle, tint = color, modifier = Modifier
                                .size(24.dp)
                                .padding(bottom = 4.dp))
                        },
                        label = { Text(text = item.bTitle, color = color) }
                    )
                }
            }
        }
    }

    Scaffold(
        bottomBar = bottomBar,
        topBar = {
            TopAppBar(
                title = { Text("$titleStart | $titleEnd") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            coroutineScope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }
                    ) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu", tint = Color.White)
                    }
                }
            )
        },
        drawerGesturesEnabled = false,
        drawerContent = {
            LazyColumn(
                modifier = Modifier.padding(top = 40.dp, start = 16.dp)
            ) {
                items(screensInDrawer) {item ->
                    // TODO: Add a drawer item
                    DrawerItem(selected = currentRoute == item.dRoute, item) {
                        coroutineScope.launch {
                            scaffoldState.drawerState.close()
                        }
                    }
                }
            }
        },
        scaffoldState = scaffoldState,
        modifier = Modifier.shadow(elevation = 15.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = userHandle,
                onValueChange = { userHandle = it },
                label = { Text("User Handle", color = Color.Black) },
                placeholder = { Text("byteninja_05") },
                colors = OutlinedTextFieldDefaults.colors(
                    cursorColor = Color.DarkGray,
                    focusedBorderColor = Color.Blue,
                    unfocusedBorderColor = Color.DarkGray,
                    unfocusedTextColor = Color.DarkGray,
                    focusedTextColor = Color.Black
                ),
                modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
                singleLine = true,
                trailingIcon = {
                    IconButton(
                        onClick = {
                            ccDataViewModel.fetchCCUserProfileData(userHandle)
                        }
                    ) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search", tint = if(userHandle.isNotBlank()) Color.Cyan else Color.Black)
                    }
                }
            )
            Box {
                when {
                    viewState.loading -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 100.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                color = Color.Blue,
                                strokeWidth = 2.dp,
                            )
                        }
                    }

                    viewState.error != null -> {
                        Text(
                            text = "Error occurred: ${viewState.error}",
                            modifier = Modifier.padding(
                                top = 100.dp,
                                start = 16.dp,
                                bottom = 8.dp,
                                end = 8.dp
                            ),
                            color = Color.Red
                        )
                    }

                    else -> {
                        CCDataScreen(viewState.data, userHandle)
                    }
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
    val isSelectedColor = if(selected) Color.Black else Color.DarkGray
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .clickable {
                onDrawerItemClicked()
            }
    ) {
        androidx.compose.material3.Icon(
            painter = painterResource(id = item.dIcon),
            contentDescription = item.dTitle,
            modifier = Modifier.padding(end = 16.dp, top = 4.dp),
            tint = isSelectedColor
        )
        Text(text = item.dTitle, style = MaterialTheme.typography.h6, fontWeight = txtStyle, color = isSelectedColor, modifier = Modifier.padding(top = 4.dp))
    }
}

@Composable
fun CCDataScreen(data: CodeChefUserData, userHandle: String = "byteninja_05") {
    val showGraphHeatmap = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = data.profile,
            contentDescription = null,
            modifier = Modifier.padding(end = 4.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = data.name, color = Color.Black)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            val color = when {
                data.stars.contains("1") -> Color.DarkGray
                data.stars.contains("2") -> Color.Green
                data.stars.contains("3") -> Color.Blue
                data.stars.contains("1") -> Color.Magenta
                else -> Color.Yellow
            }
            Text(text = "Current Rating: ", color = Color.Black, fontWeight = FontWeight.Bold)
            Text(text = data.stars, color = color, modifier = Modifier.padding(end = 8.dp))
            Text(text = data.currentRating.toString(), color = Color.DarkGray)
        }
        Row {
            Text(text = "Country: ", color = Color.Black, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 2.dp,end = 8.dp))
            AsyncImage(
                model = data.countryFlag,
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
                    .padding(end = 4.dp)
            )
            Text(data.countryName, color = Color.DarkGray, modifier = Modifier.padding(top = 2.dp))
        }
        Spacer(modifier = Modifier.height(4.dp))

        Row {
            Text(text = "Highest Rating: ", color = Color.Black, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 2.dp,end = 8.dp))
            Text(text = data.highestRating.toString(), color = Color.DarkGray, modifier = Modifier.padding(top = 2.dp))
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row {
            Text(text = "Global Rank: ", color = Color.Black, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 2.dp,end = 8.dp))
            Text(text = data.globalRank.toString(), color = Color.DarkGray, modifier = Modifier.padding(top = 2.dp))
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row {
            Text(text = "Country Rank: ", color = Color.Black, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 2.dp,end = 8.dp))
            Text(text = data.countryRank.toString(), color = Color.DarkGray, modifier = Modifier.padding(top = 2.dp))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                if(showGraphHeatmap.value) showGraphHeatmap.value = false
                else showGraphHeatmap.value = true
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(bottom = 16.dp),
            elevation = ButtonDefaults.elevation(25.dp)
        ) {
            Text("User Ratings and Heatmap", textAlign = TextAlign.Center)
        }

        if(showGraphHeatmap.value) {
            val iframeUrl1 = "https://codechef-api.vercel.app/rating/$userHandle"
            val iframeUrl2 = "https://codechef-api.vercel.app/heatmap/$userHandle"
            Text(
                "User Ratings:",
                modifier = Modifier.padding(top = 8.dp),
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Divider()
            AndroidView(
                factory = { context ->
                    WebView(context).apply {
                        webViewClient = WebViewClient()
                        settings.javaScriptEnabled = true // Enable JavaScript if needed
                        loadUrl(iframeUrl1)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text("User Heatmap:", color = Color.Black, fontWeight = FontWeight.Bold)
            Divider()
            AndroidView(
                factory = { context ->
                    WebView(context).apply {
                        webViewClient = WebViewClient()
                        settings.javaScriptEnabled = true // Enable JavaScript if needed
                        loadUrl(iframeUrl2)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
            Divider()
        }
    }
}

@Composable
fun NavigationGraph(modifier: Modifier, navController: NavController) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = Screen.DrawerScreen.Profile.dRoute,
    ) {
        composable(Screen.DrawerScreen.Profile.dRoute) {
            CodeChefUserView()
        }
        composable(Screen.BottomScreen.CodeChef.bRoute) {
            CodeChefUserView()
        }
    }
}