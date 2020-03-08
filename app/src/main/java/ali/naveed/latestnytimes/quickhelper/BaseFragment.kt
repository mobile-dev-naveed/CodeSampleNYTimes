package ali.naveed.latestnytimes.quickhelper

import androidx.fragment.app.Fragment
import ali.naveed.latestnytimes.quickhelper.BaseActivity

/**
 * @author Naveed Ali
 * @CreatedOn 05/03/2020
 */
abstract class BaseFragment : Fragment() {

    //abstract fun addListeners();

    open fun getActivityContext(): BaseActivity {
        return activity as BaseActivity
    }

    fun showProgress() {
        (activity as BaseActivity).showProgress()
    }

    fun dismissProgress() {
        (activity as BaseActivity).dismissProgress()
    }
}