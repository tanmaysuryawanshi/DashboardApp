package com.tanmaysuryawanshi.dashboard.dashboardfeature.presentation.fragments

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.PackageManager.NameNotFoundException
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.android.material.button.MaterialButton
import com.tanmaysuryawanshi.dashboard.R
import com.tanmaysuryawanshi.dashboard.core.util.Resource
import com.tanmaysuryawanshi.dashboard.dashboardfeature.presentation.adapter.TopLinkAdapter
import com.tanmaysuryawanshi.dashboard.dashboardfeature.presentation.viewmodels.DashboardViewmodel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.lang.Exception


@AndroidEntryPoint
class LinksFragment : Fragment() {
    private val viewModel: DashboardViewmodel by viewModels()


    lateinit var greetingTextView: TextView
    lateinit var todaysClickTextView: TextView
    lateinit var topLocationTextView: TextView
    lateinit var topSourceTextView: TextView
    lateinit var bestTimeTextView:TextView
    lateinit var errorTextView: TextView
lateinit var progressBar: ProgressBar
lateinit var nestedScrollView:NestedScrollView
lateinit var recyclerView: RecyclerView
lateinit var lineChart:LineChart
lateinit var adapter:TopLinkAdapter
lateinit var whatsappButton:MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root =inflater.inflate(R.layout.fragment_links, container, false)
recyclerView=root.findViewById(R.id.recyclerview)
        greetingTextView=root.findViewById(R.id.greetingTV)
        topSourceTextView=root.findViewById(R.id.topSourceTV)
        topLocationTextView=root.findViewById(R.id.topLocationTV)
        bestTimeTextView=root.findViewById(R.id.bestTimeTV)
todaysClickTextView=root.findViewById(R.id.todaysclicksTV)
        errorTextView=root.findViewById(R.id.errorTextView)
        progressBar=root.findViewById(R.id.progressBar)
        nestedScrollView=root.findViewById(R.id.scrollView)
        lineChart=root.findViewById(R.id.lineChart)
whatsappButton=root.findViewById(R.id.whatsappButton)

lifecycleScope.launch {
    viewModel.getGreeting()
    viewModel.fetchData()
}
viewModel.greeting.observe(viewLifecycleOwner){
    greetingTextView.text=it
}
viewModel.topLocation.observe(viewLifecycleOwner){
    topLocationTextView.text=it
}
        viewModel.topSource.observe(viewLifecycleOwner){
            topSourceTextView.text=it
        }
        viewModel.bestTime.observe(viewLifecycleOwner){
            bestTimeTextView.text=it
        }
        viewModel.todaysClicks.observe(viewLifecycleOwner){
            todaysClickTextView.text=it
        }
        val layoutManager=LinearLayoutManager(context)


        recyclerView.layoutManager=layoutManager

