package mx.edu.itson.calculadoraimcarmenta

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pesoK:EditText=findViewById(R.id.etKilos)
        val alturaE:EditText=findViewById(R.id.etEstatura)
        val calcular:Button=findViewById(R.id.btnCalcular)
        val imc:TextView=findViewById(R.id.tvIMC)
        val rango:TextView=findViewById(R.id.tvRange)

        calcular.setOnClickListener {
            var peso:Double=0.0
            var estatura: Double = 0.0

            try {
                peso=pesoK.text.toString().toDouble()
                estatura=alturaE.text.toString().toDouble()
            }catch (e:java.lang.Exception){
                imc.setText("Debe de ingresar valores reales")
                print(e)
            }
            var resultado = calcularIMC(peso, estatura)
            val formattedNumber=".%2f".format(resultado)

            imc.setText(formattedNumber)
            var salud:String
            var colour:Int

            when{
                resultado < 18.5 ->{
                    salud="Bajo peso"
                    colour=R.color.colourRed
                }
                resultado >= 18.5 && resultado <= 24.9 ->{
                    salud="Saludable"
                    colour=R.color.colourGreenish
                }
                resultado >= 25 && resultado <= 29.9 ->{
                    salud="Sobrepeso"
                    colour=R.color.colourYellow
                }
                resultado >= 30 && resultado <= 34.9 ->{
                    salud="Obesidad Grado 1"
                    colour=R.color.colourOrange
                }
                resultado >= 35 && resultado <= 39.9 ->{
                    salud="Obesidad Grado 2"
                    colour=R.color.colourBrown
                }
                resultado > 40 ->{
                    salud="Obesidad Grado 3"
                    colour=R.color.colourRed
                }
                else ->{
                    salud="Error"
                    colour = 0
                }
            }

            rango.setBackgroundColor(colour)
            rango.setText(salud)

        }
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun calcularIMC(peso:Double, estatura:Double):Double {
        return peso/(estatura*estatura)
    }
}