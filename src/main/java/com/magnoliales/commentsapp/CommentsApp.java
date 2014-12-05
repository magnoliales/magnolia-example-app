package com.magnoliales.commentsapp;

import info.magnolia.ui.api.app.AppContext;
import info.magnolia.ui.api.app.AppView;
import info.magnolia.ui.framework.app.BaseApp;

import javax.inject.Inject;

public class CommentsApp extends BaseApp {

    private AppContext appContext;

    @Inject
    public CommentsApp(AppContext appContext, AppView view) {
        super(appContext, view);
    }
}
