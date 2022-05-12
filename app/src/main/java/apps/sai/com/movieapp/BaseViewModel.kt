package apps.sai.com.movieapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import apps.sai.com.movieapp.data.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel : ViewModel() {
    val errorLiveData = MutableLiveData<String>()

}