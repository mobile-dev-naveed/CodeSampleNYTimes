package ali.naveed.latestnytimes.view

import android.os.Bundle
import ali.naveed.latestnytimes.R
import ali.naveed.latestnytimes.quickhelper.SplashBaseActivity


class SplashActivity : SplashBaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        runDefaultTimerDelay()
    }

    override fun onTimerEnded(){
        launchActivity(MainNewsListActivity::class.java,null, true)
    }

}
