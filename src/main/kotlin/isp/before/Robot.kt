package isp.before

import Worker

class Robot(private val numberRobot:Int) : Worker {
    override fun work() {
        // Implementation code removed for better clarity.
    }

    override fun eat() {
        // ❌ ISP (Interface Segregation Principle) violation occurs when a class does not need a method.
        // This method is not applicable to a robot.
        throw UnsupportedOperationException("Robots don't eat!")
    }
    
}
