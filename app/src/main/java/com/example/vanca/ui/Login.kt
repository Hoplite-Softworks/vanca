package com.example.vanca.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

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
            OutlinedTextField(
                value = viewModel.username,
                onValueChange = { newValue -> viewModel.updateUsernameInput(newValue) },
                label = { Text(text = "Username") },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                modifier = Modifier
            )

            /*
            OutlinedTextField(
                value = viewModel.password,
                onValueChange = { newValue -> viewModel.updatePassword(newValue) },
                label = {Text(text = "Password")}
             */

            Button(
                onClick = {
                    if (viewModel.checkCredentials()) {
                        onSuccessfulLogin()
                    }
                    else {
                        viewModel.resetCredentials()
                    }
                }
            ) {
                Text("Login")
            }
        }

        AppLogo()

        AboutLink(
            modifier = Modifier.align(Alignment.BottomCenter),
            onTeamLinkClicked = onTeamLinkClicked
        )

    }


}

@Preview
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