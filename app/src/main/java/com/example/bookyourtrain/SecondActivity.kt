package com.example.bookyourtrain

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.bookyourtrain.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    companion object {
        const val EXTRA_USERNAMELOGIN = "extra_username"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(MainActivity.EXTRA_USERNAME)
        val password = intent.getStringExtra(MainActivity.EXTRA_PASSWORD)

        with(binding){
            btnLogin.setOnClickListener {
                val usernameEmailInput = editTxtUsnEmailLogin.text.toString()
                val passwordInput = editTxtPasswordLogin.text.toString()

                if (usernameEmailInput == username && passwordInput == password) {
                    val intentToThirdActivity = Intent(this@SecondActivity, ThirdActivity::class.java)
                    intentToThirdActivity.putExtra(EXTRA_USERNAMELOGIN, username)
                    startActivity(intentToThirdActivity)
                }
                else {
                    Toast.makeText(this@SecondActivity, "Wrong username or password!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}