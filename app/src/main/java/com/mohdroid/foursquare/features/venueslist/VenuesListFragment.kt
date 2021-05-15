package com.mohdroid.foursquare.features.venueslist

import android.content.Context
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mohdroid.foursquare.R
import com.mohdroid.foursquare.features.MainActivity
import com.mohdroid.foursquare.features.common.EventObserver
import com.mohdroid.foursquare.features.common.FrgParent
import com.mohdroid.foursquare.features.common.RootApp
import kotlinx.android.synthetic.main.fragment_venue_list.*
import javax.inject.Inject

class VenuesListFragment : FrgParent<VenuesListViewModel>(), MainActivity.LocationCallback {


    @Inject
    lateinit var factory: VenuesListViewModel.Factory

    private var pastVisibleItems: Int = 0
    private var visibleItemCount: Int = 0
    private var totalItemCount: Int = 0
    private lateinit var layoutManager: LinearLayoutManager
    private var onLoading: Boolean = false
    private lateinit var location: Location
    var adapter: VenuesListAdapter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) {
            context.setLocationCallback(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_venue_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        viewModel.newVenues.observe(viewLifecycleOwner, Observer {
            if (adapter == null) {
                adapter =
                    VenuesListAdapter(
                        ArrayList(it),
                        actParent
                    ) { id ->
                        viewModel.onVenueItemClicked(id)
                    }
                rv.adapter = adapter
            } else {
                val positionStart = adapter?.itemCount!!
                adapter?.addRange(positionStart, ArrayList(it))
                adapter?.notifyItemRangeInserted(positionStart, it.count())
            }
            onLoading = false
        })
        viewModel.goToVenueDetailPage.observe(viewLifecycleOwner,
            EventObserver {
                val directions =
                    VenuesListFragmentDirections.actionVenuesListFragmentToVenueDetailFragment(
                        it
                    )
                findNavController().navigate(directions)
            })
        rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    visibleItemCount = layoutManager.childCount
                    totalItemCount = layoutManager.itemCount
                    pastVisibleItems = layoutManager.findFirstVisibleItemPosition()
                    if (!onLoading) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            onLoading = true
                            viewModel.onLoadMore(location)
                        }
                    }
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        rv.adapter = adapter
    }

    override fun getLocationResult(location: Location) {
        this.location = location
        viewModel.onUserLocationReceived(location)
    }

    private fun initRecyclerView() {
        layoutManager = LinearLayoutManager(actParent, RecyclerView.VERTICAL, false)
        rv.layoutManager = layoutManager
        rv.setHasFixedSize(true)
    }

    override fun getFactory(): ViewModelProvider.Factory = factory

    override fun getFactoryClass(): Class<VenuesListViewModel> = VenuesListViewModel::class.java

    override fun inject() {
        (context?.applicationContext as RootApp).appGraph.inject(this)
    }

    override fun showProgress() {
        prg.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        prg.visibility = View.INVISIBLE
    }


}