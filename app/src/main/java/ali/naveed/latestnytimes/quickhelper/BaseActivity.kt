package ali.naveed.latestnytimes.quickhelper

import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * @author Naveed Ali
 * @CreatedOn 05/03/2020
 */
open class BaseActivity : AppCompatActivity(), IViewManager {


    lateinit var viewManager: IViewManager
    var progressBar: ProgressBar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewManager = ViewManager(this)

    }


    override fun addFragment(viewId: Int, fragment: Fragment) {
        viewManager.addFragment(viewId, fragment)
    }

    override fun addFragmentToStack(viewId: Int, fragment: Fragment) {
        viewManager.addFragmentToStack(viewId, fragment)
    }

    override fun replaceFragment(viewId: Int, fragment: Fragment) {
        viewManager.replaceFragment(viewId, fragment)
    }

    override fun replaceFragmentToStack(viewId: Int, fragment: Fragment) {
        viewManager.replaceFragmentToStack(viewId, fragment)
    }

    override fun clearFragmentStack() {
        viewManager.clearFragmentStack()
    }

    override fun launchActivity(activityClass: Class<*>, bundleData: Bundle?, withFinish: Boolean) {
        viewManager.launchActivity(activityClass, bundleData, withFinish)
    }

    fun showProgress() {
        progressBar?.visibility = View.VISIBLE
    }

    fun dismissProgress() {
        progressBar?.visibility = View.GONE
    }


}