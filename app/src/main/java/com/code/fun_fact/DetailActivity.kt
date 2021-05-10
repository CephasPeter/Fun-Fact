package com.code.fun_fact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.code.fun_fact.databinding.ActivityDetailBinding
import com.code.fun_fact.databinding.ActivityMainBinding

class DetailActivity : AppCompatActivity() {
    var binding: ActivityDetailBinding? = null
    var logo = 0
    var name = ""
    var detail = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        logo = intent?.getIntExtra(FactAdapter.LOGO_EXTRA,0)!!
        name = intent?.getStringExtra(FactAdapter.NAME)!!
        detail = intent?.getStringExtra(FactAdapter.FACT)!!

        setUpFactDetail()
    }

    fun setUpFactDetail() {
        binding?.detail?.text = detail
        binding?.logo?.setImageResource(logo)
        title = name
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}