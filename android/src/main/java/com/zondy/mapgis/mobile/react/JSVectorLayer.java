package com.zondy.mapgis.mobile.react;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.zondy.mapgis.core.map.VectorLayer;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fjl 2019-6-25 下午2:52:36
 * @content 覆盖物对象Native组件
 */
public class JSVectorLayer extends JSMapLayer {
    public static final String REACT_CLASS = "JSVectorLayer";
    public static Map<String, VectorLayer> mVectorLayerList = new HashMap<String, VectorLayer>();


    public JSVectorLayer(ReactApplicationContext context) {
        super(context);
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    public static VectorLayer getObjFromList(String id) {
        return mVectorLayerList.get(id);
    }

    public static String registerId(VectorLayer obj) {
        for (Map.Entry entry : mVectorLayerList.entrySet()) {
            if (obj.equals(entry.getValue())) {
                String id = (String) entry.getKey();
                mVectorLayerList.put(id, obj);
                return (String) entry.getKey();
            }
        }

        Calendar calendar = Calendar.getInstance();
        String id = Long.toString(calendar.getTimeInMillis());
        mVectorLayerList.put(id, obj);
        return id;
    }

    @ReactMethod
    public void createObj(Promise promise) {
        try {
            VectorLayer VectorLayer = new VectorLayer();
            String VectorLayerId = registerId(VectorLayer);

            WritableMap map = Arguments.createMap();
            map.putString("VectorLayerId", VectorLayerId);
            promise.resolve(map);
        } catch (Exception e) {
            promise.reject(e);
        }
    }


    @ReactMethod
    public void isSymbolShow(String VectorLayerId, Promise promise) {
        try {
            VectorLayer VectorLayer = getObjFromList(VectorLayerId);
            boolean isSymbolShow = VectorLayer.isSymbolShow();

            promise.resolve(isSymbolShow);
        } catch (Exception e) {
            promise.reject(e);
        }
    }


    @ReactMethod
    public void setSymbolShow(String VectorLayerId, boolean symbolShow, Promise promise) {
        try {
            VectorLayer VectorLayer = getObjFromList(VectorLayerId);
            VectorLayer.setSymbolShow(symbolShow);
            promise.resolve(true);
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    @ReactMethod
    public void getTransparency(String VectorLayerId, Promise promise) {
        try {
            VectorLayer VectorLayer = getObjFromList(VectorLayerId);
            short transparency = VectorLayer.getTransparency();

            promise.resolve(transparency);
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    @ReactMethod
    public void setTransparency(String VectorLayerId, short transparency, Promise promise) {
        try {
            VectorLayer VectorLayer = getObjFromList(VectorLayerId);
            VectorLayer.setTransparency(transparency);
            promise.resolve(true);
        } catch (Exception e) {
            promise.reject(e);
        }
    }

}
