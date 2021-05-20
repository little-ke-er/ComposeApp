package com.haikun.lol.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.haikun.lol.data.Resource
import com.haikun.lol.data.responseentity.TabTitle
import com.haikun.lol.ui.commonCompose.SwipeRefreshList
import org.koin.androidx.compose.getViewModel


@Composable
fun RecommendScreen() {
    val homeViewModel: HomeViewModel = getViewModel()
    val tabTitle by homeViewModel.tabTitle.observeAsState()
    if (tabTitle == null) {
        homeViewModel.initTabTitle()
    } else {
        tabTitle?.let {
            when (it) {
                is Resource.SuccessResource -> {
                    homeViewModel.hideLoading()
                    RecommendTab(it.data)
                }
                is Resource.ErrorResource -> {
                    homeViewModel.hideLoading()
                }
                is Resource.LoadingResource -> {
                    homeViewModel.showLoaDing()
                }
            }
        }

    }
}

@Composable
fun RecommendTab(tabTitle: List<TabTitle>?) {

    var selectedTabIndex by remember {
        mutableStateOf(0)
    }
    Column {
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex, edgePadding = 10.dp,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            tabTitle?.forEachIndexed { index, tabTitle ->
                Tab(selected = index == selectedTabIndex, onClick = { selectedTabIndex = index }) {
                    Text(tabTitle.title, modifier = Modifier.padding(8.dp))
                }
            }
        }

        RecommendContent(selectedTabIndex, tabTitle)
    }

}

@Composable
fun RecommendContent(selectedTabIndex: Int, tabTitle: List<TabTitle>?) {

    tabTitle?.let { it ->

        val homeViewModel: HomeViewModel = getViewModel()//使用koin注入viewModel

        val collectAsLazyPagingItems =
            homeViewModel.getContentByTitle(it[selectedTabIndex].title).collectAsLazyPagingItems()

        SwipeRefreshList(collectAsLazyPagingItems){
            items(collectAsLazyPagingItems) {
                it?.let {
                    Column {
                        Text(text = it.title, style = MaterialTheme.typography.h6)
                        Text(text = it.content)
                        Divider()
                    }
                }
            }
        }
    }
}

