package com.example.weatherapp.view.fragments.searchresult

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.ResultInfo
import com.example.weatherapp.view.MainActivity
import kotlinx.android.synthetic.main.fragment_search_result.view.*


class SearchResultFragment : Fragment() {

    companion object {

        const val EXTRA_WEATHER_DETAILS = "EXTRA_WEATHER_DETAILS"

        fun newInstance(weatherDetails: List<ResultInfo>): SearchResultFragment {
            var fragment = SearchResultFragment()
            var bundle = Bundle()
            bundle.putParcelableArrayList(EXTRA_WEATHER_DETAILS, ArrayList<Parcelable>(weatherDetails))
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val v =  inflater.inflate(R.layout.fragment_search_result, container, false)

        val weatherDetails =
            arguments!!.getParcelableArrayList<ResultInfo>(EXTRA_WEATHER_DETAILS)!!

        val adapter = SearchResultAdapter(weatherDetails, onItemClickListener)
        val viewManager = LinearLayoutManager(activity)

        val recycler = (v.recyclerView as RecyclerView)
        recycler.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            this.adapter = adapter
        }

        val mDividerItemDecoration = DividerItemDecoration(
            recycler.context,
            viewManager.orientation
        )
        recycler.addItemDecoration(mDividerItemDecoration)

        return v
    }

    private val onItemClickListener = { resultInfo: ResultInfo ->
            (activity!! as MainActivity).viewModel.cityToForecast.value = resultInfo
    }
}