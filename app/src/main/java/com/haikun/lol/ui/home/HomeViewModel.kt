package com.haikun.lol.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.haikun.lol.data.Resource
import com.haikun.lol.data.responseentity.Content
import com.haikun.lol.data.responseentity.TabTitle
import com.haikun.lol.data.responseentity.User
import com.haikun.lol.repository.HomeRepository
import com.haikun.lol.ui.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HomeViewModel(private val homeRepository: HomeRepository) : BaseViewModel() {

    val user = MutableLiveData<User>()

    val tabTitle = MutableLiveData<Resource<List<TabTitle>>>()


    init {
        initUser()
    }

    private fun initUser() {
        user.value = User(
            "不会二连的嘉文",
            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3869452544,2736629861&fm=11&gp=0.jpg",
            "犯我德邦者，虽远必诛！"
        )
    }

    fun initTabTitle() {
        viewModelScope.launch {
            tabTitle.postValue(Resource.LoadingResource(null))
            delay(1000)
            tabTitle.postValue(Resource.SuccessResource(mutableListOf(TabTitle("帖子"),TabTitle("官方"),TabTitle("赛事"),TabTitle("手游"),TabTitle("云顶"))))

        }
    }

    fun getContentByTitle(title: String): Flow<PagingData<Content>> {
        return homeRepository.getContentByTitle(title).flow.cachedIn(viewModelScope)
    }


}