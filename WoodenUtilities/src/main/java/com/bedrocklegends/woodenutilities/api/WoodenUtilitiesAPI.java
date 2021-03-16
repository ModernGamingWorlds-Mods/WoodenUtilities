package com.bedrocklegends.woodenutilities.api;

import com.bedrocklegends.woodenutilities.resin.ResinProvider;
import com.bedrocklegends.woodenutilities.resin.ResinProviderManager;
import com.google.common.collect.ImmutableList;

public class WoodenUtilitiesAPI {

    public static final String MOD_ID = "woodenutilities";
    public static final ImmutableList<ResinProvider> RESIN_PROVIDERS = ResinProviderManager.getProviders();
}
