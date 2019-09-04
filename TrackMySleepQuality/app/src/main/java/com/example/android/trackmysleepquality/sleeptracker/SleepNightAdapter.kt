package com.example.android.trackmysleepquality.sleeptracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.convertDurationToFormatted
import com.example.android.trackmysleepquality.convertNumericQualityToString
import com.example.android.trackmysleepquality.database.SleepNight

class SleepNightAdapter : RecyclerView.Adapter<SleepNightAdapter.ViewHolder> (){

    var data = listOf<SleepNight>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = data.size

    //this func is called when the RecyclerView needs a view holder to represent an item.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    //this function is called by RecyclerView to display the data for one list item at the specified position
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }




    class ViewHolder private constructor(itemView : View) : RecyclerView.ViewHolder(itemView){
        val sleepLength: TextView = itemView.findViewById(R.id.sleep_length)
        val quality: TextView = itemView.findViewById(R.id.quality_string)
        val qualityImage: ImageView = itemView.findViewById(R.id.quality_image)

        fun bind(item: SleepNight) {
            val res = itemView.context.resources

            sleepLength.text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, res)

            quality.text = convertNumericQualityToString(item.sleepQuality, res)

            qualityImage.setImageResource(
                    when (item.sleepQuality) {
                        0 -> R.drawable.ic_sleep_0
                        1 -> R.drawable.ic_sleep_1
                        2 -> R.drawable.ic_sleep_2
                        3 -> R.drawable.ic_sleep_3
                        4 -> R.drawable.ic_sleep_4
                        5 -> R.drawable.ic_sleep_5
                        else -> R.drawable.ic_sleep_active
                    }
            )
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context) // always pass in the context of the parent view group, which is the RecyclerView

                val view = layoutInflater
                        .inflate(R.layout.list_item_sleep_night, parent, false)  //The third, boolean, argument is attachToRoot.
                // This argument needs to be false,
                // because RecyclerView adds this item to the view hierarchy for you when it's time.
                return ViewHolder(view)
            }
        }
    }


}

