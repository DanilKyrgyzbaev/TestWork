package com.mobilemirdev.testwork

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mobilemirdev.testwork.adapter.Adapter
import com.mobilemirdev.testwork.databinding.ActivityMainBinding
import com.mobilemirdev.testwork.databinding.ActivityMainBinding.inflate
import com.mobilemirdev.testwork.model.User
import com.mobilemirdev.testwork.network.RetrofitService
import com.mobilemirdev.testwork.network.RetrofitService.Companion.retrofitService
import com.mobilemirdev.testwork.repository.MainRepository
import kotlinx.android.synthetic.main.add_alert_diolog.view.*

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    private val retrofitService = RetrofitService.getInstance()
    val adapterUser = Adapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(MainViewModel::class.java)

        binding.recyclerview.adapter = adapterUser

        binding.floatingActionButton.setOnClickListener{
            //Inflate the dialog with custom view
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.add_alert_diolog, null)
            //AlertDialogBuilder
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
            //show dialog
            val  mAlertDialog = mBuilder.show()
            //login button click of custom layout
            mDialogView.button_add.setOnClickListener {
                //dismiss dialog
                //get text from EditTexts of custom layout
                val title = mDialogView.title.text.toString()
                val body = mDialogView.body.text.toString()
                viewModel.createPost(User(1, title, body))
                mAlertDialog.dismiss()

            }
        }

        viewModel.userModel.observe(this){
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
        }


        viewModel.userList.observe(this, {
            Log.d(TAG, "onCreate: $it")
            adapterUser.setUserList(it)
        })

        viewModel.errorMessage.observe(this, {

        })
        viewModel.getAllUser()
    }


}