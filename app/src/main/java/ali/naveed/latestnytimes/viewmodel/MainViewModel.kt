package ali.naveed.latestnytimes.viewmodel

import ali.naveed.latestnytimes.data.repository.PopularNewsRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

class MainViewModel : ViewModel() {
    private val repository: PopularNewsRepository = PopularNewsRepository()

    fun popularNews(span:Int = 1) = liveData(Dispatchers.IO) {
        val retrivedTodo = repository.getPopularNews(span)
        emit(retrivedTodo)
    }

}