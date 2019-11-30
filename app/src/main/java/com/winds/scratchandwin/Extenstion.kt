package com.winds.scratchandwin

import com.winds.scratchandwin.model.ModelScratch


fun getData(): List<ModelScratch> {
    val list = ArrayList<ModelScratch>()
    list.add(ModelScratch("", "", R.drawable.scratch, R.drawable.ic_spin))
    list.add(ModelScratch("", "", R.drawable.ic_scratch_card_big_2, R.drawable.ic_spin))
    list.add(ModelScratch("", "", R.drawable.ic_reward_bg, R.drawable.ic_spin))
    list.add(ModelScratch("", "", R.drawable.ic_cash_small_bg, R.drawable.ic_spin))
    list.add(ModelScratch("", "", R.drawable.ic_cap_small, R.drawable.ic_spin))
    list.add(ModelScratch("", "", R.drawable.scratch, R.drawable.ic_spin))
    list.add(ModelScratch("", "", R.drawable.ic_scratch_card_big_1, R.drawable.ic_spin))
    return list
}