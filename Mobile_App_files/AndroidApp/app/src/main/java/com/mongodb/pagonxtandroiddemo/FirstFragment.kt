package com.mongodb.pagonxtandroiddemo

import android.location.Address
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mongodb.pagonxtandroiddemo.databinding.FragmentFirstBinding
import io.realm.kotlin.Configuration
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.internal.platform.runBlocking
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.Credentials
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import org.koin.core.context.startKoin
import org.koin.dsl.module


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            val app: App = App.create("mymobileapp-silmk")
            val user = binding.txtUser.text.toString()
            val password = binding.txtPassword.text.toString()
            runBlocking {
                //val user = app.login(Credentials.emailPassword(user, password))
                val user = app.login(Credentials.emailPassword("email@domain.com", "**MYPASS**"))
                println("My App Services user id" + user.id)
                val koinModule = module {
                    single<Configuration> {
                        SyncConfiguration.Builder(user, schema = setOf(
                            Customer::class,
                            Addresses::class,
                            Accounts::class,
                            Contacts::class,
                            Documents::class
                        ))
                            .initialSubscriptions { realm ->
                                add(
                                    realm.query<Customer>(
                                        "country == $0 && documentType == $1",
                                        "C3",
                                        "national_id_card"
                                    ),
                                    "Customers from country C3 and documentType is National ID Card"
                                )
                            }
                            .build()
                    }
                    single { Realm.open(get())}
                }
                startKoin {
                    modules(
                        koinModule
                    )
                }
            }



            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}