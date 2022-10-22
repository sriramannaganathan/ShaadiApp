package com.pravinkumarp.shadiassignment.ui.invitation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.recyclerview.widget.RecyclerView
import com.pravinkumarp.shadiassignment.R
import com.pravinkumarp.shadiassignment.databinding.ItemInvitationBinding
import com.pravinkumarp.shadiassignment.model.Matches
import kotlinx.android.synthetic.main.item_invitation.view.*


class InvitationsAdapter :
    RecyclerView.Adapter<InvitationsAdapter.InvitationViewHolder>() {
    var itemList = listOf<Matches.Invitation>()
    var onInvitationUpdateListener: OnInvitationUpdateListener? = null

    inner class InvitationViewHolder(private val binding: ItemInvitationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Matches.Invitation) {
            binding.apply {
                invitation = item
                executePendingBindings()
                root.btInvitationDecline.setOnClickListener {
                    item.status = InvitationsActivity.INVITATION_DECLINED
                    if (onInvitationUpdateListener != null) {
                        onInvitationUpdateListener!!.onInvitationUpdate(item)
                    }
                }
                root.btInvitationAccept.setOnClickListener {
                    item.status = InvitationsActivity.INVITATION_ACCEPTED
                    if (onInvitationUpdateListener != null) {
                        onInvitationUpdateListener!!.onInvitationUpdate(item)
                    }
                }
                item.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                    override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                        notifyItemChanged(adapterPosition)
                    }
                })
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvitationViewHolder {
        return DataBindingUtil.inflate<ItemInvitationBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_invitation,
            parent,
            false
        ).let {
            InvitationViewHolder(it)
        }
    }

    override fun onBindViewHolder(holder: InvitationViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    interface OnInvitationUpdateListener {
        fun onInvitationUpdate(invitation: Matches.Invitation)
    }
}