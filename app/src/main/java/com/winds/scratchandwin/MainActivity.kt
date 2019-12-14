package com.winds.scratchandwin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.winds.scratchandwin.model.ModelScratch
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.adapter_scratch_item.view.*

class MainActivity : AppCompatActivity() {

    private val adapter = MainAdapter(listOf())
    private lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mContext = this


        recyclerView.adapter = adapter
        // loadData(true)
        userData(getData())
    }


    private fun loadData(isLoader: Boolean) {
        setProgressVisible(isLoader)
        // instanceViewModel.getUserApiCall()
    }


    private fun userData(it: List<ModelScratch>?) {
        adapter.items = it!!
        adapter.notifyDataSetChanged()
    }

    private fun setProgressVisible(isShown: Boolean) {
        //swipeRefresh.isRefreshing = false
        // progress.visible = isShown
    }


    private var View.visible
        get() = this.visibility == View.VISIBLE
        set(isVisible) = if (isVisible) this.visibility = View.VISIBLE else this.visibility =
            View.GONE


    inner class MainAdapter(var items: List<ModelScratch>) :
        RecyclerView.Adapter<MainAdapter.PostHolder>() {
        override fun getItemCount() = items.size
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PostHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.adapter_scratch_item,
                parent,
                false
            )
        )

        override fun onBindViewHolder(holder: PostHolder, position: Int) =
            holder.bind(items[position])

        inner class PostHolder(view: View) : RecyclerView.ViewHolder(view) {
            fun bind(item: ModelScratch) = with(this.itemView) {
                ivScratch.setImageResource(item.coverURL)
                cardBtn.setOnClickListener {
                    val intent = Intent(context, WinScreenActivity::class.java)
                    intent.putExtra("title", item.mess)
                    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as AppCompatActivity, this.ivScratch, "photoToAnimate")
                    startActivity(intent, options.toBundle())

                }
                /* postId.text = item.firstName
                 postTitle.text = item.mobile
                 postBody.text = item.uid*/
            }
        }
    }
}
