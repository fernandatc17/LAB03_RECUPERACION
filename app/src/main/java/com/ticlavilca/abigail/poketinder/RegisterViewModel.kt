import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel (
    val context: Context
) : ViewModel() {

    val inputsError = MutableLiveData<Boolean>()
    val registerSuccess = MutableLiveData<Boolean>()

    val emailError = MutableLiveData<Boolean>()
    val passwordError = MutableLiveData<Boolean>()
    val passwordConfirmationError = MutableLiveData<Boolean>()


    private var sharedPreferencesRepository: SharedPreferencesRepository =
        SharedPreferencesRepository().also {
            it.setSharedPreference(context)
        }

    fun registerUser(email: String, password: String,confirmPassword: String) {
        if (isEmptyInputs(email, password)) {
            inputsError.postValue(true)
            return
        }
        if (!isEmailValid(email)) {
            emailError.postValue(true)
            return
        }

        if (!isPasswordValid(password)) {
            passwordError.postValue(true)
            return
        }

        if (password != confirmPassword) {
            passwordConfirmationError.postValue(true)
            return
        }

        // Guardar el usuario y la contraseña en SharedPreferences
        sharedPreferencesRepository.saveUserEmail(email)
        sharedPreferencesRepository.saveUserPassword(password)
        registerSuccess.postValue(true)
    }

    private fun isEmptyInputs(email: String, password: String) = email.isEmpty() || password.isEmpty()
    private fun isEmailValid(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}"
        return email.matches(emailPattern.toRegex())
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length >= 8
    }
}
