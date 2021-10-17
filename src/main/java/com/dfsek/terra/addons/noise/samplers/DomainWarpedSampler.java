package com.dfsek.terra.addons.noise.samplers;

import com.dfsek.terra.api.noise.NoiseSampler;


public class DomainWarpedSampler implements NoiseSampler {
    private final NoiseSampler function;
    private final NoiseSampler warp;
    private final double amplitude;
    
    public DomainWarpedSampler(NoiseSampler function, NoiseSampler warp, double amplitude) {
        this.function = function;
        this.warp = warp;
        this.amplitude = amplitude;
    }
    
    @Override
    public double getNoiseSeeded(long seed, double x, double y) {
        return function.getNoiseSeeded(seed++,
                                       x + warp.getNoiseSeeded(seed++, x, y) * amplitude,
                                       y + warp.getNoiseSeeded(seed, x, y) * amplitude
                                      );
    }
    
    @Override
    public double getNoiseSeeded(long seed, double x, double y, double z) {
        return function.getNoiseSeeded(seed++,
                                       x + warp.getNoiseSeeded(seed++, x, y, z) * amplitude,
                                       y + warp.getNoiseSeeded(seed++, x, y, z) * amplitude,
                                       z + warp.getNoiseSeeded(seed, x, y, z) * amplitude
                                      );
    }
}