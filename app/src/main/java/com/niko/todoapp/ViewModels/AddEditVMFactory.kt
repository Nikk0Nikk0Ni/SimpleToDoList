package com.niko.todoapp.ViewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AddEditVMFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AddEditViewModel::class.java))
            return AddEditViewModel(application) as T
        else
            throw RuntimeException("Unknown model class $modelClass")
    }
}