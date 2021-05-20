package com.haikun.lol.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.haikun.lol.data.Resource
import com.haikun.lol.data.responseentity.Content
import com.haikun.lol.request.RetrofitService
import com.haikun.lol.request.exeRequest

class HomeRepository(private val mService:RetrofitService) {

    fun getContentByTitle(title:String)=Pager(config = PagingConfig(20)){
        ContentPageSource(title,mService)
    }
}

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