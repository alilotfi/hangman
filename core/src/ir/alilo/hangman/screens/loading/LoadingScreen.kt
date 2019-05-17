package ir.alilo.hangman.screens.loading

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import ir.alilo.hangman.Hangman
import ir.alilo.hangman.Resources
import ir.alilo.hangman.screens.ScreenBuilder


class LoadingScreen(private val hangman: Hangman) : ScreenAdapter() {
    private lateinit var gholveImage: Texture

    override fun show() {
        gholveImage = Texture(Gdx.files.internal(Resources.Textures.gholve))
    }

    override fun render(delta: Float) {
        drawBackground()
        hangman.batch.begin()
        drawGholve()
        hangman.batch.end()
        setOnClickListener()
    }

    private fun drawBackground() {
        Gdx.gl.glClearColor(Color.WHITE.r, Color.WHITE.g, Color.WHITE.b, Color.WHITE.a)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    }

    private fun drawGholve() {
        hangman.batch.draw(
            gholveImage,
            (Gdx.graphics.width - gholveImage.width) / 2f,
            (Gdx.graphics.height - gholveImage.height) / 2f
        )
    }

    private fun setOnClickListener() {
        Gdx.input.inputProcessor = object : InputAdapter() {
            override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
                hangman.screen = ScreenBuilder.buildQuestionScreen(hangman)
                return true
            }
        }
    }

    override fun hide() {
        gholveImage.dispose()
    }
}
