package com.co.bicicletas.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.co.bicicletas.R
import com.co.bicicletas.viewmodel.AlbumViewModel
import com.co.bicicletas.viewmodel.CollectionViewModel

class CollectorFragment: Fragment() {

    private lateinit var homeViewModel: CollectionViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeViewModel = ViewModelProvider(this).get(CollectionViewModel::class.java)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
}