package Components

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class SharedAuthViewModel : ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var otp by mutableStateOf("")

    fun clear(){
        email=""
        password=""
        otp=""
    }

    fun passClear(){
        password=""
    }
}