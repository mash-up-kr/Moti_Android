package com.ahobsu.moti.presentation.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.ahobsu.moti.R
import com.ahobsu.moti.Unit
import com.ahobsu.moti.data.injection.Injection
import com.ahobsu.moti.databinding.ActivityLoginBinding
import com.ahobsu.moti.domain.SignInUseCase
import com.ahobsu.moti.domain.UserUseCase
import com.ahobsu.moti.presentation.BaseActivity
import com.ahobsu.moti.presentation.ui.main.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private val loginViewModel by viewModels<LoginViewModel>()
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth

    private val sharedPrefFile = "app_preferences"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initMainDataBinding()

        Unit.mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        binding.tvTitle.setOnClickListener {
            Log.e("setOnClickListener", "click")
            Unit.jwt?.let {
                Log.e("Authorization", it)
            }
        }

        binding.btnLogin.setOnClickListener {
            signIn()
        }
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
        auth = Firebase.auth

        loginViewModel.signUpFragment.observe(this, Observer {
            if (it == LoginViewModel.SignUpFragment.NickName) {
                changeFragment(LoginViewModel.SignUpFragment.NickName)
            }
        })
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
                Unit.putJWT(account.idToken)

                SignInUseCase(Injection.provideSignUpRepository())("apple")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ it ->
                        Log.e("accessToken Success ", it.toString())
                        Unit.putAccessToken(it.accessToken)
                        Unit.putRefreshToken(it.refreshToken)
                        startMainActivity()
                    }, { e ->
                        Log.e("postSignIn e", e.toString())
                    })
                startActivity(intent)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }

    fun startMainActivity() {
        loginViewModel.user.value?.let {
            UserUseCase(Injection.provideUserRepository()).putUserInfo(it)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.e(" Success ", it.toString())
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                }, { e ->
                    Log.e("postSignIn e", e.toString())
                })
        }

    }

    fun changeFragment(fragment: LoginViewModel.SignUpFragment) {
        supportFragmentManager.beginTransaction().apply {
            add(
                R.id.fl_container,
                when (fragment) {
                    LoginViewModel.SignUpFragment.NickName -> {
                        SignUpNicknameFragment.newInstance()
                    }
                    LoginViewModel.SignUpFragment.Birthday -> {
                        SignUpBirthdayFragment.newInstance()
                    }
                    LoginViewModel.SignUpFragment.Gender -> {
                        SignUpGenderFragment.newInstance()
                    }
                    LoginViewModel.SignUpFragment.Complete -> {
                        SignUpCompleteFragment.newInstance(loginViewModel.user.value?.name ?: "")
                    }
                    else ->  SignUpNicknameFragment.newInstance()
                }
            )
            addToBackStack(null)
        }.commit()
    }

    fun popFragment() {
        supportFragmentManager.popBackStack()
    }

    private fun initMainDataBinding() {
        binding.viewModel = loginViewModel
    }


    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.id!!)
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithCredential:success")
                } else {
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                }
            }
    }

    fun setUserInfo(name: String?, gender: String?, birthday: String?) {
        loginViewModel.setUserInfo(name, gender, birthday)
    }

    companion object {
        private const val TAG = "GoogleActivity"
        private const val RC_SIGN_IN = 9001
    }
}
