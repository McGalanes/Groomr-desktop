package fr.mcgalanes.groomr.feature.createuserstory.domain.usecase

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.StepForm
import fr.mcgalanes.groomr.feature.createuserstory.domain.model.UserStory
import fr.mcgalanes.groomr.feature.createuserstory.domain.repository.UserStoryRepository
import kotlin.test.assertEquals
import org.junit.Test


class SaveStepFormUseCaseTest {

    @Test
    fun `should save need form`() {
        //GIVEN
        val stepForm = StepForm.Need(
            persona = "Sonny",
            wish = "release more tracks",
            goal = "make my fans happy",
        )

        val userStoryRepository = mockUseStoryRepository()

        val saveStepForm = SaveStepFormUseCase(userStoryRepository)

        //WHEN
        saveStepForm(stepForm)

        //THEN
        val userStory = userStoryRepository.getUserStory()
        assertEquals(stepForm.persona, userStory.persona)
        assertEquals(stepForm.wish, userStory.wish)
        assertEquals(stepForm.goal, userStory.goal)
    }

    @Test
    fun `should save kpi form`() {
        //GIVEN
        val stepForm = StepForm.Kpi(kpi = "")

        val userStoryRepository = mockUseStoryRepository()

        val saveStepForm = SaveStepFormUseCase(userStoryRepository)

        //WHEN
        saveStepForm(stepForm)

        //THEN
        val userStory = userStoryRepository.getUserStory()
        assertEquals(stepForm.kpi, userStory.kpi)
    }

    @Test
    fun `should save business value form`() {
        //GIVEN
        val stepForm = StepForm.Value(businessValue = 50)

        val userStoryRepository = mockUseStoryRepository()

        val saveStepForm = SaveStepFormUseCase(userStoryRepository)

        //WHEN
        saveStepForm(stepForm)

        //THEN
        val userStory = userStoryRepository.getUserStory()
        assertEquals(stepForm.businessValue, userStory.businessValue)
    }

    private fun mockUseStoryRepository() = object : UserStoryRepository {
        private var userStory = fakeUserStory()

        override fun getUserStory(): UserStory = userStory

        override fun saveUserStory(userStory: UserStory) {
            this.userStory = userStory
        }
    }

    private fun fakeUserStory(
        persona: String = "",
        wish: String = "",
        goal: String = "",
        kpi: String = "",
        businessValue: Int = 0,
        solution: String = "",
        enablers: List<String> = emptyList(),
        assets: List<String> = emptyList(),
        uats: List<UserStory.Uat> = emptyList(),
        effectivity: UserStory.Effectivity = UserStory.Effectivity(
            isSmallEnough = false,
            isEstimable = false,
            isIndependent = false,
            isTestable = false,
        ),
    ) =
        UserStory(
            persona = persona,
            wish = wish,
            goal = goal,
            kpi = kpi,
            businessValue = businessValue,
            solution = solution,
            enablers = enablers,
            assets = assets,
            uats = uats,
            effectivity = effectivity,
        )
}