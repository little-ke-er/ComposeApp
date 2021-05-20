## 添加依赖

```kotlin
implementation ("androidx.paging:paging-compose:1.0.0-alpha08")
implementation ("com.google.accompanist:accompanist-swiperefresh:0.9.0")
```

## 定义Item内容
非常简单的一个标题和内容

```kotlin
data class Content(val title:String,val content: String) 
```
## 定义RetrofitService

```kotlin
interface RetrofitService {
    @GET("getContent")
    suspend fun getContentByTitle(@Query("page") page: Int, @Query("title") title: String): AgResponse<List<Content>>
}
```

## 定义PagingSource

```kotlin
class ContentPageSource(val title: String, private val mService: RetrofitService) : PagingSource<Int,Content>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Content> {
        val key=params.key?:0
        val resource = exeRequest {
            mService.getContentByTitle(key, title)
        }

        return if (resource is Resource.SuccessResource) {
            resource.data?.let {
                return LoadResult.Page(
                    it, null, if (key > 8) {//这里是假设获取了8页之后就没有下一页了
                        null
                    } else {
                        key + 1
                    }
                )
            }
            LoadResult.Error(Exception())
        } else {
            LoadResult.Error(Exception())
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Content>): Int? {
        return null
    }

}
```
## Repository

```kotlin
class HomeRepository(private val mService:RetrofitService) {

    fun getContentByTitle(title:String)=Pager(config = PagingConfig(20)){
        ContentPageSource(title,mService)
    }
}
```
## ViewModel

```kotlin
class HomeViewModel(private val homeRepository: HomeRepository) : BaseViewModel() {

    fun getContentByTitle(title: String): Flow<PagingData<Content>> {
        return homeRepository.getContentByTitle(title).flow.cachedIn(viewModelScope)
    }
}
```

## 对SwipeRefresh和Paging封装

```kotlin
@Composable
fun <T : Any> SwipeRefreshList(collectAsLazyPagingItems: LazyPagingItems<T>, content: LazyListScope.() -> Unit) {
    val rememberSwipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)
    SwipeRefresh(
        state = rememberSwipeRefreshState,
        onRefresh = { collectAsLazyPagingItems.refresh() }) {

        rememberSwipeRefreshState.isRefreshing =
            collectAsLazyPagingItems.loadState.refresh is LoadState.Loading

        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            
            content()

            collectAsLazyPagingItems.apply {
                when {
                    loadState.append is LoadState.Loading -> {//加载更多时，就在底部显示loading的item
                        item { LoadingItem() }
                    }
                    loadState.append is LoadState.Error -> {//加载更多的时候出错了，就在底部显示错误的item
                        item {
                            ErrorItem() {
                                collectAsLazyPagingItems.retry()
                            }
                        }
                    }
                    loadState.refresh is LoadState.Error -> {
                        if (collectAsLazyPagingItems.itemCount <= 0) {//刷新的时候，如果itemCount小于0，说明是第一次进来，出错了显示一个大的错误内容
                            item {
                                ErrorContent() {
                                    collectAsLazyPagingItems.retry()
                                }
                            }
                        } else {
                            item {
                                ErrorItem() {
                                    collectAsLazyPagingItems.retry()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ErrorItem(retry: () -> Unit) {
    Button(onClick = { retry() }, modifier = Modifier.padding(10.dp)) {
        Text(text = "重试")
    }
}

@Composable
fun ErrorContent(retry: () -> Unit) {
    Image(modifier = Modifier.padding(top = 80.dp),painter = painterResource(id = R.drawable.bg_empty), contentDescription = null)
    Text(text = "请求出错啦")
    Button(onClick = { retry() }, modifier = Modifier.padding(10.dp)) {
        Text(text = "重试")
    }
}

@Composable
fun LoadingItem() {
    CircularProgressIndicator(modifier = Modifier.padding(10.dp))
}
```

## 在Compose里使用

```kotlin
@Composable
fun RecommendContent(selectedTabIndex: Int, tabTitle: List<TabTitle>?) {

        val homeViewModel: HomeViewModel = getViewModel()//使用koin注入viewModel
        
        val collectAsLazyPagingItems =
            homeViewModel.getContentByTitle("标题").collectAsLazyPagingItems()

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
```
## 最终效果

![最终效果](https://img-blog.csdnimg.cn/20210520090447876.gif#pic_center)
## 项目地址 [https://github.com/haikun-li/ComposeApp](https://github.com/haikun-li/ComposeApp)


