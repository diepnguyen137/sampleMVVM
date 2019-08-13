package com.skg.mybook.view.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.RecyclerView
import com.skg.mybook.R
import com.skg.mybook.databinding.ListItemBinding

import java.util.*
import kotlin.collections.ArrayList

class KeyWordAdapter(context: Context) : RecyclerView.Adapter<KeyWordAdapter.KeyWordViewHolder>() {
    private var context:Context = context
    private var keywords: List<String> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeyWordViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return KeyWordViewHolder(binding.itemContainer, binding, context)
    }

    override fun getItemCount(): Int {
        return keywords?.size
    }

    fun setAdapter(keywords: List<String>){
        this.keywords = keywords
        notifyDataSetChanged()
    }
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onBindViewHolder(holder: KeyWordViewHolder, position: Int) = holder.bind(keywords[position])

    class KeyWordViewHolder(itemView: View, binding: ListItemBinding, context: Context) : RecyclerView.ViewHolder(itemView) {
        private var dataBinding: ListItemBinding? = null
        private var context:Context = context

        init {
            dataBinding = binding
        }

        @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
        fun bind(keyword: String) {
            dataBinding?.itemContainer?.background = setUpRandomColorBackGround()
            dataBinding?.keyword = formatStr(keyword)
            dataBinding?.executePendingBindings()
        }

        private fun setUpRandomColorBackGround() : GradientDrawable{
            val r = Random()
            val red = r.nextInt(255 - 0 + 1)+0
            val green = r.nextInt(255 - 0 + 1)+0
            val blue = r.nextInt(255 - 0 + 1)+0

            val drawable = getDrawable(context, R.drawable.item_shape)
            val gradientDrawable = drawable as GradientDrawable
            gradientDrawable.setColor(Color.rgb(red,green,blue))

            return gradientDrawable
        }

        private fun formatStr(str: String): String {
            var spaces = 0
            for (i in 0 until str.length) {
                val char = str[i]
                if (char == ' ') {
                    spaces++
                }
            }
            return when (spaces) {
                0 -> str
                1 -> str.replace(' ', '\n')
                else -> transformStr(str)
            }
        }

        private fun transformStr(str: String): String {
            val chars = str.toCharArray()
            val mid = str.length / 2

            if (chars[mid] == ' ') {
                chars[mid] = '\n'
            }
            else {
                for(i in mid + 2 downTo  0){
                    if(chars[i] == ' '){
                        chars[i] = '\n'
                        break
                    }
                }
            }
            return String(chars)
        }
    }
}


