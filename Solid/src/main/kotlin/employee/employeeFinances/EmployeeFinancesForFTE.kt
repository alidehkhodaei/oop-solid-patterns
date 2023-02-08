package employee.employeeFinances

class EmployeeFinancesForFTE(override val name: String, override val totalHoursWorked: Double) : EmployeeFinances {
    override fun calculatePay(id: Int) {
        if (id < 0) {
            throw IllegalArgumentException()
        }
        // Other implementation
    }

    override fun calculateRewards(id: Int) {
        // Implementation code removed for better clarity
    }

    override fun work() {
        // Implementation code removed for better clarity
    }
}