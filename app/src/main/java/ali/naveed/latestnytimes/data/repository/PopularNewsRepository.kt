package ali.naveed.latestnytimes.data.repository

import ali.naveed.latestnytimes.data.Resource
import ali.naveed.latestnytimes.data.ResponseHandler
import ali.naveed.latestnytimes.data.Webservice
import ali.naveed.latestnytimes.data.webservice
import ali.naveed.latestnytimes.model.ItemResult
import ali.naveed.latestnytimes.model.ResultResp

/**
 * @author Naveed Ali
 * @CreatedOn 05/03/2020
 */
class PopularNewsRepository {
    private var client: Webservice = webservice
    private val responseHandler = ResponseHandler()
    suspend fun getPopularNews(span: Int): Resource<ResultResp> {
        return try {
            val response = client.getPopularArticles(span)
            return responseHandler.handleSuccess<ResultResp>(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }
}