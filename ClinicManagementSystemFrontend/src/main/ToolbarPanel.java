package main;

import org.json.simple.JSONObject;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolbarPanel extends JPanel {

    private JPanel toolbarPanel;
    private JToolBar toolbar;
    private JButton logoutButton;
    private JButton personalDataButton;
    private JButton userListButton;

    public ToolbarPanel(){
        super();
        this.toolbarPanel = this;
        this.createToolbar();
    }

    // tworzy toolbar na górze widoku
    private void createToolbar(){
        this.toolbar = new JToolBar();
        this.toolbar.setBorder( BorderFactory.createEmptyBorder() );
        toolbar.setFloatable( false);
        toolbar.addSeparator();
        personalDataButton = new PersonalData();
        toolbar.add(personalDataButton);
        userListButton = new UserList();
        toolbar.add(userListButton);
        logoutButton = new Logout();
        toolbar.add(logoutButton);
        toolbarPanel.add(toolbar);
        this.toolbarPanel.setBackground(new Color(255, 228, 188));
    }

    // klasa dla przycisku 'Wyloguj sie'
    class Logout extends JButton implements ActionListener {
        Logout(){
            super("Wyloguj sie");
            addActionListener(this);
            setBackground(new Color(188, 214, 255));
            Border emptyBorder = BorderFactory.createEmptyBorder(10,10,10,10);
            this.setBorder(emptyBorder);
            // event po najechaniu i zjechaniu myszką z przycisku
            this.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    logoutButton.setBackground(new Color(211, 228, 255));
                    logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    logoutButton.setBackground(new Color(188, 214, 255));
                }
            });
        }

        public void actionPerformed(ActionEvent e) {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(toolbarPanel);
            JScrollPane scroll = new JScrollPane(new LoginPanel(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            topFrame.getContentPane().removeAll();
            topFrame.getContentPane().add(scroll);
            topFrame.invalidate();
            topFrame.validate();
            topFrame.repaint();
            topFrame.setTitle("Logowanie");
        }
    }

    // klasa dla przycisku 'Twoje dane'
    class PersonalData extends JButton implements ActionListener {
        PersonalData(){
            super("Twoje dane");
            addActionListener(this);
            setBackground(new Color(188, 214, 255));
            Border emptyBorder = BorderFactory.createEmptyBorder(10,10,10,10);
            this.setBorder(emptyBorder);
            // event po najechaniu i zjechaniu myszką z przycisku
            this.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    personalDataButton.setBackground(new Color(211, 228, 255));
                    personalDataButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    personalDataButton.setBackground(new Color(188, 214, 255));
                }
            });
        }

        public void actionPerformed(ActionEvent e) {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(toolbarPanel);
            JScrollPane scroll = new JScrollPane(new PersonalDataPanel(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            topFrame.getContentPane().removeAll();
            topFrame.getContentPane().add(scroll);
            topFrame.invalidate();
            topFrame.validate();
            topFrame.repaint();
            topFrame.setTitle("Twoje dane");
        }
    }

    // klasa dla przycisku 'Lista użytkowników'
    class UserList extends JButton implements ActionListener {
        UserList(){
            super("Lista uzytkownikow");
            addActionListener(this);
            setBackground(new Color(188, 214, 255));
            Border emptyBorder = BorderFactory.createEmptyBorder(10,10,10,10);
            this.setBorder(emptyBorder);
            // event po najechaniu i zjechaniu myszką z przycisku
            this.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    userListButton.setBackground(new Color(211, 228, 255));
                    userListButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    userListButton.setBackground(new Color(188, 214, 255));
                }
            });
        }

        public void actionPerformed(ActionEvent e) {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(toolbarPanel);
            JScrollPane scroll = new JScrollPane(new UserListPanel(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            topFrame.getContentPane().removeAll();
            topFrame.getContentPane().add(scroll);
            topFrame.invalidate();
            topFrame.validate();
            topFrame.repaint();
            topFrame.setTitle("Lista uzytkownikow");
        }
    }

//    private JSONObject getUsers(){
//
//    }
}
