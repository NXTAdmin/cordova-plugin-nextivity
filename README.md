# Cordova Phony Plugin

This plugin allows you to retrieve cellular information from Android and iOS devices.

## Installation ##

```cordova plugin add https://github.com/NXTAdmin/cordova-plugin-nextivity```

## Methods ##

- [getCellInfo](#getCellInfo) (Android)
- [getCurrentRadioAccessTechnology](#getCurrentRadioAccessTechnology) (iOS)
- [getCTCarrier](#getCTCarrier) (iOS)

### getCellInfo ###

*Android only*

Todo docs

```javascript
phony.getCellInfo(success, error);
```

### getCurrentRadioAccessTechnology ###

*iOS only*

Get the radio access technology constant that the phone is currently operating on. Wraps the iOS methods
[CTTelephonyNetworkInfo.serviceCurrentRadioAccessTechnology](https://developer.apple.com/documentation/coretelephony/cttelephonynetworkinfo/3024510-servicecurrentradioaccesstechnol)
if iOS >= 12 or
[CTTelephonyNetworkInfo.currentRadioAccessTechnology](https://developer.apple.com/documentation/coretelephony/cttelephonynetworkinfo/1616895-currentradioaccesstechnology)
if iOS < 12.

```javascript
phony.getCurrentRadioAccessTechnology(success, error);
```

##### Success #####

Returns a [Radio Access Technology Constant](#radio-access-technology-constants)
.

```javascript
'CTRadioAccessTechnologyWCDMA'
```

### getCTCarrier ###

*iOS only*

Get information about the user’s cellular service provider. Gets the iOS data object CTCarrier.
[CTCarrier iOS developer docs](https://developer.apple.com/documentation/coretelephony/ctcarrier)

```javascript
phony.getCellInfo(success, error);
```

#### Supported Platforms ####

- iOS 4.0+
- Mac Catalyst 13.0+

#### Success ####

Returns an object with the following properties:

| Property            | Type    | Description                                                             |
| ------------------- | ------- | ----------------------------------------------------------------------- |
| `allowsVOIP`        | Boolean | Indicates if the carrier allows making VoIP calls on its network.       |
| `carrierName`       | String  | The name of the user’s home cellular service provider.                  |
| `isoCountryCode`    | String  | The ISO 3166-1 country code for the user’s cellular service provider.   |
| `mobileCountryCode` | String  | The mobile country code (MCC) for the user’s cellular service provider. |
| `mobileNetworkCode` | String  | The mobile network code (MNC) for the user’s cellular service provider. |

*`allowsVOIP` will always contain a value. The other properties default to an empty string (`''`) when not reported by the device.*

##### Example Return Values #####

```javascript
// No carrier information reported by phone.
res = {
  allowsVOIP: true,
  carrierName: '',
  isoCountryCode: '',
  mobileCountryCode: '',
  mobileNetworkCode: ''
}
```

### getCTCarrierAsync ###

Same as [getCellInfo](#getCellInfo) but uses a Promise instead of callbacks.

```javascript
phony.getCTCarrierAsync();
```

## Radio Access Technology Constants ##

Constants that describe the current radio access technology. See the full docs at
[developer.apple.com](https://developer.apple.com/documentation/coretelephony/cttelephonynetworkinfo/radio_access_technology_constants)
.

- **CTRadioAccessTechnologyGPRS** - The General Packet Radio Service (GPRS)
  radio access technology.
- **CTRadioAccessTechnologyEdge** - The Enhanced Data rates for GSM Evolution (
  EDGE) radio access technology.
- **CTRadioAccessTechnologyWCDMA** - The Wideband Code Division Multiple Access (WCDMA) radio access technology.
- **CTRadioAccessTechnologyHSDPA** - The High-Speed Downlink Packet Access (
  HSDPA) radio access technology.
- **CTRadioAccessTechnologyHSUPA** - The High-Speed Uplink Packet Access (HSUPA)
  radio access technology.
- **CTRadioAccessTechnologyCDMA1x** - The Code Division Multiple Access (CDMA)
  1x radio access technology.
- **CTRadioAccessTechnologyCDMAEVDORev0** - The Code Division Multiple Access (
  CDMA) Evolution-Data Optimized (EV-DO) Rev. 0 radio access technology.
- **CTRadioAccessTechnologyCDMAEVDORevA** - The Code Division Multiple Access (
  CDMA) Evolution-Data Optimized (EV-DO) Rev. A radio access technology.
- **CTRadioAccessTechnologyCDMAEVDORevB** - The Code Division Multiple Access (
  CDMA) Evolution-Data Optimized (EV-DO) Rev. B radio access technology.
- **CTRadioAccessTechnologyeHRPD** - The Enhanced High Rate Packet Data (eHRPD)
  radio access technology.
- **CTRadioAccessTechnologyLTE** - The Long-Term Evolution (LTE) radio access technology.
- **CTRadioAccessTechnologyNRNSA** - The 5G New Radio Non-Standalone (NRNSA)
  radio access technology.
- **CTRadioAccessTechnologyNR** - The 5G New Radio (NR) radio access technology.

Any value can be referenced directly. For example:

```javascript
phony.radioAccessTechnologyConstants.CTRadioAccessTechnologyCDMAEVDORevB
```
