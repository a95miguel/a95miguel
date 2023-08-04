package com.medel.notas.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.medel.notas.R
import com.medel.notas.databinding.FragmentAddTaskBinding
import com.medel.notas.model.TaskData

class AddTaskFragment : DialogFragment() {
    private lateinit var binding: FragmentAddTaskBinding
    private lateinit var listener: DialogNextBtnClickListener
    private var taskData: TaskData? = null

    fun setListener(listener: DialogNextBtnClickListener) {
        this.listener = listener
    }

    companion object {
        const val TAG = "DialogFragment"

        @JvmStatic
        fun newIntance(id: String, task: String) = AddTaskFragment().apply {
            arguments = Bundle().apply {
                putString("id", id)
                putString("task", task)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Comprueba si hay argumentos pasados al fragmento (Bundle).
        if (arguments != null) {
            // Si hay argumentos, extrae el valor 'id' y 'task' del Bundle y crea un objeto TaskData.
            taskData = TaskData(
                arguments?.getString("id").toString(),
                arguments?.getString("task").toString()
            )
        }
        binding.etTask.setText(taskData?.task)
        registerEvents()
    }

    private fun registerEvents() {
        binding.btnNext.setOnClickListener {
            // Obtiene el texto ingresado en el campo de texto 'etTask'.
            val task = binding.etTask.text.toString()
            // Verifica si el campo de texto no está vacío.
            if (task.isNotEmpty()) {
                // Si 'taskData' es nulo, significa que es una nueva tarea y se debe agregar.
                if (taskData == null) {
                    // Llama al método onSaveTask del escuchador (listener), pasando el texto de la tarea y el campo de texto 'etTask'.
                    listener.onSaveTask(task, binding.etTask)
                } else {
                    // Si 'taskData' no es nulo, significa que ya existe una tarea y se debe editar.
                    // Actualiza el texto de la tarea en 'taskData' con el valor ingresado en 'etTask'.
                    taskData?.task = task
                    // Llama al método onEditTask del escuchador (listener), pasando el objeto actualizado de 'taskData' y el campo de texto 'etTask'.
                    listener.onEditTask(taskData!!, binding.etTask)
                }
            } else {
                Toast.makeText(context, R.string.verefy_fields, Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnClose.setOnClickListener {
            dismiss()
        }
    }

    interface DialogNextBtnClickListener {
        fun onSaveTask(task: String, etTask: TextInputEditText)
        fun onEditTask(taskData: TaskData, etTask: TextInputEditText)
    }
}