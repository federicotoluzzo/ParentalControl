import org.w3c.dom.events.EventListener
import java.awt.*
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.lang.Thread.sleep
import javax.swing.JFrame
import javax.swing.JLabel

class Timer : JFrame{

    var startTime: Long = 0
    val duration: Long
    var endTime: Long = 0

    var showTime = JLabel()

    constructor(duration: Long) {
        this.duration = duration
    }

    fun start() {
        startTime = System.currentTimeMillis()
        endTime = startTime + duration
        add(showTime)
        isUndecorated = true
        isVisible = true
        layout = FlowLayout()
        background = Color(255,255,255, 128)
        showTime.foreground = Color.BLACK
        showTime.font = Font("Timer Font", Font.BOLD, 32)
        isAlwaysOnTop = true
        pack()

        Thread{
            while(endTime - System.currentTimeMillis() > 0){
                showTime.text = getClock()
                sleep(1000)
                pack()
            }
            lockScreen()
        }.start()
    }

    fun getClock():String{
        val secondsLeft:Long
        val minutesLeft:Long
        val hoursLeft:Long

        var timeLeft = endTime - System.currentTimeMillis();
        timeLeft /= 1000 //get the number of seconds left

        secondsLeft = timeLeft % 60
        timeLeft /= 60
        minutesLeft = timeLeft % 60
        timeLeft /= 60
        hoursLeft = timeLeft % 60

        return "$hoursLeft:$minutesLeft:$secondsLeft"
    }

    fun lockScreen(){
        val lock = JFrame()
        lock.isUndecorated = true
        lock.background = Color(255,0,0, 128)
        lock.size = Dimension(Toolkit.getDefaultToolkit().screenSize.width, Toolkit.getDefaultToolkit().screenSize.height)
        lock.isAlwaysOnTop = true
        lock.isVisible = true
    }
}