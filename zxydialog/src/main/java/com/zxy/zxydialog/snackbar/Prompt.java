package com.zxy.zxydialog.snackbar;


import com.zxy.zxydialog.R;

public enum Prompt {
    /**
     * 红色,错误
     */
    ERROR(R.drawable.common_bounced_icon_error, 0xffff0000),

    /**
     * 蓝色,警告
     */
    WARNING(R.drawable.common_bounced_icon_warning, 0xff00574B),

    /**
     * 绿色,成功
     */
    SUCCESS(R.drawable.common_bounced_icon_successful, 0xff000000);

    private int resIcon;
    private int backgroundColor;

    Prompt(int resIcon, int backgroundColor) {
        this.resIcon = resIcon;
        this.backgroundColor = backgroundColor;
    }

    public int getResIcon() {
        return resIcon;
    }

    public void setResIcon(int resIcon) {
        this.resIcon = resIcon;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
