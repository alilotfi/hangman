package ir.alilo.hangman.screens.lost

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.viewport.ScreenViewport
import ir.alilo.hangman.Hangman
import ir.alilo.hangman.Resources


class LostScreen(private val hangman: Hangman) : ScreenAdapter() {
    private lateinit var music: Music
    private lateinit var message: Label
    private lateinit var stage: Stage

    override fun show() {
        music = Gdx.audio.newMusic(Gdx.files.internal(Resources.Sounds.congrats))
        music.play()
        music.isLooping = true

        val viewport = ScreenViewport()
        stage = Stage(viewport, hangman.batch)
        message = Label(Resources.Strings.dogsShit, Label.LabelStyle(hangman.largeFont, Color.GOLD))

        val layout = GlyphLayout(hangman.largeFont, Resources.Strings.dogsShit)
        message.setPosition(
            (Gdx.graphics.width - layout.width) / 2,
            (Gdx.graphics.height - layout.height) / 2
        )
        stage.addActor(message)
    }

    override fun render(delta: Float) {
        drawBackground()
        stage.draw()
    }

    private fun drawBackground() {
        Gdx.gl.glClearColor(Color.BROWN.r, Color.BROWN.g, Color.BROWN.b, Color.BROWN.a)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    }

    override fun hide() {
        music.dispose()
        stage.dispose()
    }
}