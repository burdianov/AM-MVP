package com.testography.am_mvp.mvp.presenters;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.testography.am_mvp.R;
import com.testography.am_mvp.data.managers.DataManager;
import com.testography.am_mvp.mvp.models.AuthModel;
import com.testography.am_mvp.mvp.views.IAuthView;
import com.testography.am_mvp.ui.custom_views.AuthPanel;
import com.testography.am_mvp.utils.CredentialsValidator;

import java.lang.ref.WeakReference;

public class AuthPresenter implements IAuthPresenter {

    private static Context sAppContext = DataManager.getInstance().getAppContext();
    private static AuthPresenter ourInstance = new AuthPresenter();

    private AuthModel mAuthModel;
    private WeakReference<IAuthView> mAuthView;

    private AuthPresenter() {
        mAuthModel = new AuthModel();
    }

    public static AuthPresenter getInstance() {
        return ourInstance;
    }

    @Override
    public void takeView(IAuthView authView) {
        mAuthView = new WeakReference<IAuthView>(authView);
    }

    @Override
    public void dropView() {
        mAuthView = null;
    }

    @Override
    public void initView() {
        if (getView() != null) {
            if (checkUserAuth()) {
                getView().hideLoginBtn();
            } else {
                getView().showLoginBtn();
            }
            getView().animateSocialButtons();
            getView().addChangeTextListeners();
            getView().setTypeface();
        }
    }

    @Nullable
    @Override
    public IAuthView getView() {
        return mAuthView.get();
    }

    @Override
    public void clickOnLogin() {
        if (getView() != null && getView().getAuthPanel() != null) {
            if (getView().getAuthPanel().isIdle()) {
                getView().getAuthPanel().setCustomState(AuthPanel.LOGIN_STATE);
            } else {
                String email = getView().getAuthPanel().getUserEmail();
                String password = getView().getAuthPanel().getUserPassword();

                if (!CredentialsValidator.isValidEmail(email)) {
                    getView().requestEmailFocus();
                    getView().showMessage(sAppContext.getString(R.string.err_msg_email));
                    return;
                }
                if (!CredentialsValidator.isValidPassword(password)) {
                    getView().requestPasswordFocus();
                    getView().showMessage(sAppContext.getString(R.string.err_msg_password));
                    return;
                }
                mAuthModel.loginUser(email, password);
                getView().showLoad();
                getView().showMessage("request for user auth");

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        getView().hideLoad();
                    }
                }, 3000);
            }
        }
    }

    @Override
    public void clickOnFb() {
        if (getView() != null) {
            getView().showMessage("clickOnFb");
        }
    }

    @Override
    public void clickOnVk() {
        if (getView() != null) {
            getView().showMessage("clickOnVk");
        }
    }

    @Override
    public void clickOnTwitter() {
        if (getView() != null) {
            getView().showMessage("clickOnTwitter");
        }
    }

    @Override
    public void clickOnShowCatalog() {
        if (getView() != null) {
            getView().showMessage("Show the catalog");
        }
    }

    @Override
    public boolean checkUserAuth() {
        return mAuthModel.isAuthUser();
    }
}
