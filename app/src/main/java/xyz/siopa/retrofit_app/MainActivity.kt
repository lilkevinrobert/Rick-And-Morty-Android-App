package xyz.siopa.retrofit_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import xyz.siopa.retrofit_app.repository.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    companion object{
        private const val BASE_URL = "https://jsonplaceholder.typicode.com";
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        retrofit.create(Api::class.java)
            .getPost(1)
            .enqueue(object: Callback<Any>{
                override fun onResponse(call: Call<Any>, response: Response<Any>) {
                   Log.i("MainActivity","on Response $response")
                }

                override fun onFailure(call: Call<Any>, t: Throwable) {
                    Log.i("MainActivity","on Response ${t.message}")
                }

            })

        //Retrieving results
        val repository = Repository()
        var viewModelFactory = MainViewModelFactory(repository = repository)
        viewModel =ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        viewModel.getPost()
        viewModel.myResponse.observe(this, Observer { response ->
            Log.d("a","User Id: ${response.userId}")
            Log.d("Response","Post Id: ${response.id}")
            Log.d("Response","Post Title: ${response.title}")
            Log.d("Response","Post Body: ${response.body}")
        })

    }
}