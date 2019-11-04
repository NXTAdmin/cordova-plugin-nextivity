/* TelephonyManagerInfo Plugin - Egemen Mede - @delipenguen - 24.03.2016 */
package com.egemenmede.telephonymanagerinfo;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.provider.Settings;

import android.util.Log;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.telephony.CellIdentityLte;
import android.app.Activity;

public class TelephonyManagerInfo extends CordovaPlugin {

    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {


        if ("getInfo".equals(action)) {
            JSONObject r = new JSONObject();
            r.put("phone", this.getPhoneNumber());
            r.put("simSerialNumber", this.getSimSerialNumber());
            r.put("simOperatorName", this.getSimOperatorName());
            r.put("simOperator", this.getSimOperator());
            r.put("networkOperatorName", this.getNetworkOperatorName());
            r.put("networkOperator", this.getNetworkOperator());
            r.put("networkCountryIso", this.getNetworkCountryIso());
            r.put("deviceSoftwareVersion", this.getDeviceSoftwareVersion());
            r.put("deviceId", this.getDeviceId());
            r.put("phoneType", this.getPhoneType());
            r.put("isNetworkRoaming", this.isNetworkRoaming());
            r.put("simState", this.getSimState());
            r.put("networkType", this.getNetworkType());
            r.put("callState", this.getCallState());
            r.put("dataState", this.getDataState());
            r.put("groupIdLevel", this.getGroupIdLevel1());
            r.put("simCountryIso", this.getSimCountryIso());
            r.put("subscriberId", this.getSubscriberId());
            r.put("voiceMailAlphaTag", this.getVoiceMailAlphaTag());
            r.put("voiceMailNumber", this.getVoiceMailNumber());
            r.put("hasIccCard", this.hasIccCard());
            r.put("dataActivity", this.getDataActivity());
            r.put("cellInfo", getCellularInfo());
            
            
            callbackContext.success(r);
            return true;
        }else {
            return false;
        }
    }

    //--------------------------------------------------------------------------
    // LOCAL METHODS
    //--------------------------------------------------------------------------

    public String getPhoneNumber(){
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        String number = tm.getLine1Number();
        return number;
    }

    public String getSimSerialNumber(){
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        String number = tm.getSimSerialNumber();
        return number;
    }

    public String getSimOperatorName(){
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        String number = tm.getSimOperatorName();
        return number;
    }

    public String getSimOperator(){
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        String number = tm.getSimOperator();
        return number;
    }

    public String getNetworkOperatorName(){
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        String number = tm.getNetworkOperatorName();
        return number;
    }

    public String getNetworkOperator(){
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        String number = tm.getNetworkOperator();
        return number;
    }

    public String getNetworkCountryIso(){
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        String number = tm.getNetworkCountryIso();
        return number;
    }

    public String getDeviceSoftwareVersion(){
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        String number = tm.getDeviceSoftwareVersion();
        return number;
    }

    public String getDeviceId(){
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        String number = tm.getDeviceId();
        return number;
    }

    public String getPhoneType(){
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        int phoneType = tm.getPhoneType();
        String returnValue = new String();
        switch (phoneType) {
            case (TelephonyManager.PHONE_TYPE_CDMA):
                returnValue = new String("CDMA");
                break;
            case (TelephonyManager.PHONE_TYPE_GSM):
                returnValue = new String("GSM");
                break;
            case (TelephonyManager.PHONE_TYPE_NONE):
                returnValue = new String("NONE");
                break;
            case (TelephonyManager.PHONE_TYPE_SIP):
                returnValue = new String("SIP");
                break;
        }
        return returnValue;
    }

    public String isNetworkRoaming(){
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        boolean isRoaming = tm.isNetworkRoaming();
        String returnValue = new String();
        if (isRoaming){
            returnValue = new String("YES");
        }else{
            returnValue = new String("NO");
        }
        return returnValue;
    }

    public String getSimState(){
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        int simState = tm.getSimState();
        String returnValue = new String();
        switch (simState) {
            case (TelephonyManager.SIM_STATE_ABSENT):
                returnValue = new String("ABSENT");
                break;
            case (TelephonyManager.SIM_STATE_NETWORK_LOCKED):
                returnValue = new String("NETWORK_LOCKED");
                break;
            case (TelephonyManager.SIM_STATE_PIN_REQUIRED):
                returnValue = new String("PIN_REQUIRED");
                break;
            case (TelephonyManager.SIM_STATE_PUK_REQUIRED):
                returnValue = new String("PUK_REQUIRED");
                break;
            case (TelephonyManager.SIM_STATE_READY):
                returnValue = new String("READY");
                break;
            case (TelephonyManager.SIM_STATE_UNKNOWN):
                returnValue = new String("UNKNOWN");
                break;
        }
        return returnValue;
    }

    public String getNetworkType(){
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        int networkType = tm.getNetworkType();
        String returnValue = new String();
        switch (networkType) {
            case (TelephonyManager.NETWORK_TYPE_1xRTT):
                returnValue = new String("1xRTT");
                break;
            case (TelephonyManager.NETWORK_TYPE_CDMA):
                returnValue = new String("CDMA");
                break;
            case (TelephonyManager.NETWORK_TYPE_EDGE):
                returnValue = new String("EDGE");
                break;
            case (TelephonyManager.NETWORK_TYPE_EVDO_0):
                returnValue = new String("EVDO_0");
                break;
        }
        return returnValue;
    }

