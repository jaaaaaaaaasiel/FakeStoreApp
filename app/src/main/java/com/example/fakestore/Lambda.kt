package com.example.fakestore

fun main(){
    val uno = 1
    val dos = 2

    operacionDeNumeros(uno , dos, { a,b -> suma(a,b) })
}

//Funcion que reciba dos numeros y que regrese dos numeros

fun operacionDeNumeros(a : Int, b : Int, operacion : (Int, Int) -> Int ) {
    println("El numero A vale: ${a}")
    println("El numero B vale: ${b}")
    val result = operacion(a , b)
    println(result)
}


fun suma(a: Int, b:Int) : Int{
    return a + b
}

fun resta(a: Int, b:Int) : Int{
    return a - b
}
