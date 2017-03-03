package com.dongqing.formatview;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.widget.TextView;

import java.math.BigDecimal;

/**
 * Created by dongqing on 2017/3/2.
 * android 金额格式化显示控件
 */

public class FormatView extends TextView {
    private boolean symbolShow = true;
    private int symbolType = 1;
    private int symbolColor;
    private float symbolSize;
    private int prefixColor;
    private float prefixSize;
    private int suffixColor;
    private float suffixSize;
    private int suffixLength;
    private String moneyText;


    public FormatView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FormatView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        int default_symbol_color = getResources().getColor(R.color.default_symbol_color);
        float default_symbol_size = getResources().getDimension(R.dimen.default_symbol_size);
        int default_prefix_color = getResources().getColor(R.color.default_prefix_color);
        float default_prefix_size = getResources().getDimension(R.dimen.default_prefix_size);
        int default_suffix_color = getResources().getColor(R.color.default_suffix_color);
        float default_suffix_size = getResources().getDimension(R.dimen.default_suffix_size);
        int default_suffix_length = getResources().getInteger(R.integer.default_suffix_length);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FormatView, 0, 0);
        try {
            symbolShow = a.getBoolean(R.styleable.FormatView_symbol_show, true);
            symbolType = a.getInteger(R.styleable.FormatView_symbol_style, 1);
            symbolColor = a.getColor(R.styleable.FormatView_symbol_color, default_symbol_color);
            symbolSize = a.getDimension(R.styleable.FormatView_symbol_size, default_symbol_size);
            prefixColor = a.getColor(R.styleable.FormatView_prefix_color, default_prefix_color);
            prefixSize = a.getDimension(R.styleable.FormatView_prefix_size, default_prefix_size);
            suffixColor = a.getColor(R.styleable.FormatView_suffix_color, default_suffix_color);
            suffixSize = a.getDimension(R.styleable.FormatView_suffix_size, default_suffix_size);
            suffixLength = a.getInteger(R.styleable.FormatView_suffix_length, default_suffix_length);
            moneyText = a.getFloat(R.styleable.FormatView_money, 0) + "";
        } catch (Exception e) {
            a.recycle();
        }
        init();
    }


    private void init() {
        moneyText = decimalFormat(suffixLength, moneyText);
        SpannableStringBuilder ssb = new SpannableStringBuilder();
        if (symbolShow) {
            switch (symbolType) {
                case 0:
                    ssb.append("$");
                    break;
                case 1:
                    ssb.append("￥");
                    break;
                case 2:
                    ssb.append("円");
                    break;
                case 3:
                    ssb.append("₩");
                    break;
                default:
                    ssb.append("￥");
                    break;
            }
        }
        ssb.append(moneyText);
        if(symbolShow) {
            ssb.setSpan(new ForegroundColorSpan(symbolColor), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ssb.setSpan(new ForegroundColorSpan(prefixColor), 1, ssb.toString().lastIndexOf("."), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ssb.setSpan(new AbsoluteSizeSpan((int) symbolSize), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ssb.setSpan(new AbsoluteSizeSpan((int) prefixSize), 1, ssb.toString().lastIndexOf("."), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }else{
            ssb.setSpan(new ForegroundColorSpan(prefixColor), 0, ssb.toString().lastIndexOf("."), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ssb.setSpan(new AbsoluteSizeSpan((int) prefixSize), 0, ssb.toString().lastIndexOf("."), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        }
        ssb.setSpan(new ForegroundColorSpan(suffixColor), ssb.toString().lastIndexOf("."), ssb.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.setSpan(new AbsoluteSizeSpan((int) suffixSize), ssb.toString().lastIndexOf("."), ssb.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        setText(ssb);
    }

    public boolean isSymbolShow() {
        return symbolShow;

    }

    public void setSymbolShow(boolean symbolShow) {
        this.symbolShow = symbolShow;
    }

    public int getSymbolType() {
        return symbolType;
    }

    public void setSymbolType(int symbolType) {
        this.symbolType = symbolType;
    }

    public int getSymbolColor() {
        return symbolColor;
    }

    public void setSymbolColor(int symbolColor) {
        this.symbolColor = symbolColor;
    }

    public float getSymbolSize() {
        return symbolSize;
    }

    public void setSymbolSize(float symbolSize) {
        this.symbolSize = symbolSize;
    }

    public int getPrefixColor() {
        return prefixColor;
    }

    public void setPrefixColor(int prefixColor) {
        this.prefixColor = prefixColor;
    }

    public float getPrefixSize() {
        return prefixSize;
    }

    public void setPrefixSize(float prefixSize) {
        this.prefixSize = prefixSize;
    }

    public int getSuffixColor() {
        return suffixColor;
    }

    public void setSuffixColor(int suffixColor) {
        this.suffixColor = suffixColor;
    }

    public float getSuffixSize() {
        return suffixSize;
    }

    public void setSuffixSize(float suffixSize) {
        this.suffixSize = suffixSize;
    }

    public float getSuffixLength() {
        return suffixLength;
    }

    public void setSuffixLength(int suffixLength) {
        this.suffixLength = suffixLength;
    }

    public String getMoneyText() {
        return moneyText;
    }

    public void setMoneyText(String moneyText) {
        this.moneyText = moneyText;
        init();
    }

    private String decimalFormat(int it, String decimal) {
        if (it < 0) {
            return decimal;
        }
        BigDecimal bd = new BigDecimal(decimal);
        bd = bd.setScale(it, BigDecimal.ROUND_DOWN);
        return bd.toString();
    }
}
