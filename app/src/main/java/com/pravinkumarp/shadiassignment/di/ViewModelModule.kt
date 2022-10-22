package com.pravinkumarp.shadiassignment.di

import com.pravinkumarp.shadiassignment.ui.invitation.InvitationViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { (results: String) ->
        InvitationViewModel(results, repository = get())
    }
}