package com.tangmo.emall.utils;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;

import java.util.HashMap;
import java.util.Map;

public class PaypalUtil {

    private static String clientId = "ARVFJRIxsTRU7MzOvd35fDcmHXnOGK-Pm_tV8NZnrefGY0ihiVzBoKEGjQXYdfdxqIgXVMkxOZvSwPeh";

    private static String clientSecret = "EGgRgziKx8nNxKGnNNHVO5nbipA6jKm5Cu-eVdmpBrMnqUiKiBAp5c3UqZBOE-eReSUMReVlEtTWCHpV";

    private static String mode = "live";

    public static Map<String, String> paypalSdkConfig(){
        Map<String, String> sdkConfig = new HashMap<>();
        sdkConfig.put("mode", mode);
        return sdkConfig;
    }

    public static OAuthTokenCredential authTokenCredential(){
        return new OAuthTokenCredential(clientId, clientSecret, paypalSdkConfig());
    }

    public static APIContext apiContext(){
        APIContext apiContext = null;
        try {
            apiContext = new APIContext(authTokenCredential().getAccessToken());
            apiContext.setConfigurationMap(paypalSdkConfig());
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return apiContext;
    }
}
