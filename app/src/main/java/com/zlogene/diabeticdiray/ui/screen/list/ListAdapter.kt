package com.zlogene.diabeticdiray.ui.screen.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.zlogene.diabeticdiray.databinding.ListItemLayoutBinding
import com.zlogene.diabeticdiray.model.RecordingEntity
import com.zlogene.diabeticdiray.util.CustomDataConverter

class ListAdapter : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    class ListViewHolder(private val binding: ListItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recording: RecordingEntity) {
            binding.dataTextView.text = CustomDataConverter.getDate(recording.date, "EEE MMM dd YYYY HH:mm")
            binding.sugarTextView.text = recording.sugar.toString()
            binding.insulinTextView.text = recording.insulin.toString()
            binding.noteTextView.text = recording.textNote
        }

        companion object {
            // Метод принимает на вход родительский UI- элемент
            // куда будет помещен создавааемый viewHolder
            fun from(parent: ViewGroup): ListViewHolder {
                // Инициализатор вида ViewHolder
                val layoutInflater = LayoutInflater.from(parent.context)
                // Загрузка макета вида элемента списка list_item_layout,
                // Инициализирует элементы textViews и возвращает их
                val binding = ListItemLayoutBinding.inflate(layoutInflater, parent, false)
                // Возврат нового экземпляра холдера
                return ListViewHolder(binding)
            }
        }
    }

    var recordings = listOf<RecordingEntity>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    // Метод создания экземпляров
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder.from(parent)
    }

    // Предотсавляет информацию о том. каким образом элементы должны быть отрисованы
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        // получить экземпляр модели RecordingEntity из списка data по позиции
        val currentRecording = recordings[position]
        // полученный экземпляр записываем в textViews
        holder.bind(currentRecording)
        // Redirect on detail fragment
        holder.itemView.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToDetailFragment(recordings[position])
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return recordings.size
    }
}
