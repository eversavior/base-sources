package core.ai.synapses

class SynapseGroup(synapse: Synapse)
{
    var baseSynapse: Synapse;
    var subSynapses:MutableList<Synapse>? = arrayListOf();

    init
    {
        this.baseSynapse = synapse;
    }
}