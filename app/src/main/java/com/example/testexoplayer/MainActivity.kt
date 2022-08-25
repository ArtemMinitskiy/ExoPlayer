package com.example.testexoplayer

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.testexoplayer.databinding.ActivityMainBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.RawResourceDataSource
import com.google.android.exoplayer2.util.Util

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializePlayer(R.raw.sphere_analysis, this)

        binding.sphereVideoView.setOnClickListener {
            Log.i("Click", "Click")
        }

    }

    private fun initializePlayer(sphereId: Int, activity: Activity) {
        binding.apply {

            val exoPlayer = ExoPlayer.Builder(activity).build()
            exoPlayer.repeatMode = Player.REPEAT_MODE_ALL

            sphereVideoView.player = exoPlayer
            val uri = RawResourceDataSource.buildRawResourceUri(sphereId).toString()
            val mediaItem = MediaItem.fromUri(Uri.parse(uri))
            val userAgent = Util.getUserAgent(activity, getString(R.string.app_name))
            var mediaSource = ProgressiveMediaSource.Factory(DefaultDataSourceFactory(activity, userAgent), DefaultExtractorsFactory()).createMediaSource(mediaItem)

            exoPlayer.prepare(mediaSource, true, false)
            exoPlayer.playWhenReady = true
        }

    }

}