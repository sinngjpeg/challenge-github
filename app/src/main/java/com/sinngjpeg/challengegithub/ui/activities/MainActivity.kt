package com.sinngjpeg.challengegithub.activities

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sinngjpeg.challengegithub.R
import com.sinngjpeg.challengegithub.adapter.RepositoryAdapter
import com.sinngjpeg.challengegithub.databinding.ActivityMainBinding
import com.sinngjpeg.challengegithub.model.GithubRepository
import com.sinngjpeg.challengegithub.model.Item
import com.sinngjpeg.challengegithub.services.ApiService
import com.sinngjpeg.challengegithub.services.GithubService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager: LinearLayoutManager
    lateinit var githubAdapter: RepositoryAdapter
    lateinit var progressBar: ProgressBar
    lateinit var repos: MutableList<Item>
    var pageNumber: Int = 1
    var isLoading: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        Thread.sleep(1000L)
        setTheme(R.style.Theme_ChallengeGithub)
        setContentView(binding.root)

        recyclerView = findViewById(R.id.recycle_view)
        progressBar = findViewById(R.id.progress_bar)
        layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.layoutManager = layoutManager
        repos = arrayListOf()

        getRepos(pageNumber)
        githubScroll()
    }

    private fun githubScroll() {

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                val visibleItemCount = layoutManager.childCount
                val lastVisibleItem = layoutManager.findLastCompletelyVisibleItemPosition()
                val total = githubAdapter.itemCount

                if (!isLoading) {

                    if ((visibleItemCount + lastVisibleItem) >= total) {
                        pageNumber++
                        getRepos(pageNumber)
                    }
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    private fun getRepos(pageNumber: Int) {

        isLoading = true
        progressBar.visibility = View.VISIBLE

        val request = ApiService.buildService(GithubService::class.java)
        val call = request.getRepositories(pageNumber)

        call.enqueue(object : Callback<GithubRepository> {

            override fun onResponse(
                call: Call<GithubRepository>,
                response: Response<GithubRepository>
            ) {

                if (response.isSuccessful) {

                    repos.addAll(response.body()!!.items)
                    githubAdapter = RepositoryAdapter(repos)

                    if (pageNumber > 1) {
                        val lastVisibleItem = layoutManager.findLastCompletelyVisibleItemPosition()
                        githubAdapter.notifyItemInserted(lastVisibleItem)
                    } else {

                        recyclerView.apply {
                            setHasFixedSize(true)
                            adapter = githubAdapter
                        }
                    }
                }
                isLoading = false
                progressBar.visibility = View.GONE
            }

            override fun onFailure(call: Call<GithubRepository>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
