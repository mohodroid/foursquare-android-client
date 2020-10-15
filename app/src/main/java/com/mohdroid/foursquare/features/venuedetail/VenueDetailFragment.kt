package com.mohdroid.foursquare.features.venuedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.mohdroid.foursquare.features.common.FrgParent
import com.mohdroid.foursquare.features.common.RootApp
import com.mohdroid.foursquare.utils.LoadImageHelper
import com.mohdroid.domain.common.getPhotoUrl
import kotlinx.android.synthetic.main.fragment_venue_detail.*
import javax.inject.Inject

class VenueDetailFragment : FrgParent<VenueDetailViewModel>() {

    @Inject
    lateinit var factory: VenueDetailViewModel.Factory

    @Inject
    lateinit var loadImageHelper: LoadImageHelper

    val args: VenueDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.venueId = args.venueId
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_venue_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.showVenueDetil.observe(viewLifecycleOwner, Observer {
            title_text.text = it.name
            rate_text.text = it.rating.toString()
            desc_text.text = it.description
            loadImageHelper.loadImage(it.bestPhoto!!.getPhotoUrl(), venue_image)
        })
    }

    override fun getFactory(): ViewModelProvider.Factory = factory

    override fun getFactoryClass(): Class<VenueDetailViewModel> = VenueDetailViewModel::class.java

    override fun showProgress() {
        prg.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        prg.visibility = View.INVISIBLE
    }

    override fun inject() {
        RootApp.getComponent().inject(this)
    }
}