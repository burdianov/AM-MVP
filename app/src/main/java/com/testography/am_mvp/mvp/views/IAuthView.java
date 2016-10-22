package com.testography.am_mvp.mvp.views;

import android.support.annotation.Nullable;

import com.testography.am_mvp.mvp.presenters.IAuthPresenter;
import com.testography.am_mvp.ui.custom_views.AuthPanel;

public interface IAuthView {

    void showMessage(String message);

    void showError(Throwable e);

    void showLoad();

    void hideLoad();

    IAuthPresenter getPresenter();

    void showLoginBtn();

    void hideLoginBtn();

    @Nullable
    AuthPanel getAuthPanel();

    void requestEmailFocus();

    void requestPasswordFocus();

    void animateSocialButtons();

    void setTypeface();

    void addChangeTextListeners();
}
