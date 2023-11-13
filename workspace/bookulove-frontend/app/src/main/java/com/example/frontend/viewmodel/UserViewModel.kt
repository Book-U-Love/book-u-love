package com.example.frontend.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.frontend.data.model.ModifyPw
import com.example.frontend.data.model.ModifyUser
import com.example.frontend.data.model.UserRegistDto
import com.example.frontend.data.repository.UserRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.concurrent.Flow

class UserViewModel: ViewModel(){
    private val userRepository: UserRepository = UserRepository()

    private val _signupRes: MutableStateFlow<String> = MutableStateFlow("init")
    private val _userInfo: MutableState<Map<String, String>> = mutableStateOf(mapOf())
    private val _modifyRes: MutableStateFlow<String> = MutableStateFlow("init")
    private val _modifyPwRes: MutableStateFlow<String> = MutableStateFlow("init")
    private val _libraryList: MutableState<List<UserRegistDto>> = mutableStateOf(listOf())
    val signupRes : StateFlow<String>
        get() = _signupRes
    val userInfo : State<Map<String, String>> = _userInfo
    val modifyRes: StateFlow<String>
        get() = _modifyRes
    val modiyfPwRes: StateFlow<String>
        get() = _modifyPwRes
    val libraryList: State<List<UserRegistDto>> = _libraryList
    fun resetState(){
        _modifyRes.value = "init'"
    }
    fun resetPwState(){
        _modifyPwRes.value = "init"
    }
    fun signUp(userInfo: UserRegistDto){
        GlobalScope.async{
            try{
                userRepository.signUp(userInfo).collect(){
                    res->
                    _signupRes.value = res;
                }
            }catch (e:Exception){
            }
        }
    }

    fun getInfo(token: String){
        GlobalScope.async{
            try{
                userRepository.getInfo(token).collect(){
                    res->
                    _userInfo.value = res
                }
            }catch (e:Exception){
            }
        }
    }

    fun modifyUserInfo(token: String, modifyUser: ModifyUser){
        GlobalScope.async {
            try{
                userRepository.modifyUser(token, modifyUser).collect(){
                    res ->
                    _modifyRes.value = res
                }
            } catch (e:Exception){
            }
        }
    }

    fun modifyUserPw(token: String, modifyPw: ModifyPw){
        GlobalScope.async {
            try{
                userRepository.modifyPassword(token, modifyPw).collect(){
                    res ->
                    _modifyPwRes.value = res
                }
            } catch (e:Exception){
            }
        }
    }

    fun getLibraryList(token: String){
        GlobalScope.async {
            try{
                userRepository.getLibraryList(token).collect(){
                    res ->
                    _libraryList.value = res
                }
            } catch (e: Exception){
            }
        }
    }
}

class UserViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel() as T
        }
        throw IllegalArgumentException("unknown viewModel");
    }
}