/*
 * Created by JFormDesigner on Sat May 03 15:04:08 EEST 2014
 */

package app;

import core.dao.UserDAO;
import core.model.Util.DateValidator;
import core.model.Util.EmailValidator;
import core.model.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Alex
 */
public class Profile extends JFrame {

    private User user;

    public Profile() {
        initComponents();
    }

    private void updateUserLabels() {
        label35.setText(" " + user.getName() + " " + user.getFirstName() + " " + user.getSurname());
        label2.setText(user.getBirthday());
        label1.setText(user.getMail());

        editorPane2.setText(user.getMail());
        editorPane3.setText(user.getName());
        editorPane5.setText(user.getSurname());
        editorPane5.setText(user.getFirstName());

        String date = user.getBirthday();
        String[] buf = date.split(" ");
        editorPane7.setText(buf[0]);
        editorPane8.setText(buf[1]);
        editorPane9.setText(buf[2]);
    }

    public void setUser(User user) {
        this.user = user;
        label33.setIcon(new ImageIcon(getClass().getResource("/core/data/images/" + user.getPhoto() + ".png")));
        label3.setIcon(new ImageIcon(getClass().getResource("/core/data/images/" + user.getPhoto() + ".png")));
        updateUserLabels();
    }

    private void thisWindowClosing(WindowEvent e) {
        UserDAO.getInstance().close();
    }

    private void cancelButton3ActionPerformed(ActionEvent e) {
        UserProfile.setVisible(false);
        UserEdit.setVisible(true);
        updateUserLabels();
    }

    private void cancelButton2ActionPerformed(ActionEvent e) {
        UserEdit.setVisible(false);
        UserProfile.setVisible(true);
    }

    private void okButton2ActionPerformed(ActionEvent e) {
        User user = this.user;
        if (!editorPane2.getText().equals("") && !editorPane3.getText().equals("") && !editorPane5.getText().equals("")
                && !editorPane4.getText().equals("") && !editorPane8.getText().equals("") && !editorPane7.getText().equals("")
                && !editorPane9.getText().equals("")) {
            DateValidator dateValidator = new DateValidator();
            String date = editorPane7.getText() + " " + editorPane8.getText() + " " + editorPane9.getText();

            EmailValidator emailValidator = new EmailValidator();
            if (emailValidator.validate(editorPane2.getText())) {
                if (dateValidator.dateValid(date)) {
                    if (passwordField1.getText() != "" && user.getPassword().equals(passwordField1.getText())) {
                        if (passwordField2.getText() != "" && passwordField2.getText().equals(passwordField3.getText())) {
                            user.setPassword(passwordField2.getText());
                        } else
                            JOptionPane.showMessageDialog(this, "Пароли не совпадают");
                    } else if (!passwordField1.getText().equals(""))
                        JOptionPane.showMessageDialog(this, "Введен неверный пароль");
                    user.setMail(editorPane2.getText());
                    user.setName(editorPane3.getText());
                    user.setFirstName(editorPane4.getText());
                    user.setSurname(editorPane5.getText());
                    user.setStringBirthday(date);
                    UserDAO.getInstance().update(this.user, user);
                    this.user = user;
                    updateUserLabels();
                    JOptionPane.showMessageDialog(this, "Данные успешно обновлены");
                    UserEdit.setVisible(false);
                    UserProfile.setVisible(true);
                } else
                    JOptionPane.showMessageDialog(this, "Введена неправильная дата");
            } else
                JOptionPane.showMessageDialog(this, "Введен неправильный E-mail");
        } else
            JOptionPane.showMessageDialog(this, "Не все поля заполнены!");
    }

