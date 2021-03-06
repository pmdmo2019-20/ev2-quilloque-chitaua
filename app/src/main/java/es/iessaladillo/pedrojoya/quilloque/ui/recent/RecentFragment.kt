package es.iessaladillo.pedrojoya.quilloque.ui.recent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import es.iessaladillo.pedrojoya.quilloque.R
import es.iessaladillo.pedrojoya.quilloque.data.DatabaseContact
import es.iessaladillo.pedrojoya.quilloque.data.entity.Call
import kotlinx.android.synthetic.main.recent_fragment.*

class RecentFragment : Fragment() {

    private lateinit var recentAdapter: RecentFragmentAdapter
    private val viewmodel: RecentFragmentViewmodel by viewModels {
        RecentFragmentViewmodelFactory(DatabaseContact.getInstance(this.requireContext()).recentsDao, requireActivity().application)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.recent_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupRecyclerView()
    }

    private fun setupAdapter() {
        recentAdapter = RecentFragmentAdapter().also {
            it.onItemClickListener = { position ->  }
        }
    }
    private fun setupRecyclerView() {
        lstCalls.run {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(activity, 1)
            adapter = recentAdapter
        }
    }


    private fun showCalls(callsList: List<Call>) {
        lstCalls.post {
            recentAdapter.submitList(callsList)
            emptyView.visibility = if (callsList.isEmpty()) View.VISIBLE else View.INVISIBLE
        }
    }

}