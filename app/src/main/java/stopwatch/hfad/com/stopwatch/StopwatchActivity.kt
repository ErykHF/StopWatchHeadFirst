package stopwatch.hfad.com.stopwatch

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_stopwatch.*

class StopwatchActivity : AppCompatActivity() {

    private var seconds: Int = 0
    private var isRunning: Boolean = false
    private var wasRunning: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stopwatch)

        savedInstanceState?.let {
           seconds = it.getInt("seconds")
           isRunning =  it.getBoolean("isRunning")
           wasRunning= it.getBoolean("wasRunning")

        }

        runTimer()
        startTimer()
        stopTimer()
        resetTimer()
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putInt("seconds", seconds)
        savedInstanceState.putBoolean("isRunning", isRunning)
        savedInstanceState.putBoolean("wasRunning", wasRunning)
    }

    override fun onStop() {
        super.onStop()
        wasRunning = isRunning
        isRunning = false
    }

    override fun onStart() {
        super.onStart()
        if (wasRunning) {
            isRunning = true
        }
    }


    fun startTimer() {

        start_button.setOnClickListener {
            isRunning = true
        }
    }

    fun stopTimer() {
        stop_button.setOnClickListener {
            isRunning = false
        }
    }

    fun resetTimer() {

        reset_button.setOnClickListener {
            isRunning = false
            seconds = 0
        }
    }


    private fun runTimer() {
        val handler = Handler()

        handler.post(object : Runnable {
            override fun run() {
                val hours: Int = seconds / 3600
                val minutes: Int = (seconds % 3600) / 60
                val secs: Int = seconds % 60

                val time: String = String.format("%d:%02d:%02d", hours, minutes, secs)
                time_view.text = time
                if (isRunning) {
                    seconds++

                }
                handler.postDelayed(this, 1000)
            }
        })

    }
}




