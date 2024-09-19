package org.learning.lld.models;

import lombok.Getter;

public class Size {
    @Getter
    private final int height;
    @Getter
    private final int width;

    public Size(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public boolean fitsIn(Size sizeToFit) {
        return sizeToFit.width <= this.width && sizeToFit.height <= this.height;
    }
}
