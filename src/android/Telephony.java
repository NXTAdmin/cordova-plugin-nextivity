/* Telephony Plugin - John Owen */
package com.nextivity.telephony;

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


import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellIdentityCdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.CellSignalStrengthLte;
import android.telephony.CellSignalStrengthWcdma;
import android.telephony.TelephonyManager;
import android.telephony.ServiceState;
import android.util.Log;


public class Telephony extends CordovaPlugin {

    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {


        if ("getCellInfo".equals(action)) {
            JSONObject s = new JSONObject();
            s.put("cellInfo", getCellularInfo());
                        
            callbackContext.success(s);
            return true;
        }else {
            JSONObject e = new JSONObject();
            e.put("error", "Telephony plugin had really bad error.");
            callbackContext.error(e);
            return false;
        }
    }

    //--------------------------------------------------------------------------
    // LOCAL METHODS
    //--------------------------------------------------------------------------

    private String getCellularInfo()
    {
        String cellularInfo = "";
        String log = "";
        
        TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
//        ServiceState serviceState = tm.getServiceState();
//        log += " ChNum: " + serviceState.getChannelNumber();
//        log += " OpName: " + serviceState.getOperatorAlphaLong();
        
        
        
        
        
        if(tm.getAllCellInfo()==null) {
            log += " getAllCellInfo returned null.";
//            Log.v(TAG, "getAllCellInfo returned null");
        }
        else if(tm.getAllCellInfo().isEmpty()) {
            log += " getAllCellInfo returned empty.";
//            Log.v(TAG, "getAllCellInfo returned empty");
        }
        else {
            for (final CellInfo info : tm.getAllCellInfo()) {
                
                if( log.length() > 0 )
                {
                    log += ", ";
                }
                
                if (info instanceof CellInfoGsm) {
                    log += "tech:GSM fcn:0 isReg:false dbm:0";  // tbd
//                    CellIdentityGsm gsm_cell = ((CellInfoGsm) info).getCellIdentity();
//                    log += gsm_cell.getCid() + "#" + gsm_cell.getLac() + "#" + gsm_cell.getMcc() + "#" + gsm_cell.getMnc() + "_";

//                    final CellSignalStrengthGsm gsm = ((CellInfoGsm) info).getCellSignalStrength();
//                    log += gsm.getDbm() + "#" + gsm.getLevel()+"#"+gsm.getAsuLevel()+":";
                } else if (info instanceof CellInfoCdma) {
                    log += "tech:CDMA fcn:0 isReg:false dbm:0";  // tbd
//                    CellIdentityCdma cdma_cell = ((CellInfoCdma) info).getCellIdentity();
//                    log += cdma_cell.getBasestationId() + "#" + cdma_cell.getNetworkId() + "#" + cdma_cell.getSystemId() + "#" + cdma_cell.getSystemId() + "_";

//                    final CellSignalStrengthCdma cdma = ((CellInfoCdma) info).getCellSignalStrength();
//                    log += cdma.getDbm() + "#" + cdma.getLevel()+"#"+cdma.getAsuLevel()+":";
                } else if (info instanceof CellInfoLte) {
                    log += "tech:LTE fcn:";
                    CellIdentityLte lte_cell = ((CellInfoLte) info).getCellIdentity();
                    log += lte_cell.getEarfcn();
                    log += " isReg:" + info.isRegistered();
//                    log += lte_cell.getCi() + "#" + lte_cell.getPci() + "#" + lte_cell.getMcc() + "#" + lte_cell.getMnc() + "_";

                    final CellSignalStrengthLte lte = ((CellInfoLte) info).getCellSignalStrength();
                    log += " dbm:" + lte.getDbm();
//                    log += lte.getDbm() + "#" + lte.getLevel()+"#"+lte.getAsuLevel()+":";
                } else if (info instanceof CellInfoWcdma) {
                    log += "tech:WCDMA fcn:";
                    CellIdentityWcdma wcdma_cell = ((CellInfoWcdma) info).getCellIdentity();
                    log += wcdma_cell.getUarfcn();
                    log += " isReg:" + info.isRegistered();
//                    log += wcdma_cell.getCid() + "#" + wcdma_cell.getLac() + "#" + wcdma_cell.getMcc() + "#" + wcdma_cell.getMnc() + "_";

                    final CellSignalStrengthWcdma wcdma = ((CellInfoWcdma) info).getCellSignalStrength();
                    log += " dbm:" + wcdma.getDbm();
//                    log += wcdma.getDbm() + "#" + wcdma.getLevel()+"#"+wcdma.getAsuLevel()+":";
                } else {
                    log += "tech:???? fcn:0 isReg:false dbm:0";
//                    Log.v(TAG, "Unknown Network Type");
                }
            }
        }
        
        
        
        cellularInfo = log;
        return cellularInfo;
    }
    
}
