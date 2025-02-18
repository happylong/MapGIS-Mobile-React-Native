package com.zondy.mapgis.mobile.react;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.zondy.mapgis.android.graphic.GraphicsOverlay;
import com.zondy.mapgis.android.graphic.GraphicsOverlays;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fjl 2019-6-18 下午2:52:36
 * @content 覆盖物对象Native组件
 */
public class JSGraphicsOverlays extends ReactContextBaseJavaModule {
    public static final String REACT_CLASS = "JSGraphicsOverlays";
    public static Map<String, GraphicsOverlays> mGraphicsOverlaysList = new HashMap<String, GraphicsOverlays>();


    public JSGraphicsOverlays(ReactApplicationContext context) {
        super(context);
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    public static GraphicsOverlays getObjFromList(String id) {
        return mGraphicsOverlaysList.get(id);
    }


    public static String registerId(GraphicsOverlays obj) {
        for (Map.Entry entry : mGraphicsOverlaysList.entrySet()) {
            if (obj.equals(entry.getValue())) {
                String id = (String) entry.getKey();
                mGraphicsOverlaysList.put(id, obj);
                return (String) entry.getKey();
            }
        }

        Calendar calendar = Calendar.getInstance();
        String id = Long.toString(calendar.getTimeInMillis());
        mGraphicsOverlaysList.put(id, obj);
        return id;
    }

    @ReactMethod
    public void createObj(Promise promise) {
        try {
            GraphicsOverlays GraphicsOverlays = new GraphicsOverlays();
            String GraphicsOverlaysId = registerId(GraphicsOverlays);

            WritableMap map = Arguments.createMap();
            map.putString("GraphicsOverlaysId", GraphicsOverlaysId);
            promise.resolve(map);
        } catch (Exception e) {
            promise.reject(e);
        }
    }


    @ReactMethod
    public void add(String GraphicsOverlaysId, String graphicsOverlayId, Promise promise) {
        try {
            GraphicsOverlays graphicsOverlays = getObjFromList(GraphicsOverlaysId);
            GraphicsOverlay graphicsOverlay = JSGraphicsOverlay.mGraphicsOverlayList.get(graphicsOverlayId);
            graphicsOverlays.add(graphicsOverlay);
            promise.resolve(true);
        } catch (Exception e) {
            promise.reject(e);
        }
    }


    @ReactMethod
    public void getCount(String GraphicsOverlaysId, Promise promise) {
        try {
            GraphicsOverlays graphicsOverlays = getObjFromList(GraphicsOverlaysId);
            int count = graphicsOverlays.getCount();

            promise.resolve(count);
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    @ReactMethod
    public void indexOf(String GraphicsOverlaysId, String graphicsOverlayID, Promise promise) {
        try {
            GraphicsOverlays graphicsOverlays = getObjFromList(GraphicsOverlaysId);
            GraphicsOverlay graphicsOverlay = JSGraphicsOverlay.mGraphicsOverlayList.get(graphicsOverlayID);
            int index = graphicsOverlays.indexOf(graphicsOverlay);

            promise.resolve(index);
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    @ReactMethod
    public void getGraphicsOverlay(String GraphicsOverlaysId, int index, Promise promise) {
        try {
            GraphicsOverlays graphicsOverlays = getObjFromList(GraphicsOverlaysId);
            GraphicsOverlay graphicsOverlay = graphicsOverlays.getGraphicsOverlay(index);

            String GraphicsOverlayID = JSGraphicsOverlay.registerId(graphicsOverlay);
            WritableMap map = Arguments.createMap();
            map.putString("GraphicsOverlayID", GraphicsOverlayID);
            promise.resolve(map);

        } catch (Exception e) {
            promise.reject(e);
        }
    }

    @ReactMethod
    public void getAllGraphicsOverlays(String GraphicsOverlaysId, Promise promise) {
        try {
            GraphicsOverlays graphicsOverlays = getObjFromList(GraphicsOverlaysId);
            List<GraphicsOverlay> graphicsOverlayLst = graphicsOverlays.getAllGraphicsOverlays();

            String GraphicsOverlayID = "";

            WritableMap map = Arguments.createMap();
            map.putString("GraphicsOverlayID", GraphicsOverlayID);
            promise.resolve(map);

//            String featureID = "";
//            WritableArray arr = Arguments.createArray();
//            for (int i = 0; i < featureList.size();i++)
//            {
//                Feature feature = featureList.get(i);
//                featureID= JSFeature.registerId(feature);
//                arr.pushString(featureID);
//            }
//            WritableMap map = Arguments.createMap();
//            map.putArray("values",arr);
//            promise.resolve(map);
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    @ReactMethod
    public void insert(String GraphicsOverlaysId, int index, String graphicsOverlayID, Promise promise) {
        try {
            GraphicsOverlays graphicsOverlays = getObjFromList(GraphicsOverlaysId);
            GraphicsOverlay graphicsOverlay = JSGraphicsOverlay.mGraphicsOverlayList.get(graphicsOverlayID);
            int result = graphicsOverlays.insert(index, graphicsOverlay);

            promise.resolve(result);
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    @ReactMethod
    public void move(String GraphicsOverlaysId, int fromIndex, int toIndex, Promise promise) {
        try {
            GraphicsOverlays graphicsOverlays = getObjFromList(GraphicsOverlaysId);
            graphicsOverlays.move(fromIndex, toIndex);
            promise.resolve(true);
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    @ReactMethod
    public void remove(String GraphicsOverlaysId, int index, Promise promise) {
        try {
            GraphicsOverlays graphicsOverlays = getObjFromList(GraphicsOverlaysId);
            graphicsOverlays.remove(index);
            promise.resolve(true);
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    @ReactMethod
    public void removeAll(String GraphicsOverlaysId, Promise promise) {
        try {
            GraphicsOverlays graphicsOverlays = getObjFromList(GraphicsOverlaysId);
            graphicsOverlays.removeAll();
            promise.resolve(true);
        } catch (Exception e) {
            promise.reject(e);
        }
    }
}
