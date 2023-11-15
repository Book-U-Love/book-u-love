package com.example.frontend.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.frontend.data.model.ModifyPw
import com.example.frontend.data.model.ModifyUser
import com.example.frontend.data.model.UserRegistDto
import com.example.frontend.data.repository.UserRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.lang.Exception

class UserViewModel: ViewModel(){
    private val userRepository: UserRepository = UserRepository()

    private val _signupRes: MutableStateFlow<String> = MutableStateFlow("init")
    private val _userMyInfo: MutableState<Map<String, String>> = mutableStateOf(mapOf())
    private val _userPage: MutableState<Map<String, String>> = mutableStateOf(mapOf())
    private val _userReviewList: MutableState<List<Map<String, String>>> = mutableStateOf(listOf())
    private val _modifyRes: MutableStateFlow<String> = MutableStateFlow("init")
    private val _modifyPwRes: MutableStateFlow<String> = MutableStateFlow("init")
    private val _libraryList: MutableState<List<Map<String, String>>> = mutableStateOf(listOf())
    private val _checkId: MutableStateFlow<String> = MutableStateFlow("init")
    val signupRes : StateFlow<String>
        get() = _signupRes
    val userMyInfo : State<Map<String, String>> = _userMyInfo
    val userPage : State<Map<String, String>> = _userPage
    val modifyRes: StateFlow<String>
        get() = _modifyRes
    val modiyfPwRes: StateFlow<String>
        get() = _modifyPwRes
    val libraryList: State<List<Map<String, String>>> = _libraryList
    val checkId: StateFlow<String>
        get() = _checkId
    val userReviewList : State<List<Map<String, String>>> = _userReviewList
    fun resetState(){
        _modifyRes.value = "init'"
    }
    fun resetPwState(){
        _modifyPwRes.value = "init"
    }
    fun resetCheckId(){
        _checkId.value = "init"
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

    fun getMyInfo(){
        GlobalScope.async{
            try{
                userRepository.getMyInfo().collect(){
                    res->
                    _userMyInfo.value = res
                }
            }catch (e:Exception){
            }
        }
    }

    fun getMyPage(){
        GlobalScope.async {
            try{
                userRepository.getMyPage().collect(){
                    res ->
                    _userPage.value = res
                }
            }catch (e:Exception){
            }
        }
    }

    fun getUserPage(userId: String){
        GlobalScope.async {
            try{
                userRepository.getUserPage(userId).collect(){
                    res ->
                    _userPage.value = res
                }
            } catch(e: Exception){
            }
        }
    }

    fun modifyUserInfo(modifyUser: ModifyUser){
        GlobalScope.async {
            try{
                userRepository.modifyUser(modifyUser).collect(){
                    res ->
                    _modifyRes.value = res
                }
            } catch (e:Exception){
            }
        }
    }

    fun modifyUserPw(modifyPw: ModifyPw){
        GlobalScope.async {
            try{
                userRepository.modifyPassword(modifyPw).collect(){
                    res ->
                    _modifyPwRes.value = res
                }
            } catch (e:Exception){
            }
        }
    }

    fun getLibraryList(){
        GlobalScope.async {
            try{
                userRepository.getLibraryList().collect(){
                    res ->
                    _libraryList.value = res
                }
            } catch (e: Exception){
            }
        }
    }

    fun checkId(userId: String){
        GlobalScope.async {
            try{
                userRepository.checkId(userId).collect(){
                    res ->
                    _checkId.value = res
                }
            }catch (e: Exception){
            }
        }
    }

    fun getReviewList(){
        GlobalScope.async {
            try{
                userRepository.getReviewList().collect(){
                    res ->
                    _userReviewList.value = res
                }
            }catch (e: Exception){
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