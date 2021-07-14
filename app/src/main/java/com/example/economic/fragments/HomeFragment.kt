package com.example.economic.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.economic.DetailNews
import com.example.economic.R
import com.example.economic.retrofit.ApiService
import com.example.economic.retrofit.MainAdapter
import com.example.economic.retrofit.MainModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
lateinit var MainAdapter : MainAdapter

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val TAG: String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }
    }

    private fun getDataFromApi(){
        ApiService.endPoint.getData()
            .enqueue(object : retrofit2.Callback<MainModel>{
                override fun onResponse(
                    call: Call<MainModel>,
                    response: Response<MainModel>
                ) {
                    if (response.isSuccessful){
                        val result = response.body()
                        printlog(result.toString())
                        showdata(result!!)
                    }
                }

                override fun onFailure(call: Call<MainModel>, t: Throwable) {
                    println(t.toString())
                }

            })
    }

    private fun setupRecycleView(){
        MainAdapter = MainAdapter(arrayListOf(), object : MainAdapter.OnAdapterListener{
            override fun onClick(result: MainModel.Result) {
//                 Toast.makeText(activity?.applicationContext,result.title, Toast.LENGTH_SHORT)
//                     .show()
                startActivity(
                    Intent(context?.applicationContext,DetailNews::class.java)
                        .putExtra("intent_title", result.title)
                        .putExtra("title_image", result.image)
                )
            }

        })
        recycleView.apply {
            layoutManager = LinearLayoutManager(activity?.applicationContext)
            adapter = MainAdapter
        }
    }

    private fun printlog(message : String){
        Log.d(TAG, message)
    }

    private fun showdata(data : MainModel){
        var results = data.result
        MainAdapter.setData(results)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Picasso.get().load("https://picsum.photos/200/300").fit().centerCrop().into(image_view)
        Picasso.get().load("https://picsum.photos/200/300").fit().centerCrop().into(image_view2)
        Picasso.get().load("https://picsum.photos/200/300").fit().centerCrop().into(image_view3)
        setupRecycleView()
        getDataFromApi()

        val homeFragment = HomeFragment()
        val ecoblogFragment = EcoBlogFragment()
        val cartFragment = ChartFragment()
        val meFragment = meFragment()
        val blankFragment1 = BlankFragment1()
        val blankFragment2 = BlankFragment2()

        top_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.tes1 -> makeCurrentfragments(blankFragment2)
                R.id.tes2 -> makeCurrentfragments(blankFragment1)
                R.id.tes3 -> makeCurrentfragments(cartFragment)
                R.id.tes4 -> makeCurrentfragments(meFragment)

            }
            true
        }
    }

    private fun makeCurrentfragments(fragment: Fragment) =
        childFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_wrapper,fragment)
            commit()
        }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)

//                    Picasso.get().load("https://picsum.photos/200/300").fit().centerCrop().into(R.id.image_view)
                }
            }
    }
}