    public String getCallState(){
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        int callState = tm.getCallState();
        String returnValue = new String();
        switch (callState) {
            case (TelephonyManager.CALL_STATE_RINGING):
                returnValue = new String("RINGING");
                break;
            case (TelephonyManager.CALL_STATE_OFFHOOK):
                returnValue = new String("OFFHOOK");
                break;
            case (TelephonyManager.CALL_STATE_IDLE):
                returnValue = new String("IDLE");
                break;
        }
        return returnValue;
    }

    public String getDataState(){
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        int dataState = tm.getDataState();
        String returnValue = new String();
        switch (dataState) {
            case (TelephonyManager.DATA_DISCONNECTED):
                returnValue = new String("DISCONNECTED");
                break;
            case (TelephonyManager.DATA_CONNECTING):
                returnValue = new String("CONNECTING");
                break;
            case (TelephonyManager.DATA_CONNECTED):
                returnValue = new String("CONNECTED");
                break;
            case (TelephonyManager.DATA_SUSPENDED):
                returnValue = new String("SUSPENDED");
                break;
        }
        return returnValue;
    }

    public String getGroupIdLevel1(){
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        String number = tm.getGroupIdLevel1();
        return number;
    }

    public String getSimCountryIso(){
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        String number = tm.getSimCountryIso();
        return number;
    }

    public String getSubscriberId(){
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        String number = tm.getSubscriberId();
        return number;
    }

    public String getVoiceMailAlphaTag(){
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        String number = tm.getVoiceMailAlphaTag();
        return number;
    }

    public String getVoiceMailNumber(){
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        String number = tm.getVoiceMailNumber();
        return number;
    }

    public String hasIccCard(){
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        boolean hasIccCard = tm.hasIccCard();
        String returnValue = new String();
        if (hasIccCard){
            returnValue = new String("TRUE");
        }else{
            returnValue = new String("FALSE");
        }
        return returnValue;
    }

    public String getDataActivity(){
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        int dataActivity = tm.getDataActivity();
        String returnValue = new String();
        switch (dataActivity) {
            case (TelephonyManager.DATA_ACTIVITY_NONE):
                returnValue = new String("NONE");
                break;
            case (TelephonyManager.DATA_ACTIVITY_IN):
                returnValue = new String("IN");
                break;
            case (TelephonyManager.DATA_ACTIVITY_OUT):
                returnValue = new String("OUT");
                break;
            case (TelephonyManager.DATA_ACTIVITY_INOUT):
                returnValue = new String("INOUT");
                break;
            case (TelephonyManager.DATA_ACTIVITY_DORMANT):
                returnValue = new String("DORMANT");
                break;
        }
        return returnValue;
    }

    
    public String getLteEarfcn(){
          Activity activity = cordova.getActivity();
//        CellIdentityLte ci = (CellIdentityLte) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
//        CellIdentityLte ci = (CellIdentityLte) activity.getSystemService(Context.TELEPHONY_SERVICE);
        String earfcn = new String("JdoEarfcn"); //String.valueOf(ci.getEarfcn());
        return earfcn;
    }

    public String getCellularInfo()
    {
        String cellularInfo = "CI: ";
        String log = "";
        
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);

        if(tm.getAllCellInfo()==null) {
//            Log.v(TAG, "getAllCellInfo returned null");
        }
        else {
            for (final CellInfo info : telephonyManager.getAllCellInfo()) {
                if (info instanceof CellInfoGsm) {
                    log += "GSM@";
                    CellIdentityGsm gsm_cell = ((CellInfoGsm) info).getCellIdentity();
                    log += gsm_cell.getCid() + "#" + gsm_cell.getLac() + "#" + gsm_cell.getMcc() + "#" + gsm_cell.getMnc() + "_";

                    final CellSignalStrengthGsm gsm = ((CellInfoGsm) info).getCellSignalStrength();
                    log += gsm.getDbm() + "#" + gsm.getLevel()+"#"+gsm.getAsuLevel()+":";
                } else if (info instanceof CellInfoCdma) {
                    log += "CDMA@";
                    CellIdentityCdma cdma_cell = ((CellInfoCdma) info).getCellIdentity();
                    log += cdma_cell.getBasestationId() + "#" + cdma_cell.getNetworkId() + "#" + cdma_cell.getSystemId() + "#" + cdma_cell.getSystemId() + "_";

                    final CellSignalStrengthCdma cdma = ((CellInfoCdma) info).getCellSignalStrength();
                    log += cdma.getDbm() + "#" + cdma.getLevel()+"#"+cdma.getAsuLevel()+":";
                } else if (info instanceof CellInfoLte) {
                    log += "LTE@";
                    CellIdentityLte lte_cell = ((CellInfoLte) info).getCellIdentity();
                    log += lte_cell.getCi() + "#" + lte_cell.getPci() + "#" + lte_cell.getMcc() + "#" + lte_cell.getMnc() + "_";

                    final CellSignalStrengthLte lte = ((CellInfoLte) info).getCellSignalStrength();
                    log += lte.getDbm() + "#" + lte.getLevel()+"#"+lte.getAsuLevel()+":";
                } else if (info instanceof CellInfoWcdma) {
                    log += "WCDMA@";
                    CellIdentityWcdma wcdma_cell = ((CellInfoWcdma) info).getCellIdentity();
                    log += wcdma_cell.getCid() + "#" + wcdma_cell.getLac() + "#" + wcdma_cell.getMcc() + "#" + wcdma_cell.getMnc() + "_";

                    final CellSignalStrengthWcdma wcdma = ((CellInfoWcdma) info).getCellSignalStrength();
                    log += wcdma.getDbm() + "#" + wcdma.getLevel()+"#"+wcdma.getAsuLevel()+":";
                } else {
                    Log.v(TAG, "Unknown Network Type");
                }
            }
        }
        cellularInfo = log;
        return cellularInfo;
    }
    
}
