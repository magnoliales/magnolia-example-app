package com.magnoliales.commentsapp.browser;

import com.vaadin.data.Item;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.AbstractClientConnector;
import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.server.Sizeable;
import com.vaadin.shared.MouseEventDetails;
import com.vaadin.ui.*;
import info.magnolia.ui.vaadin.actionbar.ActionPopup;
import org.vaadin.peter.contextmenu.ContextMenu;

import javax.inject.Inject;
import java.util.Date;

public class CommentsBrowserSubAppViewImpl implements CommentsBrowserSubAppView {

    public enum Status { NEW, APPROVED, REJECTED };

    private final VerticalLayout layout;
    private int counter;

    /**
     * @todo Add column generator for the status
     * @todo Move search box to the right
     * @todo Connect to the workspace
     * @todo See how to make use of dialogs in Magnolia from Vaadin application
     * @todo Implement search to parametrise the search box
     * @todo Add right mouse menu for simple actions and go to page
     */
    @Inject
    public CommentsBrowserSubAppViewImpl() {

        final Button button = new Button();
        final Table table = new Table();
        final ItemContextMenu contextMenu = getItemContextMenu(table);

        button.setCaption("Click me");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                table.addItem(new Object[] {
                    "Date", new Date().toString(), Status.APPROVED
                }, counter++);
            }
        });

        table.addContainerProperty("Title", String.class, null);
        table.addContainerProperty("Author", String.class, null);
        table.addContainerProperty("Status", Status.class, Status.NEW);
        table.setSizeFull();
        table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                if (event.getButton() == MouseEventDetails.MouseButton.RIGHT) {
                    // get the id and coordinates from the content
                    // @todo see {@link info.magnolia.ui.contentapp.browser.BrowserSubApp}
                    // @see ListViewImpl
                    // event.getItemId(), event.getClientX(), event.getClientY()
                    contextMenu.update(event.getItem());
                    contextMenu.open(event.getClientX(), event.getClientY());
                }
            }
        });

        layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.setSizeFull();

        layout.addComponent(button);
        layout.addComponent(table);

        layout.setExpandRatio(table, 1);
    }

    @Override
    public Component asVaadinComponent() {
        return layout;
    }

    private ItemContextMenu getItemContextMenu(final Table table) {
        ItemContextMenu contextMenu = new ItemContextMenu();
        contextMenu.setAsContextMenuOf(table);
        contextMenu.addItemClickListener(new ContextMenu.ContextMenuItemClickListener() {
            @Override
            public void contextMenuItemClicked(ContextMenu.ContextMenuItemClickEvent event) {
                // do something important
            }
        });
        return contextMenu;
    }

    private static class ItemContextMenu extends ContextMenu {

        public ItemContextMenu() {
            ContextMenuItem approveItem = this.addItem("Approve");

            ContextMenuItem rejectItem = this.addItem("Reject");
            rejectItem.setSeparatorVisible(true);

            ContextMenuItem linkItem = this.addItem("Go to website");
            linkItem.addItemClickListener(new ContextMenuItemClickListener() {
                @Override
                public void contextMenuItemClicked(ContextMenuItemClickEvent event) {
                    JavaScript.getCurrent().execute("window.open('http://google.com')");
                }
            });
        }

        public void update(Item item) {
            // depending on status one of the items needs to be disabled
        }
    }
}
