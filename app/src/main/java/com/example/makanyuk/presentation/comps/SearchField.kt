package com.example.makanyuk.presentation.comps
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.makanyuk.ui.theme.Gray4
import java.security.Key

@ExperimentalMaterial3Api
@Composable
fun CustomSearchField(
    modifier: Modifier = Modifier,
    placeholder: String,
    input: String,
    onValueChange : (String) -> Unit,
    onSearch: () -> Unit
) {

    OutlinedTextField(
        value = input,
        onValueChange = onValueChange,
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        modifier = modifier.fillMaxWidth().height(50.dp),
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
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onSearch = {onSearch()},
            onDone = {onSearch()}
        ),
        placeholder = {
            Text(
                placeholder,
                color = Gray4,
                style = MaterialTheme.typography.labelSmall
            )
        },
        leadingIcon = {
            Icon(Icons.Filled.Search, contentDescription = "")
        },
        textStyle = MaterialTheme.typography.labelSmall,

    )
}