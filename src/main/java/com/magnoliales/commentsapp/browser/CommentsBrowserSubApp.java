package com.magnoliales.commentsapp.browser;

import info.magnolia.ui.api.app.SubAppContext;
import info.magnolia.ui.framework.app.BaseSubApp;

import javax.inject.Inject;

public class
        CommentsBrowserSubApp extends BaseSubApp<CommentsBrowserSubAppView> {

    @Inject
    protected CommentsBrowserSubApp(SubAppContext subAppContext, CommentsBrowserSubAppViewImpl view) {
        super(subAppContext, view);
    }
}
