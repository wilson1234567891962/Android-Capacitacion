package com.co.bicicletas.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.co.bicicletas.R
import com.co.bicicletas.databinding.FragmentHomeBinding
import com.co.bicicletas.model.dto.Album
import com.co.bicicletas.view.adapter.AlbumAdapter
import com.co.bicicletas.viewmodel.AlbumViewModel

class AlbumFragment : Fragment() {

    private lateinit var homeViewModel: AlbumViewModel

    private var mBinding: FragmentHomeBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(AlbumViewModel::class.java)
        mBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return mBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * Add an observer on the LiveData returned by getFavoriteDishesList.
         * The onChanged() method fires when the observed data changes and the activity is in the foreground.
         */


        // Set the LayoutManager that this RecyclerView will use.
        mBinding!!.rvAlbumList.layoutManager =
            GridLayoutManager(requireActivity(), 2)
        // Adapter class is initialized and list is passed in the param.
        val adapter = AlbumAdapter(this@AlbumFragment)
        // adapter instance is set to the recyclerview to inflate the items.
        mBinding!!.rvAlbumList.adapter = adapter
        var albums = listOf<Album>(

        )
        if (albums.isNotEmpty()) {
            mBinding!!.rvAlbumList.visibility = View.VISIBLE
            mBinding!!.tvAlbumAvailable.visibility = View.GONE

            adapter.albumList(albums)
        } else {
            mBinding!!.rvAlbumList.visibility = View.GONE
            mBinding!!.tvAlbumAvailable.visibility = View.VISIBLE
        }
    }
}
