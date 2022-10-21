package org.desperu.exominddemo.di.module

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

/**
 * Koin module which provides Use Cases dependencies.
 */
val useCasesModule = module {

//    /**
//     * Provides the ArticleListViewModel instance.
//     */
//    viewModel { (fragment: BaseBindingFragment) ->
//        ArticleListViewModel(
//            get(),
//            get(),
//            get { parametersOf(fragment) }
//        )
//    }
}
