package com.example.llmproject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _listItems = MutableLiveData<List<ListItem>>()
    val listItems: LiveData<List<ListItem>> = _listItems

    init {
        // 더미 데이터 로드
        loadListItems()
    }

    private fun loadListItems() {
        // 실제로는 서버에서 데이터를 가져오거나 로컬 DB에서 로드할 수 있습니다.
        val items = listOf(
            ListItem("Item 1", "Description 1"),
            ListItem("Item 2", "Description 2"),
            ListItem("Item 3", "Description 3")
        )
        _listItems.value = items
    }
}

data class ListItem(val title: String, val description: String)