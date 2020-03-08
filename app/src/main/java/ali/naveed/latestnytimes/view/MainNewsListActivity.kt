package ali.naveed.latestnytimes.view

import ali.naveed.latestnytimes.R
import ali.naveed.latestnytimes.networking.Status
import ali.naveed.latestnytimes.model.ItemResult
import ali.naveed.latestnytimes.quickhelper.AlertUtils
import ali.naveed.latestnytimes.quickhelper.BundleExtraKeys
import ali.naveed.latestnytimes.quickhelper.mvvm.MvvmBaseActivity
import ali.naveed.latestnytimes.utils.NetworkConnection.isNetworkConnected
import ali.naveed.latestnytimes.viewmodel.MainViewModel
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
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
    var searchView:SearchView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
        setVm()
        getPopularNews()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu,menu)
        searchView = menu?.findItem(R.id.search)?.actionView as SearchView
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.onQueryChanged(newText)
                return false
            }

        })


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
        adapter = NyTimesAdapter(object : ItemClickListener {
            override fun onClicked(position: Int,itemResult: ItemResult) {
                launchActivity(NewsDetailActivity::class.java, Bundle().apply {
                    putParcelable(BundleExtraKeys.DATA.name,itemResult)
                })
            }
        })
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter


    }


    private fun getPopularNews(span: Int = 1) {
        if (isNetworkConnected(this)) {
            showProgress()
            viewModel?.popularNews(span)?.observe(this, Observer {
                dismissProgress()
                when(it.status){
                    Status.SUCCESS->{
                        it.data?.let {result->
                            result.itemResults?.let { items -> adapter.setData(items)
                                searchView?.isIconified = true
                            }
                        }
                    }
                    Status.ERROR->{
                        it.message?.let { message-> AlertUtils.showToast(this@MainNewsListActivity,message)}
                    }
                }


            })
        }else AlertUtils.showToast(this,R.string.no_internt)
    }
}
