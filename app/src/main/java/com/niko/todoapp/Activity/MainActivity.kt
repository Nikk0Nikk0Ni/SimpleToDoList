package com.niko.todoapp.Activity

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.niko.todoapp.Adapter.ShopListAdapter
import com.niko.todoapp.Fragments.ShopItemFragment
import com.niko.todoapp.R
import com.niko.todoapp.ViewModels.MainViewModel
import com.niko.todoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var viewModel = MainViewModel()
    private val adapter = ShopListAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecView()
        initButtonAdd()
        viewModel = ViewModelProvider(this)[viewModel::class.java]
        viewModel.shopList.observe(this) {
            adapter.submitList(it)
        }
    }

    private fun isOnePainMode(): Boolean {
        val orientation = resources.configuration.orientation
        return orientation == Configuration.ORIENTATION_PORTRAIT
    }

    private fun launchFragmnet(fragment: Fragment) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction().replace(R.id.ItemContainer, fragment).addToBackStack(null).commit()
    }

    private fun initButtonAdd() {
            binding.apply {
                addbutton.setOnClickListener {
                    if (isOnePainMode()) {
                        val intent = ShopItemActivity.newIntentAdd(this@MainActivity)
                        startActivity(intent)
                    }else {
                        val fragment = ShopItemFragment.newInstanceAddItem()
                        initOnEdditingFinishListenner(fragment)
                        launchFragmnet(fragment)
                    }
                }
            }
    }

    private fun initRecView() {
        binding.recView.adapter = adapter
        binding.recView.recycledViewPool.setMaxRecycledViews(R.layout.disabled_note_item, 10)
        binding.recView.recycledViewPool.setMaxRecycledViews(R.layout.enabled_note_item, 10)
        setUpLonCLickListenner()
        setUpClickKistenner()
        setUpSwipeListenner()

    }

    private fun setUpLonCLickListenner() {
        adapter.longTap = {
            viewModel.editItem(it)
        }
    }

    private fun setUpClickKistenner() {
        adapter.clickTap = {
            if (isOnePainMode()) {
                val intent = ShopItemActivity.newIntentEdit(this, it.id)
                startActivity(intent)
            } else {
                val fragment = ShopItemFragment.newInstanceEditItem(it.id)
                initOnEdditingFinishListenner(fragment)
                launchFragmnet(fragment)
            }
        }


    }

    fun initOnEdditingFinishListenner(fragment: ShopItemFragment){
        fragment.onEditingFinishedListener = object : ShopItemFragment.OnEditingFinishedListenner{
            override fun onEditingFinished() {
                supportFragmentManager.popBackStack()
            }
        }
    }

    private fun setUpSwipeListenner() {
        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapter.currentList[viewHolder.adapterPosition]
                viewModel.removeItem(item)
            }
        }).attachToRecyclerView(binding.recView)
    }
}