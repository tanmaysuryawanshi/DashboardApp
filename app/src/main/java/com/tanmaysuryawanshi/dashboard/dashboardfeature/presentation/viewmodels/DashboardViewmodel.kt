package com.tanmaysuryawanshi.dashboard.dashboardfeature.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tanmaysuryawanshi.dashboard.core.util.Resource
import com.tanmaysuryawanshi.dashboard.dashboardfeature.domain.model.ChartItemModel
import com.tanmaysuryawanshi.dashboard.dashboardfeature.domain.model.DashboardData
import com.tanmaysuryawanshi.dashboard.dashboardfeature.domain.model.TopLink
import com.tanmaysuryawanshi.dashboard.dashboardfeature.domain.repository.DashboardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class DashboardViewmodel @Inject constructor(private val repository: DashboardRepository) : ViewModel() {

    private val _data = MutableLiveData<Resource<DashboardData>>()
    val data: LiveData<Resource<DashboardData>> get() = _data
    var greeting = MutableLiveData<String>()
var todaysClicks=MutableLiveData<String>()
    var topSource=MutableLiveData<String>()
    var topLocation=MutableLiveData<String>()
    var bestTime=MutableLiveData<String>()



    var topLinks=MutableLiveData<List<TopLink>>()
var chartItems=MutableLiveData<List<Int>>()
   var contactNumber=MutableLiveData<String>()


   suspend fun getGreeting() {
        val calendar = Calendar.getInstance()
        val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)

        val greet = when (hourOfDay) {
            in 6..11 -> "Good Morning"
            in 12..16 -> "Good Afternoon"
            in 17..21 -> "Good Evening"
            else -> "Good Night"
        }
        Log.d("viewmodel", "getGreeting: $greet")

            greeting.value=greet




    }

  suspend fun fetchData() {
       Log.d("viewmodel", "fetchData: ")
        viewModelScope.launch {
            _data.value = Resource.Loading()
            _data.value = repository.getDashboardData()
todaysClicks.value= _data.value!!.data?.today_clicks.toString().takeIf { it.isNotBlank() }?:"NA"
topSource.value=_data.value!!.data?.top_source.takeIf { !it.isNullOrBlank() }?:"NA"
topLinks.value= _data.value!!.data?.dataDto?.top_links
            topLocation.value= _data.value!!.data?.top_location.takeIf { !it.isNullOrBlank() }?:"NA"
            bestTime.value= _data.value!!.data?.startTime.takeIf { !it.isNullOrBlank() }?:"NA"

        }
    }
}