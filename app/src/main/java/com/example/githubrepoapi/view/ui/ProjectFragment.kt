package com.example.githubrepoapi.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.githubrepoapi.R
import com.example.githubrepoapi.utils.Constants.Companion.CLONE_URL
import com.example.githubrepoapi.utils.Constants.Companion.CREATED_AT
import com.example.githubrepoapi.utils.Constants.Companion.LANGUAGES
import com.example.githubrepoapi.utils.Constants.Companion.NAME
import com.example.githubrepoapi.utils.Constants.Companion.OPEN_ISSUES
import com.example.githubrepoapi.utils.Constants.Companion.UPDATED_AT
import com.example.githubrepoapi.utils.Constants.Companion.WATCHERS

private const val TAG = "ProjectFragment"

class ProjectFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_project, container, false)

        val name = arguments!!.getString(NAME)
        val languages = "Languages: " + arguments!!.getString(LANGUAGES)
        val watchers = "Watchers: " + arguments!!.getInt(WATCHERS)
        val openIssues = "Open Issues: " + arguments!!.getInt(OPEN_ISSUES)
        val createdAt = "Created At: " + arguments!!.getString(CREATED_AT)
        val updatedAt = "Updated At: " + arguments!!.getString(UPDATED_AT)
        val cloneUrl = "Clone Url: " + arguments!!.getString(CLONE_URL)

        val textName: TextView = view.findViewById(R.id.text_view_name)
        val textLanguages: TextView = view.findViewById(R.id.text_view_languages)
        val textWatcher: TextView = view.findViewById(R.id.text_view_watcher)
        val textOpenIssues: TextView = view.findViewById(R.id.text_view_open_issues)
        val textCreatedAt: TextView = view.findViewById(R.id.text_view_created_at)
        val textUpdatedAt: TextView = view.findViewById(R.id.text_view_updated_at)
        val textCloneUrl: TextView = view.findViewById(R.id.text_view_clone_url)

        textName.text = name
        textLanguages.text = languages
        textWatcher.text = watchers
        textOpenIssues.text = openIssues
        textCreatedAt.text = createdAt
        textUpdatedAt.text = updatedAt
        textCloneUrl.text = cloneUrl

        return view
    }
}