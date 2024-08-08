import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anew.retrofitdataretrievepractice.ApiInterface
import com.anew.retrofitdataretrievepractice.CountryInfo
import com.anew.retrofitdataretrievepractice.DataHolder
import com.anew.retrofitdataretrievepractice.MyAdaptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class RetrofitWorkService(private val recyclerView: RecyclerView) {

    private val Baseurl = "https://disease.sh/v2/"

    fun getdata(progressBar: ProgressBar, imageView: ImageView) {
        progressBar.visibility = View.VISIBLE  // Show progress bar

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Baseurl)
            .build()
            .create(ApiInterface::class.java)

        Log.d("RetrofitWorkService", "Requesting data...")

        val dataBuilder = retrofitBuilder.getCountrYtData()
        dataBuilder.enqueue(object : Callback<List<CountryInfo>?> {
            override fun onResponse(
                call: Call<List<CountryInfo>?>,
                response: Response<List<CountryInfo>?>
            ) {
                // Hide progress bar when data is fetched
                progressBar.visibility = View.GONE

                // show search button when data is fetched
                imageView.visibility = View.VISIBLE


                if (response.isSuccessful && response.body() != null) {
                    val countryList = response.body()!!
                    DataHolder.countryList = countryList  // Store data in the singleton
                    val myAdaptor = MyAdaptor(recyclerView.context, countryList)
                    recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
                    recyclerView.adapter = myAdaptor
                    Log.d("RetrofitWorkService", "Adapter attached successfully.")
                } else {
                    Log.d("RetrofitWorkService", "Response is null or not successful.")
                }
            }

            override fun onFailure(call: Call<List<CountryInfo>?>, t: Throwable) {
                progressBar.visibility = View.GONE  // Hide progress bar on failure
                Log.d("RetrofitWorkService", "Failed to load API data: " + t.message)
            }
        })
    }
}
