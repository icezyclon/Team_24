package at.tugraz.ist.guessingwords

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.room.Room
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import at.tugraz.ist.guessingwords.data.database.GWDatabase
import at.tugraz.ist.guessingwords.data.service.WordService
import at.tugraz.ist.guessingwords.ui.custom_words.CustomWordsFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@RunWith(AndroidJUnit4::class)
class CustomWordsActivityTest {

    private fun getContext() = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setup() {
        GWDatabase._instance = Room.inMemoryDatabaseBuilder(
            getContext(),
            GWDatabase::class.java
        ).build()
    }

    @Test
    fun addNewCustomWords() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        val input = "Testing Custom Words!"

        onView(withId(R.id.btn_customWords)).check(matches(isClickable()))
        onView(withId(R.id.btn_customWords)).perform(click())
        onView(withId(R.id.editText_customWords)).perform(typeText(input))
        onView(withId(R.id.btn_save_word)).check(matches(isClickable()))
        onView(withId(R.id.btn_save_word)).perform(click())

        onView(withId(R.id.li_customWord_text)).check(matches(withText(input)))
    }


    @Test
    fun customWordSaveFragmentTest() {
        val wordServiceMock = mock<WordService>()

        val fragmentArgs = bundleOf()
        val scenario = launchFragmentInContainer<CustomWordsFragment>(fragmentArgs)
        scenario.onFragment { fragment -> fragment.customWordService = wordServiceMock }

        val input = "Testing Custom Words!"

        onView(withId(R.id.editText_customWords)).perform(typeText(input))
        onView(withId(R.id.btn_save_word)).check(matches(isClickable()))
        onView(withId(R.id.btn_save_word)).perform(click())

        verify(wordServiceMock, times(1)).insertOrUpdateExistingWord(any(), any())
    }

    @Test
    fun checkIfEditOrDeleteButtonIsDisplayedAfterLongClick() {
        val activityScenario = ActivityScenario.launch(CustomWordsActivity::class.java)
        val input = "Testing Custom Words!"

        onView(withId(R.id.editText_customWords)).perform(typeText(input))
        onView(withId(R.id.btn_save_word)).perform(click())

        onView(withId(R.id.li_customWord_text)).check(matches(withText(input)))
        onView(withId(R.id.li_customWord_text)).perform(ViewActions.longClick())
        onView(withId(R.id.btn_edit_CW)).check(matches(isClickable()))
        onView(withId(R.id.btn_delete_CW)).check(matches(isClickable()))
    }

    @Test
    fun checkIfWordsIsNotDisplayedAnymoreAfterClickingTheDeleteButton () {
        val activityScenario = ActivityScenario.launch(CustomWordsActivity::class.java)
        val input = "Testing Custom Words!"

        onView(withId(R.id.editText_customWords)).perform(typeText(input))
        onView(withId(R.id.btn_save_word)).perform(click())

        onView(withId(R.id.li_customWord_text)).check(matches(withText(input)))
        onView(withId(R.id.li_customWord_text)).perform(ViewActions.longClick())

        onView(withId(R.id.btn_delete_CW)).check(matches(isClickable()))
        onView(withId(R.id.btn_delete_CW)).perform(click())

        onView(withId(R.id.lst_custom_words)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test
    fun checkIfEditWordActuallyChangedTheTextOfTheWord () {
        val activityScenario = ActivityScenario.launch(CustomWordsActivity::class.java)
        val input = "Testing Custom Words!"
        val updatedInput = "Updating Custom Words!"

        onView(withId(R.id.editText_customWords)).perform(typeText(input))
        onView(withId(R.id.btn_save_word)).perform(click())

        onView(withId(R.id.li_customWord_text)).check(matches(withText(input)))
        onView(withId(R.id.li_customWord_text)).perform(longClick())

        onView(withId(R.id.btn_edit_CW)).check(matches(isClickable()))
        onView(withId(R.id.btn_edit_CW)).perform(click())

        onView(withId(R.id.editText_customWords)).check(matches(withText(input)))
        onView(withId(R.id.editText_customWords)).perform(clearText()).perform(typeText(updatedInput))

        onView(withId(R.id.btn_save_word)).check(matches(isClickable()))
        onView(withId(R.id.btn_save_word)).perform(click())

        onView(withId(R.id.li_customWord_text)).check(matches(withText(updatedInput)))
    }

    @Test
    fun checkIfCancelButtonAfterEditCustomWordButtonIsDisplayed() {
        val activityScenario = ActivityScenario.launch(CustomWordsActivity::class.java)
        val input = "Testing Custom Words!"

        onView(withId(R.id.editText_customWords)).perform(typeText(input))
        onView(withId(R.id.btn_save_word)).perform(click())

        onView(withId(R.id.li_customWord_text)).check(matches(withText(input)))
        onView(withId(R.id.li_customWord_text)).perform(longClick())

        onView(withId(R.id.btn_edit_CW)).check(matches(isClickable()))
        onView(withId(R.id.btn_edit_CW)).perform(click())

        onView(withId(R.id.btn_cancel_word)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.btn_cancel_word)).check(matches(isClickable()))
        onView(withId(R.id.btn_cancel_word)).perform(click())

        onView(withId(R.id.btn_cancel_word)).check(matches(withEffectiveVisibility(Visibility.GONE)))

        onView(withId(R.id.li_customWord_text)).check(matches(withText(input)))
    }
}