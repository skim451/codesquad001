package com.woowahan.codesquad001.util;

import com.github.jknack.handlebars.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@pl.allegro.tech.boot.autoconfigure.handlebars.HandlebarsHelper
public class HandlebarsHelper {
    
    private static final Logger log = LoggerFactory.getLogger(HandlebarsHelper.class);
    
    public static CharSequence equals(final Object str1, final Options options) throws IOException {
        Object str2 = options.param(0, "arg0");

        log.debug("{}, {}", str1, str2);

        if (str1 == null || str2 == null)
            return options.inverse();

        if (str1.equals(str2))
            return options.fn();

        return options.inverse();
    }
}
