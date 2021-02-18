package com.ksw.recipeclone.bindingadapters

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.material.card.MaterialCardView
import com.ksw.recipeclone.data.database.entities.FoodJokeEntity
import com.ksw.recipeclone.models.FoodJoke
import com.ksw.recipeclone.util.NetworkResult

/**
 * Created by KSW on 2021-02-17
 */

class FoodJokeBinding {

    companion object {

        @BindingAdapter("readApiResponse3", "readDatabase3", requireAll = false)
        @JvmStatic
        fun setCardProgressVisibility(
            view: View,
            apiResponse: NetworkResult<FoodJoke>?,
            database: List<FoodJokeEntity>?
        ) {
            when(apiResponse) {
                is NetworkResult.Loading -> {
                    when(view) {
                        is ProgressBar -> {
                            view.visibility = View.VISIBLE
                        }
                        is MaterialCardView -> {
                            view.visibility = View.INVISIBLE
                        }
                    }
                }

                is NetworkResult.Error -> {
                    when(view) {
                        is ProgressBar -> {
                            view.visibility = View.INVISIBLE
                        }
                        is MaterialCardView -> {
                            view.visibility = View.VISIBLE
                            if (database != null) {
                                if (database.isEmpty()) {
                                    view.visibility = View.INVISIBLE
                                }
                            }
                        }
                    }
                }

                is NetworkResult.Success -> {
                    when(view) {
                        is ProgressBar -> {
                            view.visibility = View.INVISIBLE
                        }
                        is MaterialCardView -> {
                            view.visibility = View.VISIBLE
                        }
                    }
                }

            }
        }

        // 앱 첫 실행시 인터넷이 안켜져있을경우 에러.
        @BindingAdapter("readApiResponse4", "readDatabase4", requireAll = true)
        @JvmStatic
        fun setErrorViewVisibility(
            view : View,
            apiResponse: NetworkResult<FoodJoke>?,
            database: List<FoodJokeEntity>?
        ) {
            if (database != null) {
                view.visibility = View.VISIBLE
                if (view is TextView) {
                    if (apiResponse != null) {
                        view.text = apiResponse.message.toString()
                    }
                }
            }
            if (apiResponse is NetworkResult.Success) {
                view.visibility = View.INVISIBLE
            }
        }



    }
}