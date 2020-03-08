package ali.naveed.latestnytimes.networking

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Naveed Ali
 * @CreatedOn 05/03/2020
 */
val webservice: Webservice by lazy {
    Retrofit.Builder()
        .baseUrl("https://api.nytimes.com/svc/") //TODO move to constants
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build().create(Webservice::class.java)
}