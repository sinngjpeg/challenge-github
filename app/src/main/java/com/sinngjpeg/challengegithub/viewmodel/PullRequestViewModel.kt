package com.sinngjpeg.challengegithub.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sinngjpeg.challengegithub.model.PullRequest
import com.sinngjpeg.challengegithub.repository.GitHubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PullRequestViewModel @Inject constructor(private val gitHubRepository: GitHubRepository) : ViewModel() {

    private val _pullRequestList = MutableLiveData<List<PullRequest>>()
    val pullRequestList: LiveData<List<PullRequest>>
        get() = _pullRequestList

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable>
        get() = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    init {
        _loading.value = true
    }

    fun getPullRequests(
        owner: String,
        repository: String,
    ) {
        viewModelScope.launch {
            val result =
                kotlin.runCatching { gitHubRepository.getPullRequestList(owner, repository) }
            result.onSuccess { _pullRequestList.postValue(it) }
            result.onFailure { _error.value = it }
        }
    }
}