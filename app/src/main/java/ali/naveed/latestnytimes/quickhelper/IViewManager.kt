package ali.naveed.latestnytimes.quickhelper

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment

/**
 * @author Naveed Ali
 * @CreatedOn 05/03/2020
 */
interface IViewManager {

    fun addFragment(viewId: Int, fragment: Fragment)

    fun addFragmentToStack(viewId: Int, fragment: Fragment)

    fun replaceFragment(viewId: Int, fragment: Fragment)

    fun replaceFragmentToStack(viewId: Int, fragment: Fragment)

    fun clearFragmentStack()

    fun launchActivity(activityClass: Class<*>, bundleData: Bundle? = null, withFinish: Boolean = false)
}
