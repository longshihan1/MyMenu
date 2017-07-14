package com.longshihan.install;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.HashMap;

public class InstallService  extends AccessibilityService {
    HashMap<Integer, Boolean> hashmap = new HashMap<>();
    private static final String TAG = "[TAG]";
    public InstallService() {
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        //模拟点击 安装 完成 确定
        AccessibilityNodeInfo nodeInfo = accessibilityEvent.getSource();
        if (nodeInfo != null) {
            int eventType = accessibilityEvent.getEventType();
            // if (eventType == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED || eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            if (hashmap.get(accessibilityEvent.getWindowId()) == null) {
                boolean handled = interNoderInfo(nodeInfo);
                if (handled) {
                    hashmap.put(accessibilityEvent.getWindowId(), true);
                }
            }
            //}
        }
    }

    private boolean interNoderInfo(AccessibilityNodeInfo nodeInfo) {
        if (nodeInfo!=null) {
            int childCount = nodeInfo.getChildCount();
            if (nodeInfo.getClassName().equals("android.widget.Button")) {
                String nodeContent = nodeInfo.getText().toString();
                Log.d(TAG, "content is: " + nodeContent);
                if (nodeContent.equals("确定") || nodeContent.equals("完成") ||
                        nodeContent.equals("安装")) {
                    nodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    return true;
                }
            } else if ("android.widget.ScrollView".equals(nodeInfo.getClassName())) {
                nodeInfo.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
            }
            for (int i = 0; i < childCount; i++) {
                AccessibilityNodeInfo childNodeInfo  = nodeInfo.getChild(i);
                if (childNodeInfo  != null) {
                    if (interNoderInfo(childNodeInfo )) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void onInterrupt() {

    }

}