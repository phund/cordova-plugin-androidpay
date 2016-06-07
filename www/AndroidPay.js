var exec = require('cordova/exec');

exports.checkAndroidPay = function(arg0, success, error) {
    exec(success, error, "AndroidPay", "checkAndroidPay", [arg0]);
};
