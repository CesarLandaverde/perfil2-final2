package cesar.landaverde.cesarperfil222222222222222

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class InformacionDetalle : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_informacion_detalle)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tituloReci = intent.getStringExtra("titulo")
        val numeroReci = intent.getStringExtra("numTicket")
        val descrpReci = intent.getStringExtra("descripcion")
        val autorRecip = intent.getStringExtra("autor")
        val emalrecip = intent.getStringExtra("email")
        val estadoRecip = intent.getStringExtra("estado")

        val lblTitulo = findViewById<TextView>(R.id.lblTitulo)
        val lblNumero = findViewById<TextView>(R.id.lblNumero)
        val lblDescripcion = findViewById<TextView>(R.id.lblDescripcion)
        val lblAutor = findViewById<TextView>(R.id.lblAutor)
        val lblEmail = findViewById<TextView>(R.id.lblEmail)
        val lblEstado = findViewById<TextView>(R.id.lblEstado)

        lblTitulo.text = tituloReci
        lblNumero.text = numeroReci
        lblDescripcion.text = descrpReci
        lblAutor.text = autorRecip
        lblEmail.text = emalrecip
        lblEstado.text = estadoRecip

        val imgVolver = findViewById<ImageView>(R.id.imgVolver)
        imgVolver.setOnClickListener{
           val volver = Intent(this, InformacionDetalle::class.java)
            startActivity(volver)
        }
    }
}