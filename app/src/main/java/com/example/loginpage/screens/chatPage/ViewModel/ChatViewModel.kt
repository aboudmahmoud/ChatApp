package com.example.loginpage.screens.chatPage.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginpage.Moudle.ChatRoom.MessegDiteals
import com.example.loginpage.Moudle.ChatRoom.UseCase.MessegSendState
import com.example.loginpage.Moudle.ChatRoom.UseCase.UpdateStatChatState
import com.example.loginpage.Moudle.User.CurrenUserStatis
import com.example.loginpage.data.SeriveQurey.fireauth.RegsterImplements
import com.example.loginpage.data.SeriveQurey.firesStroe.FirebaseSirvase
import com.example.loginpage.screens.chatPage.Intent.ChatPageIntnent
import com.example.loginpage.utils.helper.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ChatViewModel @Inject constructor(
    private val repsotry: RegsterImplements,
    private val repsost: FirebaseSirvase,
) : ViewModel(){
    private val _SendMesseageState=  MutableStateFlow<MessegSendState>(MessegSendState.Idel)
    var SendMessageState = _SendMesseageState.asStateFlow()
    private val _getMeesages = MutableStateFlow<UiState<List<MessegDiteals>>>(UiState.Idel)
    val getMessges=_getMeesages.asStateFlow()
    private val _newMessages=MutableStateFlow<UpdateStatChatState<MessegDiteals>>(UpdateStatChatState.Idel)
    val newMessaes get() = _newMessages.asStateFlow()
    private  val _useInfo = MutableStateFlow<CurrenUserStatis?>(CurrenUserStatis())
    val useInfo = _useInfo.asStateFlow()
    var ResverData: CurrenUserStatis? by mutableStateOf(null)
    var SenderuseuserData: CurrenUserStatis? by mutableStateOf(null)
    private val IntentChanenl = Channel<ChatPageIntnent> { Channel.UNLIMITED }
    init {
        processIntent()
        getMeesages()
        OnUpadteMessages()
    }

    private fun processIntent() {
        viewModelScope.launch {
            IntentChanenl.consumeAsFlow().collect {

                when (it) {
                    is ChatPageIntnent.SendMessegIntent -> sendMeesgee(it.message)
                   is ChatPageIntnent.MessageSendDOonet -> _SendMesseageState.value=MessegSendState.Idel
                    is ChatPageIntnent.SetChatIDelWhanMessaeNewAdd -> _newMessages.value=UpdateStatChatState.Idel
                    is ChatPageIntnent.SetDataMessageIDel -> _getMeesages.value=UiState.Idel
                }
            }
        }
    }
fun setIdelForGesstMasse(){
    viewModelScope.launch {
        IntentChanenl.send(ChatPageIntnent.SetDataMessageIDel)
    }
}
    fun  setIdelForNewMassege(){
        viewModelScope.launch {
            IntentChanenl.send(ChatPageIntnent.SetChatIDelWhanMessaeNewAdd)
        }
    }

fun setDoneMeesage(){
    viewModelScope.launch {
        IntentChanenl.send(ChatPageIntnent.MessageSendDOonet)
    }
}
    fun IntentSendMeesage(Messages:MessegDiteals){
        viewModelScope.launch {
            IntentChanenl.send(ChatPageIntnent.SendMessegIntent(Messages))
        }
    }

    private fun sendMeesgee(Messages:MessegDiteals){
        repsost.SendRealTimeMessages(Messages){
            viewModelScope.launch {
                _SendMesseageState.emit(it)
            }
        }
    }

    private fun getMeesages(){
        repsost.getRealTimeMessages {  viewModelScope.launch {
            _getMeesages.emit(it)
        }
    }
    }
    private fun OnUpadteMessages(){
        repsost.onChangeUpateMeease {
            viewModelScope.launch {
                _newMessages.emit(it)
            }
        }
    }

}