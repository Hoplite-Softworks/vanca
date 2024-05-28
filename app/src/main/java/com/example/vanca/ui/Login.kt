package com.example.vanca.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vanca.R

@Composable
fun LoginScreen(
    viewModel: AppViewModel,
    onSuccessfulLogin: () -> Unit,
    onTeamLinkClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Login screen content
    Box(
        modifier = modifier
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {

            Text(
                text = "Sign in",
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                color = colorResource(id = R.color.textColor),
                fontFamily = FontFamily(Font(R.font.open_sans_semibold)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = viewModel.username,
                onValueChange = { newValue -> viewModel.updateUsernameInput(newValue) },
                label = { Text(text = "Username") },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    viewModel.checkCredentials(
                        onSuccessfulLogin
                    )
                }),
                textStyle = TextStyle(
                    color = colorResource(id = R.color.textColor),
                    fontFamily = FontFamily(Font(R.font.open_sans)),
                    fontSize = 20.sp
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedTextColor = colorResource(id = R.color.textColor),
                ),
                modifier = Modifier
            )

            OutlinedTextField(
                value = viewModel.password,
                onValueChange = { newValue -> viewModel.updatePasswordInput(newValue) },
                label = { Text(text = "Password") },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done, keyboardType = KeyboardType.Password),
                keyboardActions = KeyboardActions(onDone = {
                    viewModel.checkCredentials(
                        onSuccessfulLogin
                    )
                }),
                visualTransformation = PasswordVisualTransformation(),
                textStyle = TextStyle(
                    color = colorResource(id = R.color.textColor),
                    fontFamily = FontFamily(Font(R.font.open_sans)),
                    fontSize = 20.sp
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedTextColor = colorResource(id = R.color.textColor),
                ),
                modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
            )

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                Text(
                    text = "Don't have an account? Register",
                    textAlign = TextAlign.Left,
                    textDecoration = TextDecoration.Underline,
                    fontSize = 12.sp,
                    color = colorResource(id = R.color.textColor),
                    fontFamily = FontFamily(Font(R.font.open_sans)),
                    modifier = Modifier.clickable {})

                Button(
                    onClick = { viewModel.checkCredentials(onSuccessfulLogin) },
                    modifier = Modifier
                ) {
                    Text("Sign in")
                }
            }


        }

        AppLogo()

        AboutLink(
            modifier = Modifier.align(Alignment.BottomCenter),
            onTeamLinkClicked = onTeamLinkClicked
        )

    }


}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFF0)
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        viewModel(),
        {},
        {},
        modifier = Modifier
            .fillMaxSize()
    )
}