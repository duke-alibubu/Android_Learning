package com.example.android.dessertclicker.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class ActivityMainBinding extends ViewDataBinding {
  @NonNull
  public final TextView amountSoldText;

  @NonNull
  public final ImageView backgroundImage;

  @NonNull
  public final ImageButton dessertButton;

  @NonNull
  public final TextView dessertSoldText;

  @NonNull
  public final Guideline marginBottomGuide;

  @NonNull
  public final Guideline marginEndGuide;

  @NonNull
  public final Guideline marginStartGuide;

  @NonNull
  public final TextView revenueText;

  @NonNull
  public final View whiteBackground;

  @Bindable
  protected Integer mRevenue;

  @Bindable
  protected Integer mAmountSold;

  protected ActivityMainBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, TextView amountSoldText, ImageView backgroundImage,
      ImageButton dessertButton, TextView dessertSoldText, Guideline marginBottomGuide,
      Guideline marginEndGuide, Guideline marginStartGuide, TextView revenueText,
      View whiteBackground) {
    super(_bindingComponent, _root, _localFieldCount);
    this.amountSoldText = amountSoldText;
    this.backgroundImage = backgroundImage;
    this.dessertButton = dessertButton;
    this.dessertSoldText = dessertSoldText;
    this.marginBottomGuide = marginBottomGuide;
    this.marginEndGuide = marginEndGuide;
    this.marginStartGuide = marginStartGuide;
    this.revenueText = revenueText;
    this.whiteBackground = whiteBackground;
  }

  public abstract void setRevenue(@Nullable Integer revenue);

  @Nullable
  public Integer getRevenue() {
    return mRevenue;
  }

  public abstract void setAmountSold(@Nullable Integer amountSold);

  @Nullable
  public Integer getAmountSold() {
    return mAmountSold;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityMainBinding>inflate(inflater, com.example.android.dessertclicker.R.layout.activity_main, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityMainBinding>inflate(inflater, com.example.android.dessertclicker.R.layout.activity_main, null, false, component);
  }

  public static ActivityMainBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ActivityMainBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ActivityMainBinding)bind(component, view, com.example.android.dessertclicker.R.layout.activity_main);
  }
}
