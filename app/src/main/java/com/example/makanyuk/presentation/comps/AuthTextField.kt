package com.example.makanyuk.presentation.comps

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.makanyuk.ui.theme.Gray4
import com.example.makanyuk.ui.theme.StarterProjectTheme

@Composable
fun AuthTextField(
    modifier: Modifier = Modifier,
    placeholder: String,
    input: String,
    onValueChange : (String) -> Unit
) {
    OutlinedTextField(
        value = input,
        onValueChange = onValueChange,
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        modifier = modifier.fillMaxWidth().height(70.dp),
        colors = TextFieldDefaults.colors(
            disabledContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            focusedTextColor = Color.Black,
            focusedIndicatorColor = Color.Black,
            focusedLeadingIconColor = Color.Black,
            unfocusedIndicatorColor = Color.Gray,
            cursorColor = Color.Black
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        placeholder = {
            Text(
                placeholder,
                color = Gray4,
                style = MaterialTheme.typography.labelSmall
            )
        },
        textStyle = MaterialTheme.typography.labelSmall,
        maxLines = 1
    )
}

@Preview
@Composable
private fun field() {
    StarterProjectTheme {
        AuthTextField(
            placeholder = "Email",
            input = ""
        ) { }
    }
}