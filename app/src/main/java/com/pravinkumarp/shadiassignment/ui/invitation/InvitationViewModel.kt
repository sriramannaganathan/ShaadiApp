package com.pravinkumarp.shadiassignment.ui.invitation

import androidx.lifecycle.*
import com.pravinkumarp.shadiassignment.data.repository.Repository
import com.pravinkumarp.shadiassignment.extension.ApiResponse
import com.pravinkumarp.shadiassignment.model.Matches
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InvitationViewModel(private val results: String, private val repository: Repository) :
    ViewModel() {

//    val invitationList = MutableLiveData<List<Matches.Invitation>> ()

//    fun getMatches() {
//        viewModelScope.launch(Dispatchers.IO) {
//            isLoading.postValue(true)
//            invitationList.postValue(repository.getMatches(results).invitations)
//            isLoading.postValue(false)
//        }
//    }

//    val invitationList: LiveData<List<Matches.Invitation>> = liveData(Dispatchers.IO) {
//        isLoading.postValue(true)
//        emit(repository.getMatches(results).invitations)
//        isLoading.postValue(false)
//    }

    var invitationList = MediatorLiveData<ApiResponse<List<Matches.Invitation>>>()

    fun getMatches(): LiveData<ApiResponse<List<Matches.Invitation>>> {
        invitationList = MediatorLiveData<ApiResponse<List<Matches.Invitation>>>()
        val mutableLiveData = MutableLiveData<ApiResponse<List<Matches.Invitation>>>()
        invitationList.addSource(mutableLiveData) {
            invitationList.value = it
        }
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.postValue(true)
            mutableLiveData.postValue(ApiResponse.Loading)
            try {
                mutableLiveData.postValue(ApiResponse.Success(repository.getMatches(results).invitations))
            } catch (ex: Exception) {
                mutableLiveData.postValue(ApiResponse.Error(ex))
            }
            isLoading.postValue(false)
        }
        return invitationList
    }

    fun getMatchesFromLocal(): LiveData<ApiResponse<List<Matches.Invitation>>> {
        invitationList = MediatorLiveData<ApiResponse<List<Matches.Invitation>>>()
        val mutableLiveData = MutableLiveData<ApiResponse<List<Matches.Invitation>>>()
        invitationList.addSource(mutableLiveData) {
            invitationList.value = it
        }
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.postValue(true)
            try {
                mutableLiveData.postValue(ApiResponse.Success(repository.getMatchesFromLocal(results).invitations))
            } catch (ex: Exception) {
                mutableLiveData.postValue(ApiResponse.Error(ex))
            }
            isLoading.postValue(false)
        }
        return invitationList
    }

    fun updateInvitation(invitation: Matches.Invitation) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(invitation)
        }
    }

    val isLoading = MutableLiveData<Boolean>()
}