package ir.alilo.hangman.screens.win

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.ParticleEffect
import ir.alilo.hangman.Hangman
import ir.alilo.hangman.Resources


class WinScreen(private val hangman: Hangman) : ScreenAdapter() {
    private lateinit var effect: ParticleEffect
    private lateinit var music: Music

    override fun show() {
        music = Gdx.audio.newMusic(Gdx.files.internal(Resources.Sounds.congrats))
        music.play()
        music.isLooping = true

        effect = ParticleEffect()
        effect.load(
            Gdx.files.internal(Resources.Effects.flame),
            Gdx.files.internal(Resources.Effects.Particles.flameParticle)
        )
        effect.start()
        effect.setPosition(Gdx.graphics.width / 2f, Gdx.graphics.height / 4f)
    }

    override fun render(delta: Float) {
        drawBackground()
        hangman.batch.begin()
        effect.draw(hangman.batch, delta)
        hangman.batch.end()
    }

    private fun drawBackground() {
        Gdx.gl.glClearColor(Color.BLACK.r, Color.BLACK.g, Color.BLACK.b, Color.BLACK.a)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    }

    override fun hide() {
        effect.dispose()
        music.dispose()
    }
}