package com.winds.scratchandwin


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.winds.scratchcardlibrary.listener.ScratchListener
import com.winds.scratchcardlibrary.ui.ScratchCardLayout
import com.winds.scratchcardlibrary.util.ScratchCardUtils
import kotlinx.android.synthetic.main.activity_win_screen.*


class WinScreenActivity : AppCompatActivity(), ScratchListener {

    companion object{
        private val TAG = MainActivity::class.java.simpleName
    }

    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_win_screen)
        context = this

        initView()
    }

    private fun initView() {
        reset.setOnClickListener {
            resetLayout()
        }
        resetLayout()
        setControlPanelListeners()
    }

    private fun resetLayout() {
        //Java reset
        scratchCard.setScratchDrawable(ContextCompat.getDrawable(context, R.drawable.scratch))
        scratchCard.setScratchListener(this)
        scratchCard.setScratchWidthDip(ScratchCardUtils.dipToPx(context, 40f))
        scratchCard.setRevealFullAtPercent(40)
        scratchCard.setScratchEnabled(true)
        scratchCard.resetScratch()

        //Xml Reset
        //Xml Reset
        brushSizeSeekBar.progress = 40
        revealFullAtSeekBar.progress = 40
        scratchEffectToggle.isChecked = true
        scratchEffectToggle.text = getString(R.string.enabled)
    }


    private fun setControlPanelListeners() {

        brushSizeSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                scratchCard.setScratchWidthDip(ScratchCardUtils.dipToPx(context, seekBar!!.progress.toFloat()))
            }
        })


        scratchEffectToggle.setOnCheckedChangeListener { _, isChecked ->
            scratchCard.setScratchEnabled(isChecked)
            scratchEffectToggle.text = getString(if (isChecked) R.string.enabled else R.string.disabled)
        }


        //Scratch reveal at percent
        revealFullAtSeekBar.setOnSeekBarChangeListener(object:SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                Toast.makeText(context,"Restting view since reveal percent setting was changed",Toast.LENGTH_SHORT).show()
                scratchCard.setRevealFullAtPercent(seekBar!!.progress)
                scratchCard.resetScratch()
            }

        })
    }


    override fun onScratchProgress(scratchCardLayout: ScratchCardLayout?, atLeastScratchedPercent: Int) {
        Log.d(TAG, "Progress = $atLeastScratchedPercent")
    }

    override fun onScratchComplete() {
        Log.d(TAG, "Scratch ended")
    }

    override fun onScratchStarted() {
        Log.d(TAG, "Scratch started")
    }
}