package cesar.landaverde.cesarperfil222222222222222

import RecyclerViewHelper.Adaptador
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import modelo.ClaseConexion
import modelo.Tickets

class MostrarRegistro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mostrar_registro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val imgvvolviste = findViewById<ImageView>(R.id.imgvolver)
        imgvvolviste.setOnClickListener{
            val pantallaAnterior = Intent(this,RegistrarDatos::class.java)
            startActivity(pantallaAnterior)
        }

        val rcvtickets = findViewById<RecyclerView>(R.id.rcvTicket)
        rcvtickets.layoutManager = LinearLayoutManager(this)

       fun MostarDatos(): List<Tickets> {
           val objConexion = ClaseConexion().cadenaConexion()

           val statement = objConexion?.createStatement()
           val resultSet =statement?.executeQuery("Select * from tickets")!!
           val ticketss = mutableListOf<Tickets>()

           while (resultSet.next()) {
               val uuid = resultSet.getString("uuid")
               val numerotick = resultSet.getString("numero_Ticket")
               val titulotick = resultSet.getString("titulo_ticket")
               val descriptick = resultSet.getString("Descrip_ticket")
               val autortick = resultSet.getString("autor_ticket")
               val emailtick = resultSet.getString("email_autor")
               val estadotick = resultSet.getString("estado_ticket")

               val tickeet = Tickets(uuid,numerotick,titulotick,descriptick,autortick,emailtick,estadotick)

              ticketss.add(tickeet)
           }

            return ticketss
        }

        CoroutineScope(Dispatchers.IO).launch {

        val ticketdb = MostarDatos()

        withContext(Dispatchers.Main){

            val adapt = Adaptador(ticketdb)

            rcvtickets.adapter = adapt
        }

        }

    }
}