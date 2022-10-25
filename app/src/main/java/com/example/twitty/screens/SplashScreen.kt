package com.example.twitty.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import com.example.twitty.R
import com.example.twitty.screens.destinations.LoginScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.example.twitty.screens.destinations.PostScreenDestination
import kotlinx.coroutines.delay

@RootNavGraph(start = true )
@Destination
@Composable
fun SplashScreen(
    navigator: DestinationsNavigator
){
    val scale = remember{
        Animatable(0f)
    }

    LaunchedEffect(key1 = true){
        scale.animateTo(
            targetValue =  0.7f,
            animationSpec = tween(
                durationMillis = 1000,
                easing ={
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )
        delay(600L)
        navigator.navigate(LoginScreenDestination.route)
    }
    //Image
    Box(contentAlignment =  Alignment.Center, modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.twitterlogo),
            contentDescription = "Logo",
            modifier = Modifier.scale(scale.value))

    }
}