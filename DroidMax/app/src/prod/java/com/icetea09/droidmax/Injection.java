package com.icetea09.droidmax;


import com.icetea09.droidmax.api.Api;
import com.icetea09.droidmax.api.IApi;

/**
 * Enables injection of production implementations.
 */
public class Injection {

    public static IApi getApi() {
        return Api.getInstance();
    }

}
