package ocp.before

class PayCalculator(var currency: String) {

    fun calculatePay(typeEmployee: TypeEmployee) {
        if (typeEmployee == TypeEmployee.FULL_TIME) {
            // Implementation code removed for better clarity
        } else if (typeEmployee == TypeEmployee.PART_TIME) {
            // Implementation code removed for better clarity
        } else if (typeEmployee == TypeEmployee.CONTRACTOR) {
            // Implementation code removed for better clarity
        } else {
            // Implementation code removed for better clarity
        }
    }

    enum class TypeEmployee { FULL_TIME, PART_TIME, CONTRACTOR }
}
