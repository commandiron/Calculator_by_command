package com.commandiron.calculator_by_command

class CalculatorProcess() {

    fun sum (num1: Double, num2: Double): Double{
        var sum = num1 + num2
        return sum
    }
    fun deduct (num1: Double, num2: Double): Double{
        var deduct = num1 - num2
        return deduct
    }
    fun multiply(num1: Double, num2: Double): Double{
        var multiply = num1 * num2
        return multiply
    }
    fun divide(num1: Double, num2: Double): Double{
        var divide = num1 / num2
        return divide
    }
    fun percentage(num1: Double): Double{
        var percentage = num1 / 100
        return percentage
    }
}