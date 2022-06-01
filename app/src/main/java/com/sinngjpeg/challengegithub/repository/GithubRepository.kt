package com.sinngjpeg.challengegithub.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sinngjpeg.challengegithub.model.Item
import com.sinngjpeg.challengegithub.model.PullRequest
import com.sinngjpeg.challengegithub.services.GithubService
import com.sinngjpeg.challengegithub.services.RepositoryPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GitHubRepository @Inject constructor(private val api: GithubService) {

    fun getRepositoriesList(): Flow<PagingData<Item>> {
        return Pager(PagingConfig(1)) {
            RepositoryPagingSource(api)
        }.flow
    }

    suspend fun getPullRequestList(owner: String, repository: String): List<PullRequest> =
        api.getPullRequestList(owner, repository)
}