package fr.mcgalanes.groomr.feature.createuserstory.domain.usecase

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.Step
import kotlin.test.Test
import kotlin.test.assertContentEquals

class GetStepsUseCaseTest {

    @Test
    fun `should return all steps`() {
        //WHEN
        val actual = GetStepsUseCase()()

        //THEN
        val expected = arrayOf(
            Step.Need,
            Step.Kpi,
            Step.Value,
            Step.Solution,
            Step.Enablers,
            Step.Assets,
            Step.UAT,
        )

        assertContentEquals(
            expected,
            actual
        )
    }
}