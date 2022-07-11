package com.example.booster.logic

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject
import com.dsc.form_builder.SelectState
import com.dsc.form_builder.FormState
import com.dsc.form_builder.ChoiceState
import com.dsc.form_builder.TextFieldState
import com.dsc.form_builder.Validators
import com.example.booster.ui.form.Credentials

class InvestorTypeViewModel @Inject constructor(
    private val app: Application
) : AndroidViewModel(app) {

    private val viewModelState = MutableStateFlow(
        InvestorViewModelState(
            formState = FormState(
                fields = listOf(
                    ChoiceState(
                        name = "gender",
                        validators = listOf(Validators.Required(message = "you need to specify your gender"))
                    ),
                    TextFieldState(
                        name = "email",
                        transform = { it.trim().lowercase() },
                        validators = listOf(Validators.Email()),
                    ),
                    TextFieldState(
                        name = "password",
                        validators = listOf(Validators.Required())
                    ),
                    TextFieldState(
                        name = "age",
                        transform = { it.toInt() },
                        validators = listOf(Validators.MinValue(limit = 18, message = "too young"))
                    ),
                    TextFieldState(
                        name = "happiness",
                        transform = { it.toFloat() },
                        validators = listOf(Validators.Required(message = "how happy are you?"))
                    ),
                    SelectState(
                        name = "hobbies",
                        validators = listOf(
                            Validators.Required(message = "pick at least one hobby")
                        )
                    )
                )
            )
        )
    )

    val uiState = viewModelState
        .map { it.toUiState() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            viewModelState.value.toUiState(),
        )

    fun submit() {
        if (viewModelState.value.formState.validate()) {
            val data = viewModelState.value.formState.getData(Credentials::class)
            // TODO:
            // submit the data from the form - me@example.com
            // return to home screen(?)
            // update state to submitted
            Toast.makeText(app.applicationContext, data.toString(), Toast.LENGTH_LONG).show()
            println("submit: data from the form $data")
        }
    }

    init {
        requestScreenData()
    }

    private fun requestScreenData() {
        viewModelScope.launch {
            // delay so we can see the loading composable
            delay(2000)
            val fundDetails = parseJSON()
            viewModelState.update {
                it.copy(
                    isLoading = false,
                    fundDetail = fundDetails
                )
            }
        }
    }

    private fun readJSONFromAsset(): String {
        lateinit var jsonString: String
        try {
            jsonString = app.assets.open("investor_types_and_funds.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (exception: IOException) {
            exception.printStackTrace()
        }
        return jsonString
    }

    private fun parseJSON(): List<FundDetailsDTO> {
        val typeToken = object : TypeToken<List<FundDetailsDTO>>() {}.type
        return Gson().fromJson(readJSONFromAsset(), typeToken)
    }
}