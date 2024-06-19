package modelo

import java.sql.Connection
import java.sql.DriverManager
class ClaseConexion {
   fun cadenaConexion():Connection?{
       try {
           val ip ="jdbc:oracle:thin:@192.168.0.5:1521:xe"
           val usuario="CESAR_DEVELOPER"
           val contrasena = "cesar2121"

           val conexion = DriverManager.getConnection(ip,usuario,contrasena)
           return conexion
       }
       catch (e:Exception){
           println("Error: $e")
           return null
       }
   }

}