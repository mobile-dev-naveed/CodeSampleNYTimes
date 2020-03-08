package ali.naveed.latestnytimes.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ali.naveed.latestnytimes.R
import ali.naveed.latestnytimes.model.ItemResult
import ali.naveed.latestnytimes.quickhelper.BaseFragment
import ali.naveed.latestnytimes.quickhelper.BundleExtraKeys
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : BaseFragment() {

    private var data:ItemResult? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArguments()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    private fun parseArguments(){
        data = arguments?.getParcelable(BundleExtraKeys.DATA.name)
    }

    private fun loadData(){
        data?.let {
            titleTv.text = it.title?:""
            byTv.text = it.byline?:""
            descTv.text = it._abstract?:"--"
            it.media?.firstOrNull()?.mediaMetadata?.lastOrNull()?.url?.let{mediaUrl->
                Glide.with(requireContext()).load(mediaUrl).centerCrop().into(mediaImv)
            }

        }
    }


    companion object{
        fun getNewInstance(item: ItemResult?):DetailFragment{
            val fragment = DetailFragment().apply { arguments = Bundle().apply { putParcelable(BundleExtraKeys.DATA.name,item) } }
            return fragment
        }
    }
}