    private void button1ActionPerformed(ActionEvent e) {
        JFileChooser fileopen = new JFileChooser();

        FileFilter filter = new FileNameExtensionFilter("PNG File", "png");
        fileopen.setFileFilter(filter);

        int ret = fileopen.showDialog(null, "Открыть файл");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileopen.getSelectedFile();
            try {
                BufferedImage image = ImageIO.read(file);
                BufferedImage scaled = new BufferedImage(150, 150, BufferedImage.TYPE_INT_RGB);
                Graphics2D g = scaled.createGraphics();
                g.drawImage(image, 0, 0, 150, 150, null);
                g.dispose();
                ImageIO.write(scaled, "PNG", new File(System.getProperty("user.dir") + "/src/core/data/images/" + user.getPhoto() + ".png"));
                label3.setIcon(new ImageIcon(scaled));
            } catch (IOException e1) {
            }
        }
    }

    private void okButton3ActionPerformed(ActionEvent e) {
        if ((new File(System.getProperty("user.dir") + "/src/app/data/USER.TOKEN")).exists())
            new File(System.getProperty("user.dir") + "/src/app/data/USER.TOKEN").delete();
        this.dispose();
        new Login();
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        UserProfile = new JPanel();
        contentPanel3 = new JPanel();
        panel10 = new JPanel();
        label33 = new JLabel();
        label35 = new JLabel();
        label41 = new JLabel();
        scrollPane25 = new JScrollPane();
        scrollPane29 = new JScrollPane();
        label2 = new JLabel();
        label34 = new JLabel();
        scrollPane26 = new JScrollPane();
        scrollPane24 = new JScrollPane();
        label1 = new JLabel();
        scrollPane27 = new JScrollPane();
        scrollPane28 = new JScrollPane();
        scrollPane30 = new JScrollPane();
        scrollPane31 = new JScrollPane();
        panel11 = new JPanel();
        buttonBar3 = new JPanel();
        okButton3 = new JButton();
        cancelButton3 = new JButton();
        UserEdit = new JPanel();
        contentPanel2 = new JPanel();
        panel1 = new JPanel();
        button1 = new JButton();
        label3 = new JLabel();
        label4 = new JLabel();
        scrollPane5 = new JScrollPane();
        editorPane2 = new JEditorPane();
        label12 = new JLabel();
        scrollPane2 = new JScrollPane();
        passwordField1 = new JPasswordField();
        label5 = new JLabel();
        scrollPane3 = new JScrollPane();
        passwordField2 = new JPasswordField();
        label11 = new JLabel();
        scrollPane4 = new JScrollPane();
        passwordField3 = new JPasswordField();
        label6 = new JLabel();
        scrollPane6 = new JScrollPane();
        editorPane3 = new JEditorPane();
        label71 = new JLabel();
        scrollPane8 = new JScrollPane();
        editorPane5 = new JEditorPane();
        label7 = new JLabel();
        scrollPane7 = new JScrollPane();
        editorPane4 = new JEditorPane();
        label8 = new JLabel();
        scrollPane9 = new JScrollPane();
        panel2 = new JPanel();
        scrollPane11 = new JScrollPane();
        editorPane7 = new JEditorPane();
        label9 = new JLabel();
        scrollPane12 = new JScrollPane();
        editorPane8 = new JEditorPane();
        label10 = new JLabel();
        scrollPane13 = new JScrollPane();
        editorPane9 = new JEditorPane();
        buttonBar2 = new JPanel();
        okButton2 = new JButton();
        cancelButton2 = new JButton();

        //======== this ========
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== UserProfile ========
        {
            UserProfile.setBorder(new EmptyBorder(12, 12, 12, 12));
            UserProfile.setLayout(new BorderLayout());

            //======== contentPanel3 ========
            {
                contentPanel3.setPreferredSize(new Dimension(470, 290));
                contentPanel3.setMinimumSize(new Dimension(470, 194));
                contentPanel3.setMaximumSize(new Dimension(470, 290));
                contentPanel3.setLayout(new BoxLayout(contentPanel3, BoxLayout.X_AXIS));

                //======== panel10 ========
                {
                    panel10.setLayout(new GridBagLayout());
                    ((GridBagLayout) panel10.getLayout()).columnWidths = new int[]{156, 113, 127, 0};
                    ((GridBagLayout) panel10.getLayout()).rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                    ((GridBagLayout) panel10.getLayout()).columnWeights = new double[]{0.0, 0.0, 0.0, 1.0E-4};
                    ((GridBagLayout) panel10.getLayout()).rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

                    //---- label33 ----
                    label33.setIcon(new ImageIcon(getClass().getResource("/core/data/images/no_avatar.png")));
                    label33.setMaximumSize(new Dimension(150, 150));
                    label33.setMinimumSize(new Dimension(150, 150));
                    label33.setPreferredSize(new Dimension(150, 150));
                    label33.setVerticalAlignment(SwingConstants.TOP);
                    label33.setVerticalTextPosition(SwingConstants.TOP);
                    label33.setBorder(null);
                    panel10.add(label33, new GridBagConstraints(0, 0, 1, 7, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));

                    //---- label35 ----
                    label35.setText(" \u0413\u043e\u043d\u0447\u0430\u0440\u0435\u043d\u043a\u043e \u0410\u043b\u0435\u043a\u0441\u0430\u043d\u0434\u0440 \u0420\u0443\u0441\u043b\u0430\u043d\u043e\u0432\u0438\u0447");
                    label35.setFont(new Font("Arial", Font.BOLD, 16));
                    label35.setVerticalAlignment(SwingConstants.TOP);
                    label35.setBorder(null);
                    panel10.add(label35, new GridBagConstraints(1, 0, 2, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 0), 0, 0));

                    //---- label41 ----
                    label41.setText(" \u0414\u0430\u0442\u0430 \u0440\u043e\u0436\u0434\u0435\u043d\u0438\u044f:");
                    label41.setFont(new Font("Arial", Font.BOLD, 16));
                    label41.setVerticalAlignment(SwingConstants.TOP);
                    label41.setBorder(null);
                    panel10.add(label41, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));

                    //======== scrollPane25 ========
                    {
                        scrollPane25.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                        scrollPane25.setViewportBorder(null);
                        scrollPane25.setBorder(null);
                        scrollPane25.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

                        //======== scrollPane29 ========
                        {
                            scrollPane29.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                            scrollPane29.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                            scrollPane29.setBorder(null);

                            //---- label2 ----
                            label2.setText("24 \u0438\u044e\u043b\u044f 1995");
                            label2.setFont(new Font("Arial", Font.PLAIN, 16));
                            scrollPane29.setViewportView(label2);
                        }
                        scrollPane25.setViewportView(scrollPane29);
                    }
                    panel10.add(scrollPane25, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 0), 0, 0));

                    //---- label34 ----
                    label34.setText(" \u0412\u0430\u0448 E-mail:");
                    label34.setFont(new Font("Arial", Font.BOLD, 16));
                    label34.setVerticalAlignment(SwingConstants.TOP);
                    label34.setBorder(null);
                    panel10.add(label34, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));

                    //======== scrollPane26 ========
                    {
                        scrollPane26.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                        scrollPane26.setViewportBorder(null);
                        scrollPane26.setBorder(null);
                        scrollPane26.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

                        //======== scrollPane24 ========
                        {
                            scrollPane24.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                            scrollPane24.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                            scrollPane24.setBorder(null);

                            //---- label1 ----
                            label1.setText("Aleck94@gmail.com");
                            label1.setFont(new Font("Arial", Font.PLAIN, 16));
                            scrollPane24.setViewportView(label1);
                        }
                        scrollPane26.setViewportView(scrollPane24);
                    }
                    panel10.add(scrollPane26, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 0), 0, 0));

                    //======== scrollPane27 ========
                    {
                        scrollPane27.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                        scrollPane27.setViewportBorder(null);
                        scrollPane27.setBorder(null);
                        scrollPane27.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                    }
                    panel10.add(scrollPane27, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 0), 0, 0));

                    //======== scrollPane28 ========
                    {
                        scrollPane28.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                        scrollPane28.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                        scrollPane28.setBorder(null);
                    }
                    panel10.add(scrollPane28, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 0), 0, 0));

                    //======== scrollPane30 ========
                    {
                        scrollPane30.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                        scrollPane30.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                        scrollPane30.setBorder(null);
                    }
                    panel10.add(scrollPane30, new GridBagConstraints(2, 6, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 0), 0, 0));

                    //======== scrollPane31 ========
                    {
                        scrollPane31.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                        scrollPane31.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                        scrollPane31.setBorder(null);

                        //======== panel11 ========
                        {
                            panel11.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
                        }
                        scrollPane31.setViewportView(panel11);
                    }
                    panel10.add(scrollPane31, new GridBagConstraints(2, 7, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 0), 0, 0));
                }
                contentPanel3.add(panel10);
            }
            UserProfile.add(contentPanel3, BorderLayout.CENTER);

            //======== buttonBar3 ========
            {
                buttonBar3.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar3.setLayout(new GridBagLayout());
                ((GridBagLayout) buttonBar3.getLayout()).columnWidths = new int[]{0, 85, 80};
                ((GridBagLayout) buttonBar3.getLayout()).columnWeights = new double[]{1.0, 0.0, 0.0};

                //---- okButton3 ----
                okButton3.setText("\u0412\u042b\u0425\u041e\u0414");
                okButton3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        okButton3ActionPerformed(e);
                    }
                });
                buttonBar3.add(okButton3, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton3 ----
                cancelButton3.setText("\u0420\u0415\u0414\u0410\u041a\u0422\u0418\u0420\u041e\u0412\u0410\u0422\u042c \u041f\u0420\u041e\u0424\u0418\u041b\u042c");
                cancelButton3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cancelButton3ActionPerformed(e);
                    }
                });
                buttonBar3.add(cancelButton3, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
            }
            UserProfile.add(buttonBar3, BorderLayout.PAGE_END);
        }
        contentPane.add(UserProfile, BorderLayout.CENTER);

        //======== UserEdit ========
        {
            UserEdit.setBorder(new EmptyBorder(12, 12, 12, 12));
            UserEdit.setVisible(false);
            UserEdit.setLayout(new BorderLayout());

            //======== contentPanel2 ========
            {
                contentPanel2.setPreferredSize(new Dimension(470, 290));
                contentPanel2.setMinimumSize(new Dimension(470, 194));
                contentPanel2.setMaximumSize(new Dimension(470, 290));
                contentPanel2.setLayout(new BoxLayout(contentPanel2, BoxLayout.X_AXIS));

                //======== panel1 ========
                {
                    panel1.setLayout(new GridBagLayout());
                    ((GridBagLayout) panel1.getLayout()).columnWidths = new int[]{156, 0, 127, 0};
                    ((GridBagLayout) panel1.getLayout()).rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
                    ((GridBagLayout) panel1.getLayout()).columnWeights = new double[]{0.0, 0.0, 0.0, 1.0E-4};
                    ((GridBagLayout) panel1.getLayout()).rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

                    //---- button1 ----
                    button1.setText("\u0412\u044b\u0431\u0435\u0440\u0438\u0442\u0435 \u0444\u0430\u0439\u043b");
                    button1.setPreferredSize(new Dimension(10, 30));
                    button1.setMinimumSize(new Dimension(10, 30));
                    button1.setMaximumSize(new Dimension(140, 30));
                    button1.setIcon(null);
                    button1.setFont(new Font("Tahoma", Font.PLAIN, 16));
                    button1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            button1ActionPerformed(e);
                        }
                    });
                    panel1.add(button1, new GridBagConstraints(0, 6, 1, 2, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 0, 5), 0, 0));

                    //---- label3 ----
                    label3.setIcon(new ImageIcon(getClass().getResource("/core/data/images/no_avatar.png")));
                    label3.setMaximumSize(new Dimension(150, 150));
                    label3.setMinimumSize(new Dimension(150, 150));
                    label3.setPreferredSize(new Dimension(150, 150));
                    label3.setVerticalAlignment(SwingConstants.TOP);
                    label3.setVerticalTextPosition(SwingConstants.TOP);
                    panel1.add(label3, new GridBagConstraints(0, 0, 1, 7, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));

                    //---- label4 ----
                    label4.setText(" \u0412\u0430\u0448 E-mail:");
                    label4.setFont(new Font("Arial", Font.BOLD, 16));
                    label4.setVerticalAlignment(SwingConstants.TOP);
                    panel1.add(label4, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));

                    //======== scrollPane5 ========
                    {
                        scrollPane5.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                        scrollPane5.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

                        //---- editorPane2 ----
                        editorPane2.setText("Aleck94@gmail.com");
                        scrollPane5.setViewportView(editorPane2);
                    }
                    panel1.add(scrollPane5, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 0), 0, 0));

                    //---- label12 ----
                    label12.setText(" \u0421\u0442\u0430\u0440\u044b\u0439 \u043f\u0430\u0440\u043e\u043b\u044c:");
                    label12.setFont(new Font("Arial", Font.BOLD, 16));
                    label12.setVerticalAlignment(SwingConstants.TOP);
                    panel1.add(label12, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));

                    //======== scrollPane2 ========
                    {
                        scrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                        scrollPane2.setViewportBorder(null);
                        scrollPane2.setBorder(null);
                        scrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                        scrollPane2.setViewportView(passwordField1);
                    }
                    panel1.add(scrollPane2, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 0), 0, 0));

                    //---- label5 ----
                    label5.setText(" \u041d\u043e\u0432\u044b\u0439 \u043f\u0430\u0440\u043e\u043b\u044c:");
                    label5.setFont(new Font("Arial", Font.BOLD, 16));
                    label5.setVerticalAlignment(SwingConstants.TOP);
                    panel1.add(label5, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));

                    //======== scrollPane3 ========
                    {
                        scrollPane3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                        scrollPane3.setViewportBorder(null);
                        scrollPane3.setBorder(null);
                        scrollPane3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                        scrollPane3.setViewportView(passwordField2);
                    }
                    panel1.add(scrollPane3, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 0), 0, 0));

                    //---- label11 ----
                    label11.setText(" \u041f\u043e\u0434\u0442\u0432\u0435\u0440\u0434\u0438\u0442\u0435 \u043f\u0430\u0440\u043e\u043b\u044c:");
                    label11.setFont(new Font("Arial", Font.BOLD, 16));
                    label11.setVerticalAlignment(SwingConstants.TOP);
                    panel1.add(label11, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));

                    //======== scrollPane4 ========
                    {
                        scrollPane4.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                        scrollPane4.setViewportBorder(null);
                        scrollPane4.setBorder(null);
                        scrollPane4.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                        scrollPane4.setViewportView(passwordField3);
                    }
                    panel1.add(scrollPane4, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 0), 0, 0));

                    //---- label6 ----
                    label6.setText(" \u0418\u043c\u044f:");
                    label6.setFont(new Font("Arial", Font.BOLD, 16));
                    label6.setVerticalAlignment(SwingConstants.TOP);
                    panel1.add(label6, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));

                    //======== scrollPane6 ========
                    {
                        scrollPane6.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                        scrollPane6.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

                        //---- editorPane3 ----
                        editorPane3.setText("\u0410\u043b\u0435\u043a\u0441\u0430\u043d\u0434\u0440");
                        scrollPane6.setViewportView(editorPane3);
                    }
                    panel1.add(scrollPane6, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 0), 0, 0));

                    //---- label71 ----
                    label71.setText(" \u041e\u0442\u0447\u0435\u0441\u0442\u0432\u043e:");
                    label71.setFont(new Font("Arial", Font.BOLD, 16));
                    label71.setVerticalAlignment(SwingConstants.TOP);
                    panel1.add(label71, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));

                    //======== scrollPane8 ========
                    {
                        scrollPane8.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                        scrollPane8.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

                        //---- editorPane5 ----
                        editorPane5.setText("\u0420\u0443\u0441\u043b\u0430\u043d\u043e\u0432\u0438\u0447");
                        scrollPane8.setViewportView(editorPane5);
                    }
                    panel1.add(scrollPane8, new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 0), 0, 0));

                    //---- label7 ----
                    label7.setText(" \u0424\u0430\u043c\u0438\u043b\u0438\u044f:");
                    label7.setFont(new Font("Arial", Font.BOLD, 16));
                    label7.setVerticalAlignment(SwingConstants.TOP);
                    panel1.add(label7, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));

                    //======== scrollPane7 ========
                    {
                        scrollPane7.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                        scrollPane7.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

                        //---- editorPane4 ----
                        editorPane4.setText("\u0413\u043e\u043d\u0447\u0430\u0440\u0435\u043d\u043a\u043e");
                        scrollPane7.setViewportView(editorPane4);
                    }
                    panel1.add(scrollPane7, new GridBagConstraints(2, 6, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 0), 0, 0));

                    //---- label8 ----
                    label8.setText(" \u0414\u0430\u0442\u0430 \u0440\u043e\u0436\u0434\u0435\u043d\u0438\u044f:");
                    label8.setFont(new Font("Arial", Font.BOLD, 16));
                    label8.setVerticalAlignment(SwingConstants.TOP);
                    panel1.add(label8, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 0, 5), 0, 0));

                    //======== scrollPane9 ========
                    {
                        scrollPane9.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                        scrollPane9.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                        scrollPane9.setBorder(null);

                        //======== panel2 ========
                        {
                            panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

                            //======== scrollPane11 ========
                            {
                                scrollPane11.setMaximumSize(new Dimension(30, 32767));
                                scrollPane11.setPreferredSize(new Dimension(30, 22));
                                scrollPane11.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                                scrollPane11.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

                                //---- editorPane7 ----
                                editorPane7.setMaximumSize(new Dimension(20, 2147483647));
                                editorPane7.setMinimumSize(new Dimension(40, 20));
                                editorPane7.setText("24");
                                scrollPane11.setViewportView(editorPane7);
                            }
                            panel2.add(scrollPane11);

                            //---- label9 ----
                            label9.setText(" . ");
                            panel2.add(label9);

                            //======== scrollPane12 ========
                            {
                                scrollPane12.setMaximumSize(new Dimension(30, 32767));
                                scrollPane12.setPreferredSize(new Dimension(30, 22));
                                scrollPane12.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                                scrollPane12.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

                                //---- editorPane8 ----
                                editorPane8.setMaximumSize(new Dimension(20, 2147483647));
                                editorPane8.setMinimumSize(new Dimension(40, 20));
                                editorPane8.setText("07");
                                scrollPane12.setViewportView(editorPane8);
                            }
                            panel2.add(scrollPane12);

                            //---- label10 ----
                            label10.setText(" . ");
                            panel2.add(label10);

                            //======== scrollPane13 ========
                            {
                                scrollPane13.setMaximumSize(new Dimension(40, 32767));
                                scrollPane13.setPreferredSize(new Dimension(40, 22));
                                scrollPane13.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                                scrollPane13.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

                                //---- editorPane9 ----
                                editorPane9.setMaximumSize(new Dimension(20, 2147483647));
                                editorPane9.setMinimumSize(new Dimension(40, 20));
                                editorPane9.setText("1995");
                                scrollPane13.setViewportView(editorPane9);
                            }
                            panel2.add(scrollPane13);
                        }
                        scrollPane9.setViewportView(panel2);
                    }
                    panel1.add(scrollPane9, new GridBagConstraints(2, 7, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 0, 0), 0, 0));
                }
                contentPanel2.add(panel1);
            }
            UserEdit.add(contentPanel2, BorderLayout.CENTER);

            //======== buttonBar2 ========
            {
                buttonBar2.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar2.setLayout(new GridBagLayout());
                ((GridBagLayout) buttonBar2.getLayout()).columnWidths = new int[]{0, 85, 80};
                ((GridBagLayout) buttonBar2.getLayout()).columnWeights = new double[]{1.0, 0.0, 0.0};

                //---- okButton2 ----
                okButton2.setText("\u041e\u041a");
                okButton2.setActionCommand("\u041e\u041a");
                okButton2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        okButton2ActionPerformed(e);
                    }
                });
                buttonBar2.add(okButton2, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton2 ----
                cancelButton2.setText("\u041e\u0422\u041c\u0415\u041d\u0410");
                cancelButton2.setActionCommand("\u041e\u0422\u041c\u0415\u041d\u0410");
                cancelButton2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cancelButton2ActionPerformed(e);
                    }
                });
                buttonBar2.add(cancelButton2, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
            }
            UserEdit.add(buttonBar2, BorderLayout.PAGE_END);
        }
        contentPane.add(UserEdit, BorderLayout.NORTH);
        pack();
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel UserProfile;
    private JPanel contentPanel3;
    private JPanel panel10;
    private JLabel label33;
    private JLabel label35;
    private JLabel label41;
    private JScrollPane scrollPane25;
    private JScrollPane scrollPane29;
    private JLabel label2;
    private JLabel label34;
    private JScrollPane scrollPane26;
    private JScrollPane scrollPane24;
    private JLabel label1;
    private JScrollPane scrollPane27;
    private JScrollPane scrollPane28;
    private JScrollPane scrollPane30;
    private JScrollPane scrollPane31;
    private JPanel panel11;
    private JPanel buttonBar3;
    private JButton okButton3;
    private JButton cancelButton3;
    private JPanel UserEdit;
    private JPanel contentPanel2;
    private JPanel panel1;
    private JButton button1;
    private JLabel label3;
    private JLabel label4;
    private JScrollPane scrollPane5;
    private JEditorPane editorPane2;
    private JLabel label12;
    private JScrollPane scrollPane2;
    private JPasswordField passwordField1;
    private JLabel label5;
    private JScrollPane scrollPane3;
    private JPasswordField passwordField2;
    private JLabel label11;
    private JScrollPane scrollPane4;
    private JPasswordField passwordField3;
    private JLabel label6;
    private JScrollPane scrollPane6;
    private JEditorPane editorPane3;
    private JLabel label71;
    private JScrollPane scrollPane8;
    private JEditorPane editorPane5;
    private JLabel label7;
    private JScrollPane scrollPane7;
    private JEditorPane editorPane4;
    private JLabel label8;
    private JScrollPane scrollPane9;
    private JPanel panel2;
    private JScrollPane scrollPane11;
    private JEditorPane editorPane7;
    private JLabel label9;
    private JScrollPane scrollPane12;
    private JEditorPane editorPane8;
    private JLabel label10;
    private JScrollPane scrollPane13;
    private JEditorPane editorPane9;
    private JPanel buttonBar2;
    private JButton okButton2;
    private JButton cancelButton2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
