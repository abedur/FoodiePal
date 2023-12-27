package com.miu.foodiepal_culinarycompanion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.miu.foodiepal_culinarycompanion.databinding.ActivityTabBinding

class TabActivity : AppCompatActivity() {
    lateinit var tabbinding : ActivityTabBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tabbinding = ActivityTabBinding.inflate(layoutInflater)
        setContentView(tabbinding.root)

        val adapter = MyAdapter(supportFragmentManager, lifecycle)
        tabbinding.pager.adapter = adapter

        tabbinding.tabLayout.tabGravity = TabLayout.GRAVITY_FILL


        TabLayoutMediator(tabbinding.tabLayout, tabbinding.pager){tab, position->
            when(position){
                0->{
                    tab.text = "Recipes"
                    tab.setIcon(R.drawable.baseline_chrome_reader_mode_24)
                }
                1->{
                    tab.text = "Meal Planner"
                    tab.setIcon(R.drawable.baseline_fastfood_24)
                }
                2->{
                    tab.text = "Blog"
                    tab.setIcon(R.drawable.baseline_notes_24)
                }
                3->{
                    tab.text = "Contact"
                    tab.setIcon(R.drawable.baseline_contact_mail_24)
                }
                4->{
                    tab.text = "About"
                    tab.setIcon(R.drawable.baseline_info_24)
                }
            }
        }.attach()



        tabbinding.btmNav.setOnItemSelectedListener  {item->
            when(item.itemId){
                R.id.btniRecipes -> tabbinding.pager.setCurrentItem(0, true)
                R.id.btniMeal -> tabbinding.pager.setCurrentItem(1, true)
                R.id.btniBlog -> tabbinding.pager.setCurrentItem(2, true)
            }
            true
        }
    }
    /*
    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.tab_layout, fragment)
        fragmentTransaction.commit()
    }*/
}