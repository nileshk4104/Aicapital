package com.tech.aicapital;

import android.graphics.drawable.Drawable;

public class ExpandedMenuModel {
    private String submenu_name;
    private int iconImage;

    public String getSubmenu_name() {
        return submenu_name;
    }

    public void setSubmenu_name(String submenu_name) {
        this.submenu_name = submenu_name;
    }

    public int getIconImage() {
        return iconImage;
    }

    public void setIconImage(int iconImage) {
        this.iconImage = iconImage;
    }

    public ExpandedMenuModel(String submenu_name, int iconImage) {
        this.submenu_name = submenu_name;
        this.iconImage = iconImage;
    }
}
