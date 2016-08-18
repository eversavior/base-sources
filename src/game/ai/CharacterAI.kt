package game.ai

import core.ai.AIContext
import core.ai.DecisionProvider

class CharacterAI: DecisionProvider
{
    override fun makeDecision(context: AIContext): Boolean {
        throw UnsupportedOperationException()
    }

    override fun calculatePriority(context: AIContext): Int {
        throw UnsupportedOperationException()
    }
}