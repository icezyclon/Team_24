package at.tugraz.ist.guessingwords

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import org.junit.Test

class MultiplayerActivityTest {


    @Test
    fun addMultiplayerText() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        val input = "Testing multiplayer txt box"

        Espresso.onView(ViewMatchers.withId(R.id.btn_multiplayer)).check(ViewAssertions.matches(ViewMatchers.isClickable()))
        Espresso.onView(ViewMatchers.withId(R.id.btn_multiplayer)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.editText_multiplayer)).perform(ViewActions.typeText(input))
    }

    @Test
    fun hostButtonTest(){
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        Espresso.onView(ViewMatchers.withId(R.id.btn_multiplayer)).check(ViewAssertions.matches(ViewMatchers.isClickable()))
        Espresso.onView(ViewMatchers.withId(R.id.btn_multiplayer)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btn_host)).check(ViewAssertions.matches(ViewMatchers.isClickable()))
    }

    @Test
    fun joinButtonTest(){
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        Espresso.onView(ViewMatchers.withId(R.id.btn_multiplayer)).check(ViewAssertions.matches(ViewMatchers.isClickable()))
        Espresso.onView(ViewMatchers.withId(R.id.btn_multiplayer)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btn_join)).check(ViewAssertions.matches(ViewMatchers.isClickable()))
    }

    @Test
    fun multiplayerBackButton(){
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        Espresso.onView(ViewMatchers.withId(R.id.btn_multiplayer)).check(ViewAssertions.matches(ViewMatchers.isClickable()))
        Espresso.onView(ViewMatchers.withId(R.id.btn_multiplayer)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withContentDescription(R.string.abc_action_bar_up_description)).perform(ViewActions.click())
    }

}