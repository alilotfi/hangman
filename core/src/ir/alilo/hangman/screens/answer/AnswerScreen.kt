package ir.alilo.hangman.screens.answer

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ScreenViewport
import ir.alilo.hangman.Hangman
import ir.alilo.hangman.components.Keyboard
import ir.alilo.hangman.components.KeyboardButtonType
import ir.alilo.hangman.components.KeyboardListener
import ir.alilo.hangman.components.TextArea
import ir.alilo.hangman.screens.WinScreen

class AnswerScreen(private val hangman: Hangman, private val text: String) : ScreenAdapter(),
    KeyboardListener {
    private lateinit var textArea: TextArea
    private lateinit var stage: Stage

    override fun show() {
        val viewport = ScreenViewport()
        stage = Stage(viewport, hangman.batch)

        val keyboard = Keyboard(hangman.largeFont, this, hasSpace = false, hasDelete = false)
        stage.addActor(keyboard)
        textArea = TextArea(hangman.largeFont, text, initialRevealed = false)
        stage.addActor(textArea)

        Gdx.input.inputProcessor = stage
    }

    override fun render(delta: Float) {
        drawBackground()
        stage.draw()
    }

    private fun drawBackground() {
        Gdx.gl.glClearColor(Color.LIME.r, Color.LIME.g, Color.LIME.b, Color.LIME.a)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    }

    override fun hide() {
        stage.dispose()
    }

    override fun onButtonPressed(char: Char?, type: KeyboardButtonType) {
        if (type != KeyboardButtonType.CHARACTER) {
            throw IllegalArgumentException("Only characters are allowed while answering")
        }
        char ?: throw IllegalArgumentException("Character of type CHARACTER should not be null")
        val revealed = textArea.revealCharacter(char)
        if (revealed && textArea.isRevealed()) {
            hangman.screen = WinScreen(hangman)
        }
    }
}