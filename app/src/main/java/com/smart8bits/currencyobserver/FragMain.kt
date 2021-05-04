package com.smart8bits.currencyobserver

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragMain : Fragment() {
    val viewModel: ViwMdlMain by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return setBinding(inflater, container)
    }

    private fun setBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        return DataBindingUtil.inflate<ViewDataBinding>(
            inflater,
            R.layout.frag_main,
            container,
            false
        ).apply {
            lifecycleOwner = this@FragMain
            setVariable(BR.viewModel, viewModel)
        }.root
    }
}