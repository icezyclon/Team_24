package at.tugraz.ist.guessingwords

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Test
    fun startGameButton() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.btn_startGame)).check(matches(isClickable()))
        onView(withId(R.id.btn_startGame)).perform(click())
        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())
        onView(withId(R.id.btn_startGame)).check(matches(isClickable()))
    }

    @Test
    fun multiplayerButton() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.btn_multiplayer)).check(matches(isClickable()))
        onView(withId(R.id.btn_multiplayer)).perform(click())
        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())
        onView(withId(R.id.btn_multiplayer)).check(matches(isClickable()))
    }

    @Test
    fun customWordsButton() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.btn_customWords)).check(matches(isClickable()))
        onView(withId(R.id.btn_customWords)).perform(click())
        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())
        onView(withId(R.id.btn_customWords)).check(matches(isClickable()))
    }

    @Test
    fun aboutPageLink() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        Espresso.openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext())
        onView(withText("About")).perform(click())
    }

    @Test
    fun translateTest() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        Espresso.openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext())
        onView(withText("Language")).perform(click())
        onView(withId(R.id.lang_russian)).check(matches(isClickable()))
        onView(withId(R.id.lang_russian)).perform(click())
        onView(withId(R.id.btn_language_change)).perform(click())
        onView(withId(R.id.btn_customWords)).check(matches(withText("Пользовательские слова")))
    }
}