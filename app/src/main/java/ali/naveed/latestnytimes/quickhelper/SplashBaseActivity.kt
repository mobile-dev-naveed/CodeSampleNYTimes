package ali.naveed.latestnytimes.quickhelper


import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.View.*


/**
 * @author Naveed Ali
 * @CreatedOn 05/03/2020
 */
abstract class SplashBaseActivity : BaseActivity() {

    private val defaultTimeDelay: Long = 2500 // 2.5 seconds


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showSystemUI()
    }

    private fun showSystemUI() {
//        val decorView = window.decorView
//        decorView.systemUiVisibility = (SYSTEM_UI_FLAG_LAYOUT_STABLE
//                or SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                or SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                or SYSTEM_UI_FLAG_FULLSCREEN
//                )
    }

    protected fun runDefaultTimerDelay() {
        runTimerDelay(defaultTimeDelay)
    }

    protected fun runTimerDelay(millis: Long) {
        Handler().postDelayed({
            onTimerEnded()
        }, millis)
    }

    abstract fun onTimerEnded()


    override fun onDestroy() {
        super.onDestroy()
    }

}