package com.example.loginpractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.loginpractice.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth


//* 주의 : 에뮬레이터 실행 인터넷 확인 필요.

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var user: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {

        val email = binding.editEmail.text.toString()
        val password = binding.editPassword.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            user.createUserWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity()) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this,"계정 생성이 완료되었습니다.",Toast.LENGTH_LONG).show()
                        startActivity(Intent(this,SecondActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this,task.exception!!.message,Toast.LENGTH_LONG).show()
                    }

                }

        } else {
            Toast.makeText(this,"이메일과 비밀번호를 입력하세요.",Toast.LENGTH_LONG).show()
        }

    }
}