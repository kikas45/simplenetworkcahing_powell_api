package com.example.simplenetworkcahing_powell_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simplenetworkcahing_powell_api.databinding.ActivityMainBinding
import com.example.simplenetworkcahing_powell_api.features.RestaurantAdapter
import com.example.simplenetworkcahing_powell_api.restaurants.RestaurantViewModel
import com.example.simplenetworkcahing_powell_api.util.Resource
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var countdownTimer: CountDownTimer
    private var seconds = 4L


    private lateinit var viewModel: RestaurantViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var textViewError: TextView
    private var resturnatApater: RestaurantAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        progressBar = findViewById(R.id.progress_bar)
        textViewError = findViewById(R.id.text_view_error)

        viewModel = ViewModelProvider(this).get(RestaurantViewModel::class.java)

        resturnatApater = RestaurantAdapter()

        recyclerView.apply {
            adapter = resturnatApater
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        makeAPIRequest()
    }


    private fun makeAPIRequest() {


        viewModel.restaurants.observe(this@MainActivity, Observer { result ->
            resturnatApater?.submitList(result.data)

            progressBar.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
            textViewError.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
            textViewError.text = result.error?.localizedMessage

            if (result is Resource.Error && result.data.isNullOrEmpty()) {
                Toast.makeText(applicationContext, "re - connecting..", Toast.LENGTH_SHORT).show()

                attemptRequestAgain(seconds)
            }

        })


    }


    private fun attemptRequestAgain(seconds: Long) {
        countdownTimer = object : CountDownTimer(seconds * 1010, 1000) {

            override fun onFinish() {
                makeAPIRequest()
              //  Thread.sleep(2000)
               // recreate()
                finish()
                startActivity(intent)
                overridePendingTransition(0,0)
                countdownTimer.cancel()
                this@MainActivity.seconds += 3

            }

            override fun onTick(p0: Long) {
                Toast.makeText(applicationContext, "Attempting in ${p0 / 1000}", Toast.LENGTH_SHORT).show()
            }

        }

        countdownTimer.start()
    }
}