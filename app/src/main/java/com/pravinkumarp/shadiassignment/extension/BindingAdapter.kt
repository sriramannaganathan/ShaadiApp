package com.pravinkumarp.shadiassignment.extension

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pravinkumarp.shadiassignment.R
import com.pravinkumarp.shadiassignment.model.Matches
import com.pravinkumarp.shadiassignment.ui.invitation.InvitationsAdapter


@BindingAdapter("bind:loadUrl")
fun bindUrlImage(view: ImageView, url: String) {
    Glide.with(view)
        .load(url)
        .circleCrop()
        .into(view)
}

@BindingAdapter("bind:loadImageFromInvitation")
fun bindImageFromInvitation(view: ImageView, invitation: Matches.Invitation) {
    val placeholderImage = when (invitation.gender) {
        "female" -> R.drawable.female_placeholder
        else -> R.drawable.male_placeholder
    }
    Glide.with(view)
        .load(invitation.picture.large)
        .placeholder(placeholderImage)
        .circleCrop()
        .into(view)
}

@BindingAdapter("bind:toSmallDate")
fun bindToSmallDate(view: TextView, date: String) {
    try {
        view.text = Converters.toSmallDate(date)
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
}

@BindingAdapter("bind:profileName")
fun bindToSmallDate(view: TextView, name: Matches.Invitation.Name) {
    view.text = "${name.first} ${name.last}"
}

@BindingAdapter("bind:invitationDescription")
fun bindToSmallDate(view: TextView, invitation: Matches.Invitation) {
    view.text =
        "${invitation.dob.age}, ${invitation.location.city}, ${invitation.location.state}, ${invitation.location.country}"
}

@BindingAdapter("bind:show")
fun bindToSmallDate(view: View, show: Boolean) {
    view.visibility = if (show) View.VISIBLE else View.GONE
}

@BindingAdapter("bind:adapter")
fun setAdapter(view: RecyclerView, baseAdapter: RecyclerView.Adapter<*>) {
    view.adapter = baseAdapter
}

@BindingAdapter("bind:items")
fun setItems(view: RecyclerView, items: List<Matches.Invitation>?) {
    val invitationsAdapter = view.adapter as InvitationsAdapter
    invitationsAdapter.itemList = items ?: emptyList()
    invitationsAdapter.notifyDataSetChanged()
}