package cesar.landaverde.cesarperfil222222222222222

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import modelo.ClaseConexion
import java.util.UUID

class RegistrarDatos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrar_datos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val txtTitulo = findViewById<EditText>(R.id.txtTituloTicket)
        val txtNum = findViewById<EditText>(R.id.txtNumTicket)
        val txtdescrip = findViewById<EditText>(R.id.txtDescripregister)
        val txtemail = findViewById<EditText>(R.id.txtEmail)
        val txtautor = findViewById<EditText>(R.id.txtAutor)
        val txtestado = findViewById<EditText>(R.id.txtEstado)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrarmne)
        val btnVerRegistro = findViewById<Button>(R.id.btnVerRegistro)


        btnRegistrar.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val objConexion = ClaseConexion().cadenaConexion()
                val agreTickets = objConexion?.prepareStatement("Insert into Tickets(uuid,numero_ticket,titulo_ticket,descrip_ticket,autor_ticket,email_ticket,estado_ticket) Values(?,?,?,?,?,?,?)")!!

                agreTickets.setString(1,UUID.randomUUID().toString())
                agreTickets.setString(2,txtNum.text.toString())
                agreTickets.setString(3,txtTitulo.text.toString())
                agreTickets.setString(4,txtdescrip.text.toString())
                agreTickets.setString(5,txtautor.text.toString())
                agreTickets.setString(6,txtemail.text.toString())
                agreTickets.setString(7,txtestado.text.toString())

                agreTickets.executeUpdate()




            }
        }

        btnVerRegistro.setOnClickListener {
           val pantallaRegistrar = Intent(this,Login::class.java)
            startActivity(pantallaRegistrar)
        }

        val cerrarsesion = findViewById<ImageView>(R.id.imgCerrar)

        cerrarsesion.setOnClickListener{

            val context = this

            val builder = AlertDialog.Builder(context)

            builder.setTitle("Log Out")
            builder.setMessage("¿Desea Cerrar Sesión?")

            builder.setPositiveButton("Si"){dialog, which ->

                val pLogin = Intent(this,Login::class.java)
                startActivity(pLogin)
            }

            builder.setNegativeButton("No"){ dialog, which ->

                dialog.dismiss()
            }

            val dialog = builder.create()

            dialog.show()

        }





    }
}