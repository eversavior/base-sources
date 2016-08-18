package core.ai

/**
 * Created on 13.04.2016.
 */
interface DecisionProvider
{
    fun calculatePriority(context:AIContext):Int

    fun makeDecision(context:AIContext):Boolean
}