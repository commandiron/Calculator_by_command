package com.commandiron.calculator_by_command

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var numberBtnList: List<Button>
    private lateinit var processBtnList: List<Button>

    private var getNumbersForSetInTextView = arrayListOf<String>()
    private var processNumber = ""

    private var num1: Double? = null
    private var num2: Double? = null
    private var result: Double? = null
    private var previousProcess = "="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNumberBtnList()
        createProccesBtnList()

        numberDedectAndWrite(numberBtnList)
        process(processBtnList)
    }

    fun createNumberBtnList(): List<Button> {
        numberBtnList = listOf(
            zero_btn,
            one_btn,
            two_btn,
            three_btn,
            four_btn,
            five_btn,
            six_btn,
            seven_btn,
            eight_btn,
            nine_btn,
            dot_btn
        )
        return numberBtnList
    }

    fun createProccesBtnList(): List<Button>{
        processBtnList = listOf(equal_btn,
            sum_btn,
            deduct_btn,
            multiply_btn,
            divide_btn,
            percentage_btn,
            equal_btn,
            C_btn,
            CE_btn)
        return processBtnList
    }

    fun numberDedectAndWrite(numberBtnList: List<Button>) {

        numberBtnList.forEach {
            it.setOnClickListener {

                when (it.id) {
                    zero_btn.id -> getNumbersForSetInTextView.add("0")
                    one_btn.id -> getNumbersForSetInTextView.add("1")
                    two_btn.id -> getNumbersForSetInTextView.add("2")
                    three_btn.id -> getNumbersForSetInTextView.add("3")
                    four_btn.id -> getNumbersForSetInTextView.add("4")
                    five_btn.id -> getNumbersForSetInTextView.add("5")
                    six_btn.id -> getNumbersForSetInTextView.add("6")
                    seven_btn.id -> getNumbersForSetInTextView.add("7")
                    eight_btn.id -> getNumbersForSetInTextView.add("8")
                    nine_btn.id -> getNumbersForSetInTextView.add("9")
                    dot_btn.id -> getNumbersForSetInTextView.add(".")
                }

                for (number in getNumbersForSetInTextView){
                    processNumber = processNumber + number
                }

                println(processNumber)

                process_tv.text = processNumber
                processNumber = ""
            }
        }
    }

    fun process(processBtnList: List<Button>){

        processBtnList.forEach { it.setOnClickListener {

            when(it.id){
                sum_btn.id -> {
                    previousProcess = "+"
                    sum()
                }
                deduct_btn.id -> {
                    previousProcess = "-"
                    deduct()
                }
                multiply_btn.id ->{
                    previousProcess = "*"
                    multiply()
                }
                divide_btn.id ->{
                    previousProcess = "/"
                    divide()
                }
                percentage_btn.id ->{
                    previousProcess = "%"
                    percentage()
                }

                equal_btn.id -> {when(previousProcess){
                    "="-> CButton()
                    "+"-> sum()
                    "-"-> deduct()
                    "*"-> multiply()
                    "/"-> divide()
                    "%"-> percentage()
                }}

                CE_btn.id -> {process_tv.text = ""
                    getNumbersForSetInTextView.clear()}

                C_btn.id ->{CButton()}
            }

            processSymbol_tv.setText(previousProcess)
        } }
    }

    fun sum(){

        setNumbersFromTextViews(previousProcess)

        result = CalculatorProcess().sum(num1!!, num2!! )

        setResult()
    }

    fun deduct(){

        setNumbersFromTextViews(previousProcess)

        if (result_tv.text.toString() == ""){
            result = CalculatorProcess().deduct(num2!!, num1!! )
        }else{
            result = CalculatorProcess().deduct(num1!!, num2!! )
        }

        setResult()
    }

    fun multiply(){

        setNumbersFromTextViews(previousProcess)

        result = CalculatorProcess().multiply(num1!!, num2!! )

        setResult()
    }

    fun divide(){

        setNumbersFromTextViews(previousProcess)

        if (result_tv.text.toString() == ""){
            result = CalculatorProcess().divide(num2!!, num1!! )
        }else{
            result = CalculatorProcess().divide(num1!!, num2!! )
        }

        setResult()
    }

    fun percentage(){

        setNumbersFromTextViews(previousProcess)

        result = CalculatorProcess().percentage(num1!!)

        setResult()
    }

    fun CButton(){
        previousProcess = "="
        setResult()
        result_tv.text = ""
        num1 = null
        num2 = null
    }

    fun setNumbersFromTextViews(previusProcess:String){
        if (result_tv.text.toString() != ""){
            num1 = result_tv.text.toString().toDouble()
        }else if(previusProcess == "+" || previusProcess == "-"){
            num1 = 0.0
        }else num1 = 1.0

        if (process_tv.text.toString() != ""){
            num2 = process_tv.text.toString().toDouble()
        }else if(previusProcess == "+" || previusProcess == "-"){
            num2 = 0.0
        }else num2 = 1.0
    }

    fun setResult(){
        result_tv.text = result.toString()
        process_tv.text = ""
        getNumbersForSetInTextView.clear()
    }
}
