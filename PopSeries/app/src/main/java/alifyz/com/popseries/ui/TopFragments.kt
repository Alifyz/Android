package alifyz.com.popseries.ui

import alifyz.com.popseries.BuildConfig
import alifyz.com.popseries.R
import alifyz.com.popseries.adapter.PopularAdapter
import alifyz.com.popseries.adapter.TopAdapter
import alifyz.com.popseries.model.TopModel
import alifyz.com.popseries.network.RetrofitHelper
import alifyz.com.popseries.network.SeriesEndpoint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_top.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TopFragments : Fragment() {

    val gridLayout = GridLayoutManager(
            context,
            2,
            GridLayoutManager.VERTICAL,
            false)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_top, container, false)
    }

    override fun onResume() {
        super.onResume()
        progress.visibility = View.VISIBLE
        recyclerview_top.visibility = View.GONE

        val retrofit = RetrofitHelper.getInstance()

        val endpoint = retrofit.create(SeriesEndpoint::class.java)
        val call = endpoint.getTopSeries(BuildConfig.API_KEY)

        call.enqueue(object : Callback<TopModel> {
            override fun onResponse(call: Call<TopModel>?, response: Response<TopModel>?) {
                Log.d("RetrofitHelper: ", "Success")
                storeSeries(response)
            }

            override fun onFailure(call: Call<TopModel>?, t: Throwable?) {
                Log.d("RetrofitHelper: ", "Failed")
                TODO("not implemented")
            }
        })
    }

    fun storeSeries(response: Response<TopModel>?) {
        val response_body = response?.body()
        progress.visibility = View.GONE
        recyclerview_top.visibility = View.VISIBLE

        recyclerview_top.layoutManager = gridLayout
        recyclerview_top.adapter = TopAdapter(context!!, response_body!!)
    }
}