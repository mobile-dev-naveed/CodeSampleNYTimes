package ali.naveed.latestnytimes.quickhelper

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

/**
 * @author Naveed Ali
 * @CreatedOn 05/03/2020
 */
class ViewManager internal constructor(activity: AppCompatActivity) : IViewManager {


    private var activityContext: AppCompatActivity = activity

    override fun addFragment(viewId: Int, fragment: Fragment) {
        activityContext.supportFragmentManager.inTransaction {
            add(viewId, fragment)
        }
    }

    override fun addFragmentToStack(viewId: Int, fragment: Fragment) {
        activityContext.supportFragmentManager.inTransaction {
            add(viewId, fragment)
            addToBackStack(null)
        }

    }

    override fun replaceFragment(viewId: Int, fragment: Fragment) {
        activityContext.supportFragmentManager.inTransaction {
            replace(viewId, fragment)
        }

    }

    override fun replaceFragmentToStack(viewId: Int, fragment: Fragment) {
        activityContext.supportFragmentManager.inTransaction {
            replace(viewId, fragment)
            addToBackStack(null)
        }
    }

    override fun clearFragmentStack() {
        val fm = activityContext.supportFragmentManager
        for (i in 0 until fm.backStackEntryCount) {
            fm.popBackStack()
        }
    }


    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }



    override fun launchActivity(activityClass: Class<*>, bundleData: Bundle?, withFinish: Boolean) {
        val intent = Intent(activityContext, activityClass)
        bundleData?.let {
            intent.putExtra(BundleExtraKeys.BUNDLE.name, it)
        }
        activityContext.startActivity(intent)
        if (withFinish) activityContext.finish()
    }


}
