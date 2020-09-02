package com.example.calculator

import java.util.*

class CalculatorUtil {
    fun postfix(elementMath: Array<String?>?): Array<String?>? {
        var s1 = ""
        val E: Array<String?>
        val S: Stack<String> = Stack()
        if (elementMath != null) {
            for (i in elementMath.indices) {    // duyet cac phan tu
                val c = elementMath[i]?.get(0) // c la ky tu dau tien cua moi phan tu
                if (!c?.let { isOperator(it) }!!) // neu c khong la toan tu
                    s1 = s1 + " " + elementMath[i] // xuat elem vao s1
                else {                       // c la toan tu
                    if (c == '(') S.push(elementMath[i]) // c la "(" -> day phan tu vao Stack
                    else {
                        if (c == ')') {          // c la ")"
                            var c1: Char //duyet lai cac phan tu trong Stack
                            do {
                                c1 = S.peek().get(0)// c1 la ky tu dau tien cua phan tu
                                if (c1 != '(') s1 = s1 + " " + S.peek() // trong khi c1 != "("
                                S.pop()
                            } while (c1 != '(')
                        } else {
                            while (!S.isEmpty() && priority(S.peek().get(0)) >= priority(c)) {
                                // Stack khong rong va trong khi phan tu trong Stack co do uu tien >= phan tu hien tai
                                s1 = s1 + " " + S.peek() // xuat phan tu trong Stack ra s1
                                S.pop()
                            }
                            S.push(elementMath[i]) //  dua phan tu hien tai vao Stack
                        }
                    }
                }
            }
        }
        while (!S.isEmpty()) {   // Neu Stack con phan tu thi day het vao s1
            s1 = s1 + " " + S.peek()
            S.pop()
        }
        E = s1.split(" ".toRegex()).toTypedArray() //  tach s1 thanh cac phan tu
        return E
    }

    fun priority(c: Char): Int {        // thiet lap thu tu uu tien
        return if (c == '+' || c == '-') 1 else if (c == '*' || c == '/') 2 else 0
    }
    fun isOperator(c: Char): Boolean {  // kiem tra xem co phai toan tu
        val operator = charArrayOf('+', '-', '*', '/', ')', '(')
        Arrays.sort(operator)
        return if (Arrays.binarySearch(operator, c) > -1) true else false
    }
    fun processString(sMath: String): Array<String?>? { // xu ly bieu thuc nhap vao thanh cac phan tu
        var sMath = sMath
        var s1 = ""
        var elementMath: Array<String?>? = null
        sMath = sMath.trim { it <= ' ' }
        sMath = sMath.replace("\\s+".toRegex(), " ") //    chuan hoa sMath
        for (i in 0 until sMath.length) {
            val c = sMath[i] //sMath.substring(i,1);
            s1 = if (!isOperator(c)) s1 + c else "$s1 $c "
        }
        s1 = s1.trim { it <= ' ' }
        s1 = s1.replace("\\s+".toRegex(), " ") //  chuan hoa s1
        elementMath = s1.split(" ".toRegex()).toTypedArray() //tach s1 thanh cac phan tu
        return elementMath
    }

    fun valueMath(elementMath: Array<String?>?): String? {
        val S = Stack<String>()

        if (elementMath != null) {
            for (i in elementMath) {
                if (!i.equals("")){
                    val c = i?.get(0)

                    if (!c?.let { isOperator(it) }!!) S.push(i) else {
                        var num = 0.0
                        val num1 = S.pop().toDouble()
                        val num2 = S.pop().toDouble()
                        when (c) {
                            '+' -> num = num2 + num1
                            '-' -> num = num2 - num1
                            '*' -> num = num2 * num1
                            '/' -> num = num2 / num1
                            else -> {
                            }
                        }
                        S.push(num.toString())
                    }
                }
            }
        }
        println(S)
        return S.pop()
    }
    fun calculate(string: String): String? {
        return valueMath(postfix(processString(string)))
    }
}
