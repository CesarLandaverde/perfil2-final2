package cesar.landaverde.cesarperfil222222222222222

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

        btnIniciar.setOnClickListener {
            val pantallaprinci = Intent(this, RegistrarDatos::class.java)
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val objConexion = ClaseConexion().cadenaConexion()


                    if (objConexion == null) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@Login, "Error en la conexión a la base de datos", Toast.LENGTH_SHORT).show()
                        }
                        return@launch
                    }

                    val checkUsuario = objConexion.prepareStatement("Select * From Usuarios where nombre_user = ? and contra_user = ?")

                    checkUsuario.setString(1, txtusuario.text.toString())
                    checkUsuario.setString(2, txtcontra.text.toString())

                    val resultado = checkUsuario.executeQuery()

                    if (resultado.next()) {
                        withContext(Dispatchers.Main) {
                            startActivity(pantallaprinci)
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@Login, "Parametros Incorrectos", Toast.LENGTH_SHORT).show()
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@Login, "Error en la consulta de la base de datos: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }


}