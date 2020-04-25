package com.remonh87.comfortfood

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.flutter.embedding.android.FlutterActivity
import kotlinx.android.synthetic.main.placeholder_fragment.*

class LaunchScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.placeholder_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launch_pizza_button.setOnClickListener {
            val intent =  internalIntent(requireContext(), "com.remonh87.pizza.open")
            startActivity(intent)
        }

        launch_burgers_button.setOnClickListener {
            val flutterActivity = FlutterActivity.
                withCachedEngine(comfortFoodEngineId)
                .build(requireContext())

            startActivity(flutterActivity)
        }
    }
    private fun internalIntent(context: Context, action: String) = Intent(action).setPackage(context.packageName)

}