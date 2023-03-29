package com.example.voiceassitant_kot_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    class Mensaje(val text:String, val mensajeEntrante: Boolean)


    abstract class MensajesAdapter(private var context: Context) : BaseAdapter() {

        // Variable privada mensajes
        private var mensajes: ArrayList<Mensaje> = ArrayList()

        // Función agregarMensaje
        fun agregarMensaje(message: Mensaje) {
            this.mensajes.add(message)
            notifyDataSetChanged()
        }

        // Método getView
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val mensajesInflater: LayoutInflater = context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val mensajeActual = getItem(position)

            // Renderizo los mensajes Salientes y entrantes del Chat
            val vistaModificada = if (mensajeActual.mensajeEntrante)
                mensajesInflater.inflate(R.layout.mensaje_entrante, null)
            else
                mensajesInflater.inflate(R.layout.mensaje_saliente, null)

            // Cambio el cuerpo del Nuevo mensaje
            val cuerpoMensaje = vistaModificada.findViewById(R.id.cuerpo_mensaje) as TextView
            cuerpoMensaje.text = mensajeActual.text

            // Muestro la lista con los cambios realizados en los mensajes
            return vistaModificada
        }

        // Obtengo la posición de cada mensaje del Chat
        override fun getItem(position: Int): Mensaje {
            return mensajes[position]
        }

        // Obtengo el índice de un mensaje del Chat
        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        // Me Devuelve la cantidad o número de Mensajes en el Chat
        override fun getCount(): Int {
            return mensajes.size
        }

    }

}