package com.fast.system;

import java.util.Map;

import org.springframework.util.AntPathMatcher;

/**
 * 忽略url大小写
 * @author J
 *
 */
public class CaseInsensitiveAntPathMatcher extends AntPathMatcher {
    @Override
    protected boolean doMatch(String pattern, String path, boolean fullMatch, Map<String, String> uriTemplateVariables) {
        Boolean b = super.doMatch(pattern.toLowerCase(), path.toLowerCase(), fullMatch, uriTemplateVariables);
        return b;
    }
}