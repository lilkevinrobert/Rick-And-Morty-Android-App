package xyz.siopa.retrofit_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import coil.transform.RoundedCornersTransformation
import com.squareup.moshi.Moshi
import com.squareup.picasso.Picasso
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import xyz.siopa.retrofit_app.dto.response.GetCharacterByIdResponse
import xyz.siopa.retrofit_app.repository.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    companion object{
        private const val BASE_URL = "https://rickandmortyapi.com/api/";
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView:TextView = findViewById(R.id.textView)
        val imageView: ImageView = findViewById(R.id.imageView)


        /*
        * Using Moshi converter factory instead of GsonConverter
        */
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            //.addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()


        retrofit.create(RickAndMortyService::class.java)
            .getCharacterById(characterId = 1)
            .enqueue(object : Callback<GetCharacterByIdResponse>{
                override fun onResponse(call: Call<GetCharacterByIdResponse>, response: Response<GetCharacterByIdResponse>) {
                    Log.i("MainActivity","on Response $response")

                    //Returns true if code() is in the range [200..300)
                    if(!response.isSuccessful){
                        Toast.makeText(this@MainActivity, "Unsuccessful Network call", Toast.LENGTH_SHORT).show()
                        return
                    }

                    val response = response.body()!!
                    textView.text = response.name

                    /*Picasso
                        .get()
                        .load(response.image)
                        .into(imageView);*/

                    /*val request = ImageRequest.Builder(context)
                        .data("https://www.example.com/image.jpg")
                        .crossfade(true)
                        .target(imageView)
                        .build()
                    */

                    imageView
                        .load(response.image){
                            crossfade(true)
                            crossfade(500)
                            transformations(RoundedCornersTransformation(30f))
                        }


                }

                override fun onFailure(call: Call<GetCharacterByIdResponse>, t: Throwable) {
                    Log.i("MainActivityFailure","on Failure ${t.message}")
                }
            })


/*        retrofit.create(Api::class.java)
            .getPost(1)
            .enqueue(object: Callback<Any>{
                override fun onResponse(call: Call<Any>, response: Response<Any>) {
                   Log.i("MainActivity","on Response $response")
                }

                override fun onFailure(call: Call<Any>, t: Throwable) {
                    Log.i("MainActivity","on Response ${t.message}")
                }

            })*/

/*        //Retrieving results
        val repository = Repository()
        var viewModelFactory = MainViewModelFactory(repository = repository)
        viewModel =ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        viewModel.getPost()
        viewModel.myResponse.observe(this, Observer { response ->
            Log.d("a","User Id: ${response.userId}")
            Log.d("Response","Post Id: ${response.id}")
            Log.d("Response","Post Title: ${response.title}")
            Log.d("Response","Post Body: ${response.body}")
        })*/

    }
}