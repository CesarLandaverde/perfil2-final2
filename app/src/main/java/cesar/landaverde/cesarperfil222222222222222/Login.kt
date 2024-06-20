package cesar.landaverde.cesarperfil222222222222222

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import modelo.ClaseConexion

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val txtusuario = findViewById<EditText>(R.id.txtCorreoLogin)
        val txtcontra = findViewById<EditText>(R.id.txtContralogin)
        val btnIniciar = findViewById<Button>(R.id.btnIniciar)
        val btnRegistro = findViewById<Button>(R.id.btnRegistrarmne)

        btnRegistro.setOnClickListener {
          val pantallaRegistro = Intent(this, Registro::class.java)
            startActivity(pantallaRegistro)
        }

        btnIniciar.setOnClickListener{
            val pantallaprinci = Intent(this,RegistrarDatos::class.java)
            GlobalScope.launch(Dispatchers.IO) {
                val objConexion = ClaseConexion().cadenaConexion()

                val checkUsuario = objConexion?.prepareStatement("Select * From Usuarios where nombre_user = ? and contra_user = ? ")!!


            }
        }
    }


}