package com.example.githubrepoapi.view.ui

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.githubrepoapi.R
import com.example.githubrepoapi.service.model.Project
import com.example.githubrepoapi.service.repository.Repository
import com.example.githubrepoapi.utils.Constants.Companion.CLONE_URL
import com.example.githubrepoapi.utils.Constants.Companion.CREATED_AT
import com.example.githubrepoapi.utils.Constants.Companion.LANGUAGES
import com.example.githubrepoapi.utils.Constants.Companion.NAME
import com.example.githubrepoapi.utils.Constants.Companion.OPEN_ISSUES
import com.example.githubrepoapi.utils.Constants.Companion.UPDATED_AT
import com.example.githubrepoapi.utils.Constants.Companion.WATCHERS
import com.example.githubrepoapi.view.adapter.Adapter
import com.example.githubrepoapi.viewmodel.ProjectListViewModel
import com.example.githubrepoapi.viewmodel.ProjectViewModelFactory
import com.google.android.material.snackbar.Snackbar


class ProjectListFragment : Fragment(), Adapter.OnItemClickListener {

    private lateinit var viewModel: ProjectListViewModel
    private lateinit var recAdapter: Adapter
    private lateinit var swipeToRefresh: SwipeRefreshLayout
    private lateinit var mainMenu: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_project_list, container, false)

        recAdapter = Adapter(this)

        swipeToRefresh = view.findViewById(R.id.swipeToRefresh)
        mainMenu = view.findViewById(R.id.recycler_view)
        mainMenu.apply {
            mainMenu.layoutManager = LinearLayoutManager(this.context)
            mainMenu.adapter = recAdapter
            mainMenu.setHasFixedSize(true)
        }

        requestResponse()
        viewModel.myResponse.observe(this, Observer { response ->
            recAdapter.submitList(response.body())
        })

        swipeToRefresh.setOnRefreshListener {
            requestResponse()
        }

        return view
    }

    override fun onItemClick(project: Project) {
        val bundle = Bundle()
        bundle.putString(NAME, project.name)
        bundle.putString(LANGUAGES, project.languages)
        bundle.putInt(WATCHERS, project.watchers)
        bundle.putInt(OPEN_ISSUES, project.openIssues)
        bundle.putString(CREATED_AT, project.createdAt)
        bundle.putString(UPDATED_AT, project.updatedAt)
        bundle.putString(CLONE_URL, project.cloneUrl)

        val fragment = ProjectFragment()
        fragment.arguments = bundle
        val fragmentManager = activity!!.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.addToBackStack("ProjectListFragment")
        fragmentTransaction.commit()
    }

    private fun requestResponse() {
        viewModel =
            ViewModelProvider(
                this,
                ProjectViewModelFactory(Repository())
            ).get(ProjectListViewModel::class.java)
        viewModel.getProjects()

        if (!checkConnectivity()) {
            val snackBar = Snackbar.make(
                activity!!.findViewById(android.R.id.content),
                "No Internet Connection", Snackbar.LENGTH_LONG
            )
            snackBar.show()
        }

        swipeToRefresh.isRefreshing = false
    }

    private fun checkConnectivity(): Boolean {
        val cm =
            context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null
    }
}