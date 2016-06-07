package cordova-plugin-androidpay;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class AndroidPay extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("checkAndroidPay")) {
            this.checkAndroidPay(callbackContext);
            return true;
        }
        return false;
    }

    private void checkAndroidPay(CallbackContext callbackContext) {
        showProgressDialog();
        Wallet.Payments.isReadyToPay(mGoogleApiClient).setResultCallback(
            new ResultCallback<BooleanResult>() {
                @Override
                public void onResult(@NonNull BooleanResult booleanResult) {
                    hideProgressDialog();

                    if (booleanResult.getStatus().isSuccess()) {
                        if (booleanResult.getValue()) {
                            // Show Android Pay buttons and hide regular checkout button
                            // ...
                            callbackContext.success("Android Pay is ready");
                        } else {
                            // Hide Android Pay buttons, show a message that Android Pay
                            // cannot be used yet, and display a traditional checkout button
                            // ...
                            callbackContext.error("Android Pay cannot be used yet");
                        }
                    } else {
                        // Error making isReadyToPay call
                        Log.e(TAG, "isReadyToPay:" + booleanResult.getStatus());
                        callbackContext.error("isReadyToPay:" + booleanResult.getStatus());
                    }
                }
            }
        );
    }
}
