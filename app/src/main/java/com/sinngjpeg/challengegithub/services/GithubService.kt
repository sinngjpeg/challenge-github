package com.sinngjpeg.challengegithub.services

import com.sinngjpeg.challengegithub.model.GithubRepository
import com.sinngjpeg.challengegithub.model.PullRequest
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {
    @GET("/search/repositories?q=language:kotlin&sort=stars")
    suspend fun getRepositories(@Query("page") page: Int): GithubRepository

    @GET("/repos/{owner}/{repository}/pulls")
    suspend fun getPullRequestList(
        @Path("owner") owner: String,
        @Path("repository") repository: String
    ): List<PullRequest>
}
