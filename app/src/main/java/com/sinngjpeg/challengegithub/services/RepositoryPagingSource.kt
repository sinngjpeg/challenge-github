package com.sinngjpeg.challengegithub.services

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sinngjpeg.challengegithub.model.Item

class RepositoryPagingSource(private val service: GithubService) : PagingSource<Int, Item>() {
    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {

        return state.anchorPosition

    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        val pageNumber = params.key ?: FIRST_PAGE_INDEX
        return try {
            val response = service.getRepositories(pageNumber)
            var nextPageNumber: Int? = null
            if (!response.items.isNullOrEmpty()) {
                nextPageNumber = pageNumber + 1
            }
            LoadResult.Page(
                data = response.items,
                prevKey = null,
                nextKey = nextPageNumber
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object{
        private const val FIRST_PAGE_INDEX = 1
    }
}