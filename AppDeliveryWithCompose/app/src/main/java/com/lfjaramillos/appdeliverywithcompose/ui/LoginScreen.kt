package com.lfjaramillos.appdeliverywithcompose.ui

import android.service.autofill.OnClickAction
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lfjaramillos.appdeliverywithcompose.R

@Composable

fun LoginScreen(  isLoading :Boolean, onLoginClickAction:()-> Unit) {
    val logo = painterResource(id = R.drawable.ic_launcher_background)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = stringResource(id = R.string.login_title),
            style = MaterialTheme.typography.h3
        )
        Image(
            painter = logo,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(0.3f)
                .padding(horizontal = 32.dp)
        )
        Text(
            text = stringResource(id = R.string.login_welcome_message),
            style = MaterialTheme.typography.subtitle1
        )
        if(isLoading){
            CircularProgressIndicator()
        }else{
            Button(onClick = onLoginClickAction) {
                Text(text = stringResource(id = R.string.login_login_with_google))
            }
        }
        LegalStuff()

    }

}

@Composable
fun LegalStuff() {
    val annotatedString = buildAnnotatedString {
        append(stringResource(id = R.string.login_Description))
        append(" ")
        pushStringAnnotation(tag = "URL", annotation = "app://terms")
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.secondary
            )
        ) {
            append(stringResource(id = R.string.login_terms))
        }


        append(" ")
        append(stringResource(id = R.string.login_and))
        append(" ")
        pushStringAnnotation(tag = "URL", annotation = "app://privacy")
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.secondary
            )
        ) {
            append(stringResource(id = R.string.login_conditions))
        }


        pop()
    }
    Box(contentAlignment = Alignment.Center) {
        ClickableText(
            text = annotatedString,
            modifier = Modifier.padding(24.dp)
        ) { offset ->
            annotatedString.getStringAnnotations("URL", offset, offset)
                .firstOrNull()?.let { tag -> Log.i("App", "Click en ${tag.item}") }

        }
    }

}

@Composable
@Preview
fun LoginPreview(){
    LoginScreen(isLoading = true ) {
            // No -op
    }
}



