package ali.naveed.latestnytimes.quickhelper

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author Naveed Ali
 * @CreatedOn 05/03/2020
 */
open class BaseViewModel : ViewModel() {

     var error: MutableLiveData<String> = MutableLiveData()


}