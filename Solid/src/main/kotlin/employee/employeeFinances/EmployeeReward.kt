package employee.employeeFinances

import employee.BaseEmployee

interface EmployeeReward : BaseEmployee {
    fun calculateRewards(id: Int)
}