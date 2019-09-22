package com.example.android.trackmysleepquality.sleeptracker

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.databinding.ListItemSleepNightBinding

class SleepNightDiffCallback : DiffUtil.ItemCallback<SleepNight>() {
    override fun areItemsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        return oldItem.nightId == newItem.nightId
    }

    override fun areContentsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        return oldItem == newItem //since SleepNight is a data class, equal mean the data are identical
    }
}
class SleepNightAdapter(val clickListener: SleepNightListener) : ListAdapter<SleepNight, SleepNightAdapter.ViewHolder>(SleepNightDiffCallback()){

    //No longer need data field, or the getItemCount() func, ListAdapter alr does this for us

    //this func is called when the RecyclerView needs a view holder to represent an item.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    //this function is called by RecyclerView to display the data for one list item at the specified position
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(getItem(position)!!, clickListener)
    }




    class ViewHolder private constructor(val binding: ListItemSleepNightBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: SleepNight, clickListener: SleepNightListener) {
            // data binding and the data binding adapters alr did this for us
            binding.sleep = item

            binding.clickListener = clickListener

            //an optimization that asks data binding to execute any pending bindings right away.
            //good idea to call this when you use binding adapters in a RecyclerView, because it can slightly speed up sizing the views.
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context) // always pass in the context of the parent view group, which is the RecyclerView

                val binding =
                        ListItemSleepNightBinding.inflate(layoutInflater, parent, false)  //The third, boolean, argument is attachToRoot.
                // This argument needs to be false,
                // because RecyclerView adds this item to the view hierarchy for you when it's time.
                return ViewHolder(binding)
            }
        }
    }


}

class SleepNightListener(val clickListener: (sleepID: Long) -> Unit) {
    //constructor of this function takes in a lambda, returning void

    fun onClick(night: SleepNight) = clickListener(night.nightId)
}

