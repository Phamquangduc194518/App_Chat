package com.t3h.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.t3h.chatapp.databinding.LoginLayoutBinding
import com.t3h.chatapp.model.Account
import com.t3h.chatapp.service.ApiService
import com.t3h.chatapp.service.Logger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogIn : AppCompatActivity() {
    private lateinit var logInBinding: LoginLayoutBinding
    private var mlist = mutableListOf<Account>()
    var isLoggedIn = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logInBinding = LoginLayoutBinding.inflate(layoutInflater)
        val view = logInBinding.root
        setContentView(view)
        logInBinding.tvSignUp.setOnClickListener() {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
        logInBinding.btnLogIn.setOnClickListener() {
            val apiService = Logger().apiService
            apiService.getAllAccount().enqueue(object : Callback<List<Account>> {
                override fun onResponse(
                    call: Call<List<Account>>,
                    response: Response<List<Account>>,
                ) {
                    mlist = response.body() as MutableList<Account>
                    for (i in mlist) {
                        val emailInput = logInBinding.edtEmail.text.toString()
                        val passwordInput = logInBinding.edtPassWord.text.toString()
                        if (emailInput==(i.email) && passwordInput==(i.pass_word)) {
                            isLoggedIn = true
                            break
                        }
                    }
                    if (isLoggedIn) {
                        // Đăng nhập thành công
                        Toast.makeText(this@LogIn, "Đăng nhập thành công", Toast.LENGTH_SHORT).show()
                        isLoggedIn = false
                        // Tiến hành chuyển đến màn hình chính hoặc các hoạt động khác
                    } else {
                        // Đăng nhập thất bại
                        Toast.makeText(this@LogIn, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show()
                    }

                }

                override fun onFailure(call: Call<List<Account>>, t: Throwable) {
                    return
                }
            })
        }
    }
}