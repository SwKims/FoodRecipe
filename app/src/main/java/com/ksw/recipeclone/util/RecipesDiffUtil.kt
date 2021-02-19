package com.ksw.recipeclone.util

import androidx.recyclerview.widget.DiffUtil

/**
 * Created by KSW on 2021-02-01
 */

// Result -> T (Generate)

class RecipesDiffUtil<T>(
    private val oldList: List<T>,
    private val newList: List<T>
) : DiffUtil.Callback() {

    // 현재 리스트에 노출하고 있는 listsize
    override fun getOldListSize(): Int {
        return oldList.size
    }

    // 새로 추가하거나, 갱신해야 할 listsize
    override fun getNewListSize(): Int {
        return newList.size
    }

    // 현재 리스트 값과 새로운 아이템 값이 같은지 확인
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    // 현재 리스트에 노출하고 있는 아이템과 새로운 아이템의 equals를 비교한다.
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]

    }

}