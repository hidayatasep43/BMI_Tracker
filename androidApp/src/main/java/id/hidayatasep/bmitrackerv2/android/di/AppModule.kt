package id.hidayatasep.bmitrackerv2.android.di

import id.hidayatasep.bmitrackerv2.android.ui.form.FormViewModel
import id.hidayatasep.bmitrackerv2.android.ui.history.HistoryViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::FormViewModel)
    viewModelOf(::HistoryViewModel)
}