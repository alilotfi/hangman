package ir.alilo.hangman.screens.question

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.badlogic.gdx.utils.viewport.Viewport
import ir.alilo.hangman.Hangman
import ir.alilo.hangman.components.Keyboard
import ir.alilo.hangman.components.KeyboardButtonType
import ir.alilo.hangman.components.KeyboardListener
import ir.alilo.hangman.components.TextArea

class QuestionScreen(private val hangman: Hangman) : ScreenAdapter(), KeyboardListener {
    private lateinit var textArea: TextArea
    private lateinit var viewport: Viewport
    private lateinit var camera: Camera
    private lateinit var stage: Stage

    override fun show() {
        viewport = ScreenViewport()
        camera = OrthographicCamera()
        stage = Stage(viewport, hangman.batch)

        val keyboard = Keyboard(hangman.largeFont, this)
        stage.addActor(keyboard)
        textArea = TextArea(hangman.largeFont)
        stage.addActor(textArea)

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
}
