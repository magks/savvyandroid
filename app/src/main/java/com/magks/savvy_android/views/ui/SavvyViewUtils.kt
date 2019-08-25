package com.magks.savvy_android.views.ui

import android.app.Activity
import android.content.Context
import android.text.TextUtils.replace
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.ImageViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.magks.savvy_android.R
import com.magks.savvy_android.views.ui.game.GameDashboardFragment
import java.lang.reflect.Constructor
import kotlin.reflect.KFunction
import kotlin.reflect.jvm.javaConstructor

fun <T : View> View.unsafe_bind(@IdRes idRes: Int): Lazy<T> {
    @Suppress("UNCHECKED_CAST")
    return SavvyViewUtils.unsafeLazy { findViewById(idRes) as T }
}

fun <T : View> Activity.unsafe_bind(@IdRes idRes: Int): Lazy<T> {
    @Suppress("UNCHECKED_CAST")
    return SavvyViewUtils.unsafeLazy { findViewById(idRes) as T }
}

fun <T : View> View.bind(@IdRes idRes: Int): Lazy<T> {
    @Suppress("UNCHECKED_CAST")
    Log.e("SafeViewBinding", idRes.toString())
    return SavvyViewUtils.safeLazy { findViewById(idRes) as T }
}

fun <T : View> View.bind(@IdRes idRes: Int, activity: AppCompatActivity): Lazy<T> {
    @Suppress("UNCHECKED_CAST")
    Log.e("SafeViewBindingGivenActivity", idRes.toString())
    return SavvyViewUtils.safeLazy { activity.findViewById(idRes) as T }
}

fun <T : View> Activity.bind(@IdRes idRes: Int): Lazy<T> {
    @Suppress("UNCHECKED_CAST")
    return SavvyViewUtils.safeLazy { findViewById(idRes) as T }
}

class SavvyViewUtils(context: Context) : View(context) {

    companion object {
        fun presentFragment(fragmentManager: FragmentManager, fragmentTag: String, fragment: Fragment, addBackStack : Boolean) {
            var _addBackStack = addBackStack
            if (fragmentManager.backStackEntryCount == 0)
                _addBackStack = false
            //val backStackEntryAt = fragmentManager.getBackStackEntryAt(0)
            //if (backStackEntryAt.name.equals(fragmentTag))


            fragmentManager.popBackStack(fragmentTag, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            //if (fragmentManager.getBackStackEntryAt(0))


            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.apply {
                replace(R.id.fragment_container, GameDashboardFragment(), fragmentTag)
                if (_addBackStack)
                    addToBackStack(fragmentTag)
            }.commit()
        }

        // likely unneeded and for now unused
        fun presentFragment(fragmentManager: FragmentManager, fragmentTag: String, constructor: Constructor<Fragment>) : Fragment? {
            // Pop off fragments until the profile fragment and then remove that too (too avoid multiple)
            fragmentManager.popBackStack(fragmentTag, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            val fragmentTransaction = fragmentManager.beginTransaction()
            var fragment: Fragment? = fragmentManager.findFragmentByTag(fragmentTag) //?: constructor.newInstance()
            fragment?. let {
                return fragment
            }
                ?: let {
                    fragment = constructor.newInstance()
                    fragmentTransaction.apply {
                        replace(R.id.fragment_container, fragment!!, fragmentTag)
                    }.commit()
                }
            return fragment
        }
        fun <T> unsafeLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)
        fun <T> safeLazy(initializer: () -> T) = lazy(initializer)
    }


}