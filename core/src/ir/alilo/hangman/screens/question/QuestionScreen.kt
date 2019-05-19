package ir.alilo.hangman.screens.question

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.utils.viewport.ScreenViewport
import ir.alilo.hangman.Hangman
import ir.alilo.hangman.Resources
import ir.alilo.hangman.components.*
import ir.alilo.hangman.screens.answer.AnswerScreen

class QuestionScreen(private val hangman: Hangman) : ScreenAdapter(), KeyboardListener {
    private lateinit var textArea: TextArea
    private lateinit var stage: Stage

    override fun show() {
        val viewport = ScreenViewport()
        stage = Stage(viewport, hangman.batch)

        val keyboard = Keyboard(hangman.largeFont, this)
        stage.addActor(keyboard)
        textArea = TextArea(hangman.largeFont, initialRevealed = true)
        stage.addActor(textArea)
        val confirmButton = buildConfirmButton()
        stage.addActor(confirmButton)

        Gdx.input.inputProcessor = stage
    }

    override fun render(delta: Float) {
        drawBackground()
        stage.draw()
    }

    private fun drawBackground() {
        Gdx.gl.glClearColor(Color.SKY.r, Color.SKY.g, Color.SKY.b, Color.SKY.a)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    }

    private fun buildConfirmButton(): Actor {
        val size = Gdx.graphics.width / 10f
        val style = KeyboardButton.buildStyle(hangman.largeFont)
        val button = TextButton(Resources.Strings.confirm, style)
        button.setPosition((Gdx.graphics.width - size) / 2, (Gdx.graphics.height - size) / 2)
        button.setSize(size, size)
        button.addListener(ButtonListener())
        return button
    }

    override fun hide() {
        stage.dispose()
    }

    override fun onButtonPressed(char: Char?, type: KeyboardButtonType) {
        when (type) {
            KeyboardButtonType.CHARACTER -> char?.let { textArea.addCharacter(it) }
                ?: throw IllegalArgumentException("Character of type CHARACTER should not be null")
            KeyboardButtonType.DELETE -> textArea.removeCharacter()
            KeyboardButtonType.SPACE -> textArea.addSpace()
        }
    }

    private inner class ButtonListener : ChangeListener() {
        override fun changed(event: ChangeEvent?, actor: Actor?) {
            if (textArea.getText().isNotEmpty()) {
                hangman.screen = AnswerScreen(hangman, textArea.getText())
            }
        }
    }
}
