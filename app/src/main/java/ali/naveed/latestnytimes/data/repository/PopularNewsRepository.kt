package ali.naveed.latestnytimes.data.repository

import ali.naveed.latestnytimes.data.Webservice
import ali.naveed.latestnytimes.data.webservice

/**
 * @author Naveed Ali
 * @CreatedOn 05/03/2020
 */
class PopularNewsRepository {
    var client: Webservice = webservice

    suspend fun getPopularNews(id: Int) = client.getPopularArticles(id)
}