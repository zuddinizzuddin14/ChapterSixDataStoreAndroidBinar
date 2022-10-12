package com.example.myloginregister

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class MainViewModel(private val pref: UserDataStoreManager) : ViewModel() {

    fun getSession(): LiveData<Boolean> {
        return pref.getSession().asLiveData()
    }

    fun getUsername(): LiveData<String> {
        return pref.getUsername().asLiveData()
    }

    fun getPassword(): LiveData<String> {
        return pref.getPassword().asLiveData()
    }

    fun setSession(session: Boolean) {
        viewModelScope.launch {
            pref.setSession(session)
        }
    }

    fun setUser(username: String, password: String) {
        viewModelScope.launch {
            pref.setUser(username, password)
        }
    }
}