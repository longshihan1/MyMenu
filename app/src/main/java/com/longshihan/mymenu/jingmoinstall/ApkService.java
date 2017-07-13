package com.longshihan.mymenu.jingmoinstall;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.HashMap;

public class ApkService extends AccessibilityService {

    HashMap<Integer, Boolean> hashmap = new HashMap<>();

    public ApkService() {
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        //模拟点击 安装 完成 确定
        AccessibilityNodeInfo nodeInfo = accessibilityEvent.getSource();
        if (nodeInfo != null) {
            boolean handler = interNoderInfo(nodeInfo);
            if (handler) {
                hashmap.put(accessibilityEvent.getWindowId(), true);
            }
        }
    }

    private boolean interNoderInfo(AccessibilityNodeInfo nodeInfo) {
        int childCount = nodeInfo.getChildCount();
        if (nodeInfo.getClassName().equals("android.widget.Button")) {
            String nodeContent = nodeInfo.getText().toString();
            if (nodeContent.equals("确定") || nodeContent.equals("完成") ||
                    nodeContent.equals("安装")) {
                nodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }
        } else if (nodeInfo.getClassName().equals("android.widget.ScrollView")) {
            nodeInfo.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
        }
        for (int i = 0; i < childCount; i++) {
            AccessibilityNodeInfo nodeInfos = nodeInfo.getChild(i);
            if (nodeInfos!=null) {
                if (interNoderInfo(nodeInfos)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void onInterrupt() {

    }

}
