package com.nasa.pic.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nasa.pic.NasaApplication
import com.nasa.pic.R
import com.nasa.pic.data.entity.NasaEntity
import com.nasa.pic.data.remote.Result
import com.nasa.pic.databinding.FragmentNasaBinding
import com.nasa.pic.presentation.binding.bindImageFromUrl
import com.nasa.pic.presentation.viewmodel.NasaViewModel
import com.nasa.pic.utils.DateUtils
import com.nasa.pic.utils.NetworkUtils
import com.nasa.pic.utils.hide
import com.nasa.pic.utils.show
import java.util.*
import javax.inject.Inject

class NasaFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding:FragmentNasaBinding

    val nasaViewModel: NasaViewModel by viewModels { viewModelFactory }

    companion object {
        fun newInstance() = NasaFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        NasaApplication.instance.getNasaAppComponent().inject(this)

        binding = DataBindingUtil.inflate<FragmentNasaBinding>(
            inflater, R.layout.fragment_nasa, container, false
        )

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        observeNasaApi(binding,nasaViewModel)
    }

    private fun observeNasaApi(binding: FragmentNasaBinding,nasaViewModel:NasaViewModel) {
        nasaViewModel.nasaApi.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    result.data?.let { bindView(binding, it) }
                }
                Result.Status.LOADING -> binding.progressBar.show()
                Result.Status.ERROR -> {
                    binding.progressBar.hide()
//                    Snackbar.make(binding.titleTv, result.message!!, Snackbar.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun bindView(binding: FragmentNasaBinding,nasaApodEntityList: List<NasaEntity>) {
        if (!nasaApodEntityList.isEmpty()) {
            val nasaApodEntity = nasaApodEntityList.get(0);
            nasaApodEntity.apply {
                bindImageFromUrl(binding.apodImageview, nasaApodEntity.url)
                binding.titleTv.text = nasaApodEntity.title
                binding.explainTv.text = nasaApodEntity.explanation
                binding.progressBar.hide()
            }
            
            if (!DateUtils.convertStringToDate(DateUtils.convertDateFormat(nasaApodEntity.date))
                    .equals(DateUtils.convertStringToDate(DateUtils.getCurrentDate(
                    Calendar.getInstance()))) && !NetworkUtils.isNetworkAvailable(
                    binding.titleTv.context
                )
            ) {
                Toast.makeText(
                    binding.titleTv.context,
                    getString(R.string.last_image_we_have),
                    Toast.LENGTH_LONG
                ).show()
            }
        } else if (!NetworkUtils.isNetworkAvailable(binding.titleTv.context)) {
            Toast.makeText(
                binding.titleTv.context,
                getString(R.string.no_internet),
                Toast.LENGTH_SHORT
            ).show()
            binding.progressBar.hide()
        }

    }
}
