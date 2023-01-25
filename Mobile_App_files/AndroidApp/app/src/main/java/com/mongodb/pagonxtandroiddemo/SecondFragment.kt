package com.mongodb.pagonxtandroiddemo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.navigation.fragment.findNavController
import com.mongodb.pagonxtandroiddemo.databinding.FragmentSecondBinding
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.notifications.*
import io.realm.kotlin.query.RealmResults
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val realm: Realm by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val realm: Realm by inject()

        //Inmediate query
        CoroutineScope(Dispatchers.Main).launch {
            val txlistrr: RealmResults<Customer> =
                realm.query<Customer>("country == $0", "C3").find()

            val txListFlow = txlistrr.asFlow()
            txListFlow.collect {resultsChange : ResultsChange<Customer> ->
                when(resultsChange){
                    is InitialResults ->
                    {
                        val txlist = resultsChange.list
                        val context = context as MainActivity
                        val lv = container?.findViewById(R.id.listViewCustomers) as ListView
                        val adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, txlist)
                        lv.adapter = adapter
                    }
                    is UpdatedResults -> {

                        val txlist = resultsChange.list
                        val context = context as MainActivity
                        val lv = container?.findViewById(R.id.listViewCustomers) as ListView
                        val adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, txlist)
                        lv.adapter = adapter
                        // Android RecyclerView knows how to animate ranges
                    }
                }

            }



        }

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val results = realm.query<Customer>("country == $0", "C3").find()
        /*for (result in results){
            Log.println(Log.INFO, "davidtag", result.toString())
        }*/

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}