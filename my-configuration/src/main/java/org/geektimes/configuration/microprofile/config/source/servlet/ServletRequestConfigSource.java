package org.geektimes.configuration.microprofile.config.source.servlet;

import org.geektimes.configuration.microprofile.config.servlet.listener.ConfigServletRequestListener;
import org.geektimes.configuration.microprofile.config.source.MapBasedConfigSource;

import javax.servlet.ServletRequest;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

import static java.lang.String.format;

/**
 * servlet请求源
 *
 * @author star
 * @date 2021/07/15
 */
public class ServletRequestConfigSource extends MapBasedConfigSource {
    protected ServletRequest request;

    protected ServletRequestConfigSource(ServletRequest request) {
        super(format("Servlet[name:%s] Init Parameters", request.getParameterMap()), 600);
        this.request = request;
    }

    @Override
    protected void prepareConfigData(Map configData) throws Throwable {
        Map<String, String[]> map = request.getParameterMap();
        Set<String> set = map.keySet();
        while (set.iterator() != null) {
            configData.put(set.iterator(), map.get(set.iterator()));
        }
    }
}
