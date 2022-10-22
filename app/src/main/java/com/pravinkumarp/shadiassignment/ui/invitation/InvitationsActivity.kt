package com.pravinkumarp.shadiassignment.ui.invitation

import android.R.id
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.pravinkumarp.shadiassignment.R
import com.pravinkumarp.shadiassignment.base.BaseActivity
import com.pravinkumarp.shadiassignment.databinding.ActivityMainBinding
import com.pravinkumarp.shadiassignment.extension.ApiResponse
import com.pravinkumarp.shadiassignment.model.Matches
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.net.UnknownHostException


class InvitationsActivity : BaseActivity(), InvitationsAdapter.OnInvitationUpdateListener {
    companion object {
        const val INVITATION_ACCEPTED = 1
        const val INVITATION_DECLINED = 2
    }

    private val binding by binding<ActivityMainBinding>(R.layout.activity_main)
    private val viewModel: InvitationViewModel by viewModel { parametersOf("10") }
    private val invitationsAdapter = InvitationsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            lifecycleOwner = this@InvitationsActivity
            viewModel = this@InvitationsActivity.viewModel
            adapter = invitationsAdapter
        }
        // add invitation update listener
        invitationsAdapter.onInvitationUpdateListener = this@InvitationsActivity
        // load data from remote
        getDataFromRemote()
    }

    private fun getDataFromRemote() {
        viewModel.getMatches().observe(this, { apiResponse ->
            println("getDataFromRemote")
            if (apiResponse == null) {
                showMessage("Something went wrong.")
                return@observe
            }
            when (apiResponse) {
                is ApiResponse.Error -> {
                    println("exception:" + apiResponse.exception.message)
                    apiResponse.exception.printStackTrace()
                    if (apiResponse.exception is UnknownHostException) {
                        showMessage("No internet found. Loading from local.")
                        // load data from local
                        getDataFromLocal()
                        return@observe
                    }
                }
                is ApiResponse.Loading -> {

                }
                is ApiResponse.Success -> {
                    if (apiResponse.data.isEmpty()) {
                        showMessage("No data to show.")
                        return@observe
                    }
                }
            }
        })
    }

    private fun getDataFromLocal() {
        viewModel.getMatchesFromLocal().observe(this, { apiResponse ->
            println("getDataFromLocal")
            if (apiResponse == null) {
                showMessage("Something went wrong.")
                return@observe
            }
            when (apiResponse) {
                is ApiResponse.Error -> {
                    println("exception:" + apiResponse.exception.message)
                    apiResponse.exception.printStackTrace()
                    showMessage("Failed to load data.")
                    return@observe
                }
                is ApiResponse.Loading -> {

                }
                is ApiResponse.Success -> {
                    if (apiResponse.data.isEmpty()) {
                        showMessage("No data to show.")
                        return@observe
                    }
                }
            }
        })
    }

    override fun onInvitationUpdate(invitation: Matches.Invitation) {
        // save to local database
        viewModel.updateInvitation(invitation)
    }

    private fun showMessage(message: String) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        val view: View? = toast.view
        view!!.background.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN)
        val text: TextView = view.findViewById(id.message)
        text.setTextColor(Color.WHITE)
        toast.show()
    }
}