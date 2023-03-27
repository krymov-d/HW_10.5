package kz.kd.hw_105.di

import kz.kd.hw_105.presentation.pincode.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
}