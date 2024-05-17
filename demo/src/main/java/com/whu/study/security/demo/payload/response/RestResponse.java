package com.whu.study.security.demo.payload.response;

import java.util.HashMap;
import java.util.Map;

/**
 * The class defines the dynamic REST response.
 */
public class RestResponse extends HashMap<String, Object> {

    /**
     * The Constructor.
     */
    public RestResponse() {
    }

    /**
     * Return error response with message.
     * @param msg the given message
     * @return RestResponse
     */
    public static RestResponse error(final String msg) {
        RestResponse r = new RestResponse();
        r.put("errorMessage", msg);
        return r;
    }

    /**
     * Return success response with message.
     * @param msg the given success message
     * @return RestResponse
     */
    public static RestResponse success(final String msg) {
        RestResponse r = new RestResponse();
        r.put("message", msg);
        return r;
    }

    /**
     * Return success response based on dynamic info map.
     * @param map the dynamic info.
     * @return RestResponse
     */
    public static RestResponse success(final Map<String, Object> map) {
        RestResponse r = new RestResponse();
        r.putAll(map);
        return r;
    }

    /**
     * Return success response without message.
     * @return RestResponse
     */
    public static RestResponse success() {
        return new RestResponse();
    }

    /**
     * Put the dynamic variable into the dynamic info map.
     * @param key the given key of info
     * @param value the given value of info
     * @return RestResponse
     */
    @Override
    public RestResponse put(final String key, final Object value) {
        super.put(key, value);
        return this;
    }
}
