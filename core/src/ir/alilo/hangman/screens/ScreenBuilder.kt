package ir.alilo.hangman.screens

import com.badlogic.gdx.Screen
import ir.alilo.hangman.Hangman
import ir.alilo.hangman.screens.loading.LoadingScreen
import ir.alilo.hangman.screens.login.LoginScreen
import ir.alilo.hangman.screens.question.QuestionScreen

object ScreenBuilder {
    private var loadingScreenCache: LoadingScreen? = null

    fun buildLoadingScreen(hangman: Hangman): Screen {
        val localLoadingScreen = loadingScreenCache
        return if (localLoadingScreen == null) {
            val loadingScreen = LoadingScreen(hangman)
            loadingScreenCache = loadingScreen
            loadingScreen
        } else {
            localLoadingScreen
        }
    }

    fun buildLoginScreen(hangman: Hangman): Screen {
        return LoginScreen(hangman)
    }

    fun buildQuestionScreen(hangman: Hangman): Screen {
        return QuestionScreen(hangman)
    }
}
