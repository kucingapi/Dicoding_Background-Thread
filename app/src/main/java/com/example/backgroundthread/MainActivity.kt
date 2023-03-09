package com.example.backgroundthread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.backgroundthread.databinding.ActivityMainBinding
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getRandomQuote()
    }

    private fun getRandomQuote(){
        binding.progressBar.visibility = View.VISIBLE
        val client = ApiConfig.getApiService().getQuote()
        client.enqueue(object : Callback<QuoteResponse> {
            override fun onResponse(
                call: Call<QuoteResponse>,
                response: Response<QuoteResponse>
            ) {
                binding.progressBar.visibility = View.INVISIBLE
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        binding.tvQuote.text = responseBody.en
                        binding.tvAuthor.text = responseBody.author
                    }
                }
            }
            override fun onFailure(call: Call<QuoteResponse>, t: Throwable) {
                binding.progressBar.visibility = View.INVISIBLE
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

}