        viewModel.topLinks.observe(viewLifecycleOwner){


                it?.let {

                    adapter = TopLinkAdapter(it)
                    adapter.setOnInterfaceClickListner(object:TopLinkAdapter.LinkClickedInterface{
                        override fun linkClicked(position: Int) {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(viewModel.data.value?.data?.dataDto?.top_links?.get(position)?.web_link))
                            startActivity(intent)
                        }

                        override fun copyClicked(position: Int) {
                            val urlToCopy = viewModel.data.value?.data?.dataDto?.top_links?.get(position)?.smart_link
                            if (urlToCopy != null) {
                                copyToClipboard(urlToCopy)
                                Toast.makeText(requireContext(),"Copied to Clipboard",Toast.LENGTH_SHORT).show()
                            }
                        }

                    } )
                    recyclerView.adapter = adapter

                }
        }

        viewModel.data.observe(viewLifecycleOwner
        ) { response ->
            when (response) {
                is Resource.Success -> {
                    progressBar.visibility=View.GONE
                    nestedScrollView.visibility=View.VISIBLE
                    errorTextView.visibility=View.GONE

                    greetingTextView.text=viewModel.greeting.value
response.data?.let {
    data->


    val map= data.dataDto?.overall_url_chart
    Log.d("map", "onCreateView: "+map.toString())
    val entries = mutableListOf<Entry>()
var index=0
   map?.let {
       Log.d("linechart", "onCreateView: ")
       for((key,value) in it){
             entries.add(Entry(index.toFloat(),value.toFloat()))
           index++
       }

       val dataSet = LineDataSet(entries, "Chart Label")
       dataSet.color = resources.getColor(R.color.blue)
       dataSet.lineWidth = 2f
       dataSet.circleRadius = 0f
       dataSet.setCircleColor(resources.getColor(R.color.blue))
       dataSet.setDrawCircleHole(false)
       dataSet.setDrawCircles(false)
       dataSet.valueTextColor = Color.BLACK
       dataSet.setDrawValues(false)
       dataSet.setDrawFilled(true)

       val startColor = Color.parseColor("#6EA8FF")
       val endColor = Color.TRANSPARENT
       val gradientDrawable = GradientDrawable(
           GradientDrawable.Orientation.TOP_BOTTOM,
           intArrayOf(startColor, endColor)
       )
       dataSet.fillDrawable = gradientDrawable

       val lineData = LineData(dataSet)
       lineData.setValueTextSize(10f)
       Log.d("lineChart", "onCreateView: "+entries.toString())
       lineChart.data = lineData



       lineChart.setNoDataText("No data available")
       lineChart.setNoDataTextColor(Color.GRAY)
       lineChart.setBackgroundColor(Color.WHITE)
       lineChart.setTouchEnabled(false)


       lineChart.animateX(2000, Easing.EaseInOutQuart)
       lineChart.animateY(1000, Easing.EaseInOutQuart)

       val legend = lineChart.legend
       legend.isEnabled = false

       val xAxis = lineChart.xAxis
       xAxis.position = XAxis.XAxisPosition.BOTTOM
       xAxis.textColor = Color.GRAY
       xAxis.textSize = 10f
       xAxis.granularity = 1f
       xAxis.setDrawAxisLine(true)
       xAxis.setDrawGridLines(true)
       xAxis.enableGridDashedLine(5f, 5f, 0f)

       val leftAxis = lineChart.axisLeft
       leftAxis.textColor = Color.GRAY
       leftAxis.textSize = 10f
       leftAxis.axisMinimum = 0f
       leftAxis.enableGridDashedLine(5f, 5f, 0f)
       leftAxis.setDrawGridLines(true)
       leftAxis.setDrawAxisLine(true)
       leftAxis.gridColor = Color.GRAY

       val rightAxis = lineChart.axisRight
       rightAxis.isEnabled = false

       lineChart.invalidate()

       whatsappButton.setOnClickListener {
button->
           val messageText="Hello openinapp helpcenter I wanted to ask few questions"
           startActivity(
           Intent(Intent.ACTION_VIEW,
           Uri.parse(
               String.format("https://api.whatsapp.com/send?phone=+91${data.support_whatsapp_number}&text=$messageText")
           )
       ))
       }

   }


}
                    Log.d("success", "onCreateView: ")

                }

                is Resource.Error -> {progressBar.visibility=View.GONE
                    nestedScrollView.visibility=View.GONE
                    errorTextView.visibility=View.VISIBLE
                    Log.d("error", "onCreateView: ")
                }

                is Resource.Loading -> {progressBar.visibility=View.VISIBLE
                    nestedScrollView.visibility=View.GONE
                    errorTextView.visibility=View.GONE
                    Log.d("loading", "onCreateView: ")
                }
            }


        }
        return root;
    }


    private fun copyToClipboard(text: String) {

        val clipboardManager = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip: ClipData = ClipData.newPlainText("simple text", text)

      clipboardManager.setPrimaryClip(clip)

    }

}


