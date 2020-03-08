package ali.naveed.latestnytimes.view

import ali.naveed.latestnytimes.R
import ali.naveed.latestnytimes.model.ItemResult
import ali.naveed.latestnytimes.quickhelper.AlertUtils
import ali.naveed.latestnytimes.quickhelper.BundleExtraKeys
import ali.naveed.latestnytimes.quickhelper.mvvm.MvvmBaseActivity
import ali.naveed.latestnytimes.utils.NetworkConnection.isNetworkConnected
import ali.naveed.latestnytimes.viewmodel.MainViewModel
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author Naveed Ali
 * @CreatedOn 05/03/2020
 */
class MainNewsListActivity : MvvmBaseActivity<MainViewModel>() {

    private lateinit var adapter: NyTimesAdapter
    private var data: MutableList<ItemResult> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
        setVm()
        getPopularNews()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val span = when(item.itemId){
            R.id.today-> 1
            R.id.week->7
            R.id.month->30
            else -> 1
        }
        getPopularNews(span)
        return super.onOptionsItemSelected(item)

    }

    private fun setVm() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    private fun initUI() {

        setSupportActionBar(toolbar)
        initAdapter()
        progressBar = progressbar
    }



    private fun initAdapter() {
        adapter = NyTimesAdapter(data, object : ItemClickListener {
            override fun onClicked(position: Int) {
                launchActivity(NewsDetailActivity::class.java, Bundle().apply {
                    putParcelable(BundleExtraKeys.DATA.name, data[position])
                })
            }
        })
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter


    }


    private fun getPopularNews(span: Int=1) {
        if (isNetworkConnected(this)) {
            showProgress()
            viewModel?.popularNews(span)?.observe(this, Observer {
                it?.let {
                    dismissProgress()
                    data.clear()
                    it.itemResults?.let { it1 -> data.addAll(it1) }
                    Log.d("Main", "size = " + data.size)
                    adapter.notifyDataSetChanged()
                }
            })
        }else AlertUtils.showToast(this,R.string.no_internt)
    }
}
