package com.example.tvster

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray

private const val API_KEY= "bbb63fc6fd19977101b890b73c801ce2"


class TvFragment : Fragment(), OnListFragmentInteractionListener{
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.show_list, container, false)
        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        val context = view.context
        recyclerView.layoutManager = LinearLayoutManager(context)
        updateAdapter(progressBar, recyclerView)
        return view
    }



    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api_key"] = API_KEY

        client["https://api.themoviedb.org/3/tv/popular", params, object :
            JsonHttpResponseHandler() {


            override fun onSuccess(
                statusCode: Int,
                headers: okhttp3.Headers?,
                json: JsonHttpResponseHandler.JSON
            ) {

                progressBar.hide()

                val resultsJSON: JSONArray = json.jsonObject.getJSONArray("results")
                val tvRawJSON: String = resultsJSON.toString()
                val gson = Gson()
                val arrayTvType = object : TypeToken<List<Show>>() {}.type

                val models: List<Show> = gson.fromJson(tvRawJSON, arrayTvType)
                recyclerView.adapter = TvAdapter(models, this@TvFragment)

                Log.d("Tv Fragment", "response successful")
            }

            override fun onFailure(
                statusCode: Int,
                heaeders: okhttp3.Headers?,
                errorResponse: String,
                t: Throwable?
            ) {
                t?.message?.let {
                    Log.e("Tv Fragment", errorResponse)
                }
            }


        }]
    }

    override fun onItemClick(item: Show) {
        Toast.makeText(context, "test: " + item.title, Toast.LENGTH_LONG).show()
    }
}