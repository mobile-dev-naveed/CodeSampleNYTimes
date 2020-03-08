package ali.naveed.latestnytimes.view

import ali.naveed.latestnytimes.R
import ali.naveed.latestnytimes.model.ItemResult
import ali.naveed.latestnytimes.quickhelper.BundleExtraKeys
import ali.naveed.latestnytimes.quickhelper.mvvm.MvvmBaseActivity
import android.os.Bundle

/**
 * @author Naveed Ali
 * @CreatedOn 07/03/2020
 */
class NewsDetailActivity : MvvmBaseActivity<Any>() {

    private var item:ItemResult? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)
        parseExtras()
        initUI()
        setVm()

    }
    private fun parseExtras(){
        item = intent.extras?.getBundle(BundleExtraKeys.BUNDLE.name)?.getParcelable(BundleExtraKeys.DATA.name)
    }

    private fun initUI(){
        addFragment(R.id.fragment_container,DetailFragment.getNewInstance(item))

    }

    private fun setVm(){
        //viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }



}
