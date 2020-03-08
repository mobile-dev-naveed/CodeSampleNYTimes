package ali.naveed.latestnytimes.networking

import ali.naveed.latestnytimes.model.ResultResp
import ali.naveed.latestnytimes.utils.Constants.key
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author Naveed Ali
 * @CreatedOn 05/03/2020
 */
interface Webservice {
    @GET("mostpopular/v2/mostviewed/all-sections/{span}.json?api-key=$key")
    suspend fun getPopularArticles(@Path(value = "span") span: Int): ResultResp
}