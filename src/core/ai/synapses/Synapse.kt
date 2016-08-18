package core.ai.synapses

import core.ai.AIContext
import core.ai.DecisionProvider

/**
 * Created on 13.04.2016.
 */
class Synapse: DecisionProvider
{
    var basePriority:Int = 0;
    var id:Int = 0;
    var name:String = "";
    var actualPriority:Int = 0;

    override fun calculatePriority(context: AIContext):Int
    {
        return 0;
    }

    override fun makeDecision(context: AIContext): Boolean {
        throw UnsupportedOperationException()
    }
}