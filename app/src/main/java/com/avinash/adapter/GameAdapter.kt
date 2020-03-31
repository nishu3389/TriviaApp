package com.avinash.adapter


import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.avinash.R
import com.avinash.db.entity.Game
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class GameAdapter : RecyclerView.Adapter<GameAdapter.NoteHolder>() {
    private var games: List<Game> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.game_item, parent, false)
        return NoteHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val game = games[position]
        holder.tv_game_date.text = "Game "+(position+1)+" : "+SimpleDateFormat("dd MMM yyyy - hh:mm:ss").format(Date(game.createdAt))
        holder.tv_name.text = "Name : "+game.name
        holder.tv_answer_one.text = "Answer : "+game.answerOne
        holder.tv_answer_two.text = "Answer : "+game.answerTwo
    }

    override fun getItemCount(): Int {
        return games.size
    }

    fun setGames(games: List<Game>) {
        this.games = games
        notifyDataSetChanged()
    }

    inner class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_game_date: TextView = itemView.findViewById(R.id.tv_game_date)
        var tv_name: TextView = itemView.findViewById(R.id.tv_name)
        var tv_answer_one: TextView = itemView.findViewById(R.id.tv_answer_one)
        var tv_answer_two: TextView = itemView.findViewById(R.id.tv_answer_two)

    }
}