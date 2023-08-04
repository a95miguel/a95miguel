package com.medel.notas.adapter

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.medel.notas.databinding.ItemTaskBinding
import com.medel.notas.model.TaskData
import com.medel.notas.R

class TaskAdapter(private val list: MutableList<TaskData>) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    private var listener: TaskAdapterClickAdapter? = null

    fun setListener(listener: TaskAdapterClickAdapter) {
        this.listener = listener
    }

    inner class TaskViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        // Usando el alcance 'with', se accede más fácilmente a las vistas y datos del ViewHolder.
        with(holder) {
            with(list[position]) {
                // Asigna el texto de la tarea al TextView dentro del ViewHolder.
                binding.tvTask.text = this.task
                // Cuando se hace clic en el botón, se llama al método onDeleteTaskBtn del escuchador (listener),
                // pasando la tarea actual como argumento.
                binding.btnDelete.setOnClickListener {
                    listener?.onDeleteTaskBtn(this)
                }
                // Cuando se hace clic en el botón, se llama al método onEditTaskBtn del escuchador (listener),
                // pasando la tarea actual como argumento.
                binding.btnEdit.setOnClickListener {
                    listener?.onEditTaskBtn(this)
                }
            }
            holder.binding.cardView.animation =
                AnimationUtils.loadAnimation(holder.itemView.context, R.anim.anim_recycleview)
        }
    }


    override fun getItemCount(): Int {
        return list.size
    }

    /**
     * Interfaz para gestionar eventos de clic en elementos del adaptador de tareas (TaskAdapter).
     */
    interface TaskAdapterClickAdapter {
        fun onDeleteTaskBtn(taskData: TaskData)
        fun onEditTaskBtn(taskData: TaskData)
    }
}