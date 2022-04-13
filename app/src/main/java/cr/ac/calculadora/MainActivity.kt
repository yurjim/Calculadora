package cr.ac.calculadora

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.mariuszgromada.math.mxparser.Expression

class MainActivity : AppCompatActivity() {

    var texto: TextView ? = null
    var num1 = 0.0
    var num2= 0.0
    var operacion = 0
    var signo: String = " "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Variables
        texto = findViewById(R.id.textoInicial)
        texto?.text = "0"
        operacion = NO_OPER

        val btn0: Button = findViewById(R.id.button0)
        val btn1: Button = findViewById(R.id.button1)
        val btn2: Button = findViewById(R.id.button2)
        val btn3: Button = findViewById(R.id.button3)
        val btn4: Button = findViewById(R.id.button4)
        val btn5: Button = findViewById(R.id.button5)
        val btn6: Button = findViewById(R.id.button6)
        val btn7: Button = findViewById(R.id.button7)
        val btn8: Button = findViewById(R.id.button8)
        val btn9: Button = findViewById(R.id.button9)
        val btnC: Button = findViewById(R.id.buttonC)
        val btnComa: Button = findViewById(R.id.buttonComa)

        val btnSuma: Button = findViewById(R.id.buttonMas)
        val btnResta: Button = findViewById(R.id.buttonMenos)
        val btnMulti: Button = findViewById(R.id.buttonX)
        val btnDivi: Button = findViewById(R.id.buttonD)
        val btnPorce: Button = findViewById(R.id.buttonP)
        val btnIgual: Button = findViewById(R.id.buttonIgual)


        btn0.setOnClickListener{ buttonOnClick("0" ) }
        btn1.setOnClickListener{ buttonOnClick("1") }
        btn2.setOnClickListener{ buttonOnClick("2") }
        btn3.setOnClickListener{ buttonOnClick("3") }
        btn4.setOnClickListener{ buttonOnClick("4") }
        btn5.setOnClickListener{ buttonOnClick("5") }
        btn6.setOnClickListener{ buttonOnClick("6") }
        btn7.setOnClickListener{ buttonOnClick("7") }
        btn8.setOnClickListener{ buttonOnClick("8") }
        btn9.setOnClickListener{ buttonOnClick("9") }
        btnComa.setOnClickListener{ buttonOnClick(".") }

        btnSuma.setOnClickListener { operacionPresionada(SUMA) }
        btnResta.setOnClickListener { operacionPresionada(RESTA) }
        btnMulti.setOnClickListener { operacionPresionada(MULTI) }
        btnDivi.setOnClickListener { operacionPresionada(DIVI) }
        btnPorce.setOnClickListener { operacionPresionada(PORCE) }

        btnC.setOnClickListener{ borrar()}

        btnIgual.setOnClickListener {
            var res = when(operacion){
                SUMA -> num1 + num2
                RESTA -> num1 - num2
                MULTI -> num1 * num2
                DIVI -> num1 / num2
                PORCE -> num1/ 100
                else -> 0
            }
            num1 = res as Double

            texto?.text = if("$res".endsWith(".0")) { "$res".replace(".0","") } else { "%.2f".format(res) }
        }
    }


    private fun buttonOnClick(digito: String) {

        if(texto?.text == "0" && digito != ".") {
            texto?.text = "$digito"
        } else {
            texto?.text = "${texto?.text}$digito"
        }

        if(operacion == NO_OPER){
            num1 = texto?.text.toString().toDouble()
        } else {
            num2 = texto?.text.toString().toDouble()
        }
    }

    private fun operacionPresionada(ope: Int){
        this.operacion = ope

        num1 =  texto?.text.toString().toDouble()

        texto?.text  = " "

        if(ope == 1){
            signo = "+"
            //texto?.text = signo
        }/*else if(ope == 2) {
            signo = "-"
            texto?.text = signo
        }else if(operacion == 3) {
            signo = "*"
            texto?.text = signo
        }else if(operacion == 4) {
            signo = "/"
            texto?.text = signo
        }else{
            signo = "%"
            texto?.text = signo
        }*/
    }

    private fun borrar(){
        texto?.text = "0"
        num1 = 0.0
        num2 = 0.0
    }


    companion object{
        const val SUMA=1
        const val RESTA=2
        const val MULTI=3
        const val DIVI=4
        const val PORCE=5
        const val NO_OPER=0
    }


}