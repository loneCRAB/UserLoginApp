/*
 * Created by JFormDesigner on Sun May 04 09:42:33 EEST 2014
 */

package app;

import core.dao.UserDAO;
import core.model.Util.DateValidator;
import core.model.Util.EmailValidator;
import core.login.Util.IOToken;
import core.model.User;
import core.login.UserToken;

import javax.crypto.NoSuchPaddingException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @author Alex
 */
public class Login extends JFrame {
    public Login() {
        initComponents();
    }

    private User user = new User();

    private void button3ActionPerformed(ActionEvent e) {
        if (textField3.getText().equals("") && passwordField4.getText().equals(""))
            textField3.setText("LOGIN IS MISSED");
        else {
            User user = core.login.Login.getLogin(textField3.getText(), passwordField4.getText());
            if (user != null) {
                if (checkBox1.isSelected()) {
                    UserToken token = new UserToken();
                    token.setLogin(user.getMail());
                    token.setPassword(user.getPassword());
                    IOToken writer = new IOToken();
                    try {
                        writer.writeObjects(token);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (NoSuchPaddingException e1) {
                        e1.printStackTrace();
                    } catch (NoSuchAlgorithmException e1) {
                        e1.printStackTrace();
                    }
                }
                Profile p = new Profile();
                p.setUser(user);
                this.dispose();
            } else label24.setText("FAILED");
        }
    }

    private void label28MouseClicked(MouseEvent e) {
        Login.setVisible(false);
        UserRegistration.setVisible(true);
        this.setSize(510, 390);
    }

    private void canceButton2ActionPerformed(ActionEvent e) {
        UserRegistration.setVisible(false);
        Login.setVisible(true);
        this.setSize(416, 239);
    }

    private void thisWindowClosing(WindowEvent e) {
        UserDAO.getInstance().close();
    }

    private void oklButton2ActionPerformed(ActionEvent e) {
        if (!editorPane6.getText().equals("") && !passwordField5.getText().equals("") && !editorPane10.getText().equals("")
                && !editorPane11.getText().equals("") && !editorPane12.getText().equals("") && !editorPane13.getText().equals("")
                && !editorPane14.getText().equals("") && !editorPane15.getText().equals("")) {
            DateValidator dateValidator = new DateValidator();
            String date = editorPane13.getText() + " " + editorPane14.getText() + " " + editorPane15.getText();

            EmailValidator emailValidator = new EmailValidator();
            if (emailValidator.validate(editorPane6.getText())) {
                if (dateValidator.dateValid(date)) {
                    if (passwordField5.getText().equals(passwordField6.getText())) {
                        user.setMail(editorPane6.getText());
                        user.setPassword(passwordField5.getText());
                        user.setName(editorPane10.getText());
                        user.setFirstName(editorPane11.getText());
                        user.setSurname(editorPane12.getText());
                        user.setStringBirthday(date);
                        UserDAO.getInstance().create(user);
                        JOptionPane.showMessageDialog(this, "Вы успешно зарегестрированы на сайте под логином " + user.getMail());
                        UserRegistration.setVisible(false);
                        Login.setVisible(true);
                        this.setSize(416, 239);
                    } else
                        JOptionPane.showMessageDialog(this, "Пароли не совпадают");
                } else
                    JOptionPane.showMessageDialog(this, "Введена неправильная дата");
            } else
                JOptionPane.showMessageDialog(this, "Введен неправильный E-mail");
        } else
            JOptionPane.showMessageDialog(this, "Не все поля заполнены!");
    }

    private void thisWindowClosed(WindowEvent e) {
        // TODO add your code here
    }

    private void button2ActionPerformed(ActionEvent e) {

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
                UUID id = UUID.randomUUID();
                String fileName = id.toString().replaceAll("-", "");
                ImageIO.write(scaled, "PNG", new File(System.getProperty("user.dir") + "/src/core/data/images/" + fileName + ".png"));
                user.setPhoto(fileName);
                label2.setIcon(new ImageIcon(scaled));
            } catch (IOException e1) {
            }
        }

    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        Login = new JPanel();
        panel5 = new JPanel();
        label14 = new JLabel();
        textField3 = new JTextField();
        panel6 = new JPanel();
        label23 = new JLabel();
        passwordField4 = new JPasswordField();
        panel7 = new JPanel();
        label24 = new JLabel();
        button3 = new JButton();
        checkBox1 = new JCheckBox();
        panel8 = new JPanel();
        label25 = new JLabel();
        panel9 = new JPanel();
        label26 = new JLabel();
        label27 = new JLabel();
        label28 = new JLabel();
        label32 = new JLabel();
        UserRegistration = new JPanel();
        contentPanel2 = new JPanel();
        panel3 = new JPanel();
        button2 = new JButton();
        label2 = new JLabel();
        label13 = new JLabel();
        scrollPane10 = new JScrollPane();
        editorPane6 = new JEditorPane();
        scrollPane14 = new JScrollPane();
        label15 = new JLabel();
        scrollPane15 = new JScrollPane();
        passwordField5 = new JPasswordField();
        label16 = new JLabel();
        scrollPane16 = new JScrollPane();
        passwordField6 = new JPasswordField();
        label17 = new JLabel();
        scrollPane17 = new JScrollPane();
        editorPane10 = new JEditorPane();
        label18 = new JLabel();
        scrollPane18 = new JScrollPane();
        editorPane11 = new JEditorPane();
        label19 = new JLabel();
        scrollPane19 = new JScrollPane();
        editorPane12 = new JEditorPane();
        label20 = new JLabel();
        scrollPane20 = new JScrollPane();
        panel4 = new JPanel();
        scrollPane21 = new JScrollPane();
        editorPane13 = new JEditorPane();
        label21 = new JLabel();
        scrollPane22 = new JScrollPane();
        editorPane14 = new JEditorPane();
        label22 = new JLabel();
        scrollPane23 = new JScrollPane();
        editorPane15 = new JEditorPane();
        buttonBar2 = new JPanel();
        cancelButton2 = new JButton();
        oklButton2 = new JButton();

        //======== this ========
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                thisWindowClosed(e);
            }

            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== Login ========
        {
            Login.setMaximumSize(new Dimension(400, 200));
            Login.setPreferredSize(new Dimension(400, 200));
            Login.setFont(Login.getFont().deriveFont(Login.getFont().getStyle() & ~Font.BOLD));
            Login.setLayout(new BoxLayout(Login, BoxLayout.PAGE_AXIS));

            //======== panel5 ========
            {
                panel5.setMaximumSize(new Dimension(400, 40));
                panel5.setMinimumSize(new Dimension(400, 40));
                panel5.setPreferredSize(new Dimension(400, 40));
                panel5.setLayout(new FlowLayout(FlowLayout.LEFT));
                ((FlowLayout) panel5.getLayout()).setAlignOnBaseline(true);

                //---- label14 ----
                label14.setText("\u041b\u043e\u0433\u0438\u043d");
                label14.setFont(new Font("Arial", Font.PLAIN, 16));
                label14.setPreferredSize(new Dimension(70, 19));
                panel5.add(label14);

                //---- textField3 ----
                textField3.setPreferredSize(new Dimension(310, 30));
                textField3.setMaximumSize(new Dimension(310, 30));
                textField3.setMinimumSize(new Dimension(200, 30));
                textField3.setFont(new Font("Arial", Font.PLAIN, 16));
                panel5.add(textField3);
            }
            Login.add(panel5);

            //======== panel6 ========
            {
                panel6.setPreferredSize(new Dimension(400, 40));
                panel6.setMaximumSize(new Dimension(400, 40));
                panel6.setMinimumSize(new Dimension(400, 40));
                panel6.setLayout(new FlowLayout(FlowLayout.LEFT));
                ((FlowLayout) panel6.getLayout()).setAlignOnBaseline(true);

                //---- label23 ----
                label23.setText("\u041f\u0430\u0440\u043e\u043b\u044c");
                label23.setFont(new Font("Arial", Font.PLAIN, 16));
                label23.setPreferredSize(new Dimension(70, 19));
                label23.setMaximumSize(new Dimension(35, 19));
                panel6.add(label23);

                //---- passwordField4 ----
                passwordField4.setPreferredSize(new Dimension(310, 30));
                passwordField4.setMinimumSize(new Dimension(310, 30));
                passwordField4.setMaximumSize(new Dimension(310, 30));
                panel6.add(passwordField4);
            }
            Login.add(panel6);

            //======== panel7 ========
            {
                panel7.setPreferredSize(new Dimension(400, 60));
                panel7.setMinimumSize(new Dimension(400, 60));
                panel7.setMaximumSize(new Dimension(400, 60));
                panel7.setLayout(new FlowLayout(FlowLayout.LEFT));

                //---- label24 ----
                label24.setFont(new Font("Arial", Font.PLAIN, 11));
                label24.setPreferredSize(new Dimension(70, 19));
                label24.setMaximumSize(new Dimension(35, 19));
                label24.setBackground(new Color(255, 0, 51));
                label24.setForeground(Color.red);
                panel7.add(label24);

                //---- button3 ----
                button3.setText("\u0412\u0445\u043e\u0434");
                button3.setMaximumSize(new Dimension(70, 50));
                button3.setMinimumSize(new Dimension(70, 50));
                button3.setPreferredSize(new Dimension(70, 50));
                button3.setAlignmentY(0.0F);
                button3.setFont(new Font("Arial", Font.PLAIN, 16));
                button3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        button3ActionPerformed(e);
                    }
                });
                panel7.add(button3);

                //---- checkBox1 ----
                checkBox1.setText("\u041e\u0441\u0442\u0430\u0432\u0430\u0442\u0441\u044f \u0432 \u0441\u0438\u0441\u0442\u0435\u043c\u0435");
                checkBox1.setFont(new Font("Arial", Font.PLAIN, 14));
                panel7.add(checkBox1);
            }
            Login.add(panel7);

            //======== panel8 ========
            {
                panel8.setPreferredSize(new Dimension(400, 29));
                panel8.setMinimumSize(new Dimension(400, 24));
                panel8.setMaximumSize(new Dimension(400, 32767));
                panel8.setLayout(new FlowLayout(FlowLayout.LEFT));

                //---- label25 ----
                label25.setFont(new Font("Arial", Font.PLAIN, 16));
                label25.setPreferredSize(new Dimension(70, 50));
                label25.setMaximumSize(new Dimension(35, 19));
                panel8.add(label25);

                //======== panel9 ========
                {
                    panel9.setPreferredSize(new Dimension(310, 50));
                    panel9.setMinimumSize(new Dimension(310, 14));
                    panel9.setMaximumSize(new Dimension(310, 32767));
                    panel9.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

                    //---- label26 ----
                    label26.setMaximumSize(new Dimension(310, 14));
                    label26.setMinimumSize(new Dimension(310, 14));
                    label26.setPreferredSize(new Dimension(310, 14));
                    label26.setHorizontalAlignment(SwingConstants.LEFT);
                    label26.setVerticalTextPosition(SwingConstants.TOP);
                    label26.setVerticalAlignment(SwingConstants.TOP);
                    label26.setText("\u0415\u0441\u043b\u0438 \u0443 \u0412\u0430\u0441 \u043d\u0435\u0442\u0443 \u0441\u043e\u0431\u0441\u0442\u0432\u0435\u043d\u043d\u043e\u0433\u043e \u0430\u043a\u043a\u0430\u0443\u043d\u0442\u0430, \u0440\u0435\u043a\u043e\u043c\u0435\u043d\u0434\u0443\u0435\u043c \u0412\u0430\u043c");
                    label26.setFont(new Font("Arial", Font.PLAIN, 11));
                    panel9.add(label26);

                    //---- label27 ----
                    label27.setText("\u043f\u0440\u043e\u0442\u0438 \u043f\u0440\u043e\u0446\u0435\u0441\u0441 ");
                    label27.setFont(new Font("Arial", Font.PLAIN, 11));
                    panel9.add(label27);

                    //---- label28 ----
                    label28.setText("\u0440\u0435\u0433\u0438\u0441\u0442\u0440\u0430\u0446\u0438\u0438");
                    label28.setForeground(new Color(255, 51, 51));
                    label28.setFont(new Font("Arial", Font.PLAIN, 11));
                    label28.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    label28.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            label28MouseClicked(e);
                        }
                    });
                    panel9.add(label28);

                    //---- label32 ----
                    label32.setText(".");
                    label32.setFont(new Font("Arial", Font.PLAIN, 11));
                    panel9.add(label32);
                }
                panel8.add(panel9);
            }
            Login.add(panel8);
        }
        contentPane.add(Login, BorderLayout.CENTER);

        //======== UserRegistration ========
        {
            UserRegistration.setBorder(new EmptyBorder(12, 12, 12, 12));
            UserRegistration.setVisible(false);
            UserRegistration.setLayout(new BorderLayout());

            //======== contentPanel2 ========
            {
                contentPanel2.setPreferredSize(new Dimension(470, 290));
                contentPanel2.setMinimumSize(new Dimension(470, 194));
                contentPanel2.setMaximumSize(new Dimension(470, 290));
                contentPanel2.setLayout(new BoxLayout(contentPanel2, BoxLayout.X_AXIS));

                //======== panel3 ========
                {
                    panel3.setLayout(new GridBagLayout());
                    ((GridBagLayout) panel3.getLayout()).columnWidths = new int[]{156, 0, 127, 0};
                    ((GridBagLayout) panel3.getLayout()).rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
                    ((GridBagLayout) panel3.getLayout()).columnWeights = new double[]{0.0, 0.0, 0.0, 1.0E-4};
                    ((GridBagLayout) panel3.getLayout()).rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

                    //---- button2 ----
                    button2.setText("\u0412\u044b\u0431\u0435\u0440\u0438\u0442\u0435 \u0444\u0430\u0439\u043b");
                    button2.setPreferredSize(new Dimension(10, 30));
                    button2.setMinimumSize(new Dimension(10, 30));
                    button2.setMaximumSize(new Dimension(140, 30));
                    button2.setIcon(null);
                    button2.setFont(new Font("Tahoma", Font.PLAIN, 16));
                    button2.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            button2ActionPerformed(e);
                        }
                    });
                    panel3.add(button2, new GridBagConstraints(0, 6, 1, 2, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 0, 5), 0, 0));

                    //---- label2 ----
                    label2.setIcon(new ImageIcon(getClass().getResource("/core/data/images/no_avatar.png")));
                    label2.setMaximumSize(new Dimension(150, 150));
                    label2.setMinimumSize(new Dimension(150, 150));
                    label2.setPreferredSize(new Dimension(150, 150));
                    label2.setVerticalAlignment(SwingConstants.TOP);
                    label2.setVerticalTextPosition(SwingConstants.TOP);
                    panel3.add(label2, new GridBagConstraints(0, 0, 1, 7, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));

                    //---- label13 ----
                    label13.setText(" \u0412\u0430\u0448 E-mail:");
                    label13.setFont(new Font("Arial", Font.BOLD, 16));
                    label13.setVerticalAlignment(SwingConstants.TOP);
                    panel3.add(label13, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));

                    //======== scrollPane10 ========
                    {
                        scrollPane10.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                        scrollPane10.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                        scrollPane10.setViewportView(editorPane6);
                    }
                    panel3.add(scrollPane10, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 0), 0, 0));

                    //======== scrollPane14 ========
                    {
                        scrollPane14.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                        scrollPane14.setViewportBorder(null);
                        scrollPane14.setBorder(null);
                        scrollPane14.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                    }
                    panel3.add(scrollPane14, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 0), 0, 0));

                    //---- label15 ----
                    label15.setText(" \u041d\u043e\u0432\u044b\u0439 \u043f\u0430\u0440\u043e\u043b\u044c:");
                    label15.setFont(new Font("Arial", Font.BOLD, 16));
                    label15.setVerticalAlignment(SwingConstants.TOP);
                    panel3.add(label15, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));

                    //======== scrollPane15 ========
                    {
                        scrollPane15.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                        scrollPane15.setViewportBorder(null);
                        scrollPane15.setBorder(null);
                        scrollPane15.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                        scrollPane15.setViewportView(passwordField5);
                    }
                    panel3.add(scrollPane15, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 0), 0, 0));

                    //---- label16 ----
                    label16.setText(" \u041f\u043e\u0434\u0442\u0432\u0435\u0440\u0434\u0438\u0442\u0435 \u043f\u0430\u0440\u043e\u043b\u044c:");
                    label16.setFont(new Font("Arial", Font.BOLD, 16));
                    label16.setVerticalAlignment(SwingConstants.TOP);
                    panel3.add(label16, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));

                    //======== scrollPane16 ========
                    {
                        scrollPane16.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                        scrollPane16.setViewportBorder(null);
                        scrollPane16.setBorder(null);
                        scrollPane16.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                        scrollPane16.setViewportView(passwordField6);
                    }
                    panel3.add(scrollPane16, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 0), 0, 0));

                    //---- label17 ----
                    label17.setText(" \u0418\u043c\u044f:");
                    label17.setFont(new Font("Arial", Font.BOLD, 16));
                    label17.setVerticalAlignment(SwingConstants.TOP);
                    panel3.add(label17, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));

                    //======== scrollPane17 ========
                    {
                        scrollPane17.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                        scrollPane17.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                        scrollPane17.setViewportView(editorPane10);
                    }
                    panel3.add(scrollPane17, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 0), 0, 0));

                    //---- label18 ----
                    label18.setText(" \u041e\u0442\u0447\u0435\u0441\u0442\u0432\u043e:");
                    label18.setFont(new Font("Arial", Font.BOLD, 16));
                    label18.setVerticalAlignment(SwingConstants.TOP);
                    panel3.add(label18, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));

                    //======== scrollPane18 ========
                    {
                        scrollPane18.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                        scrollPane18.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                        scrollPane18.setViewportView(editorPane11);
                    }
                    panel3.add(scrollPane18, new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 0), 0, 0));

                    //---- label19 ----
                    label19.setText(" \u0424\u0430\u043c\u0438\u043b\u0438\u044f:");
                    label19.setFont(new Font("Arial", Font.BOLD, 16));
                    label19.setVerticalAlignment(SwingConstants.TOP);
                    panel3.add(label19, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));

                    //======== scrollPane19 ========
                    {
                        scrollPane19.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                        scrollPane19.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                        scrollPane19.setViewportView(editorPane12);
                    }
                    panel3.add(scrollPane19, new GridBagConstraints(2, 6, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 0), 0, 0));

                    //---- label20 ----
                    label20.setText(" \u0414\u0430\u0442\u0430 \u0440\u043e\u0436\u0434\u0435\u043d\u0438\u044f:");
                    label20.setFont(new Font("Arial", Font.BOLD, 16));
                    label20.setVerticalAlignment(SwingConstants.TOP);
                    panel3.add(label20, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 0, 5), 0, 0));

                    //======== scrollPane20 ========
                    {
                        scrollPane20.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                        scrollPane20.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                        scrollPane20.setBorder(null);

                        //======== panel4 ========
                        {
                            panel4.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

                            //======== scrollPane21 ========
                            {
                                scrollPane21.setMaximumSize(new Dimension(30, 32767));
                                scrollPane21.setPreferredSize(new Dimension(30, 22));
                                scrollPane21.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                                scrollPane21.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

                                //---- editorPane13 ----
                                editorPane13.setMaximumSize(new Dimension(20, 2147483647));
                                editorPane13.setMinimumSize(new Dimension(40, 20));
                                scrollPane21.setViewportView(editorPane13);
                            }
                            panel4.add(scrollPane21);

                            //---- label21 ----
                            label21.setText(" . ");
                            panel4.add(label21);

                            //======== scrollPane22 ========
                            {
                                scrollPane22.setMaximumSize(new Dimension(30, 32767));
                                scrollPane22.setPreferredSize(new Dimension(30, 22));
                                scrollPane22.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                                scrollPane22.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

                                //---- editorPane14 ----
                                editorPane14.setMaximumSize(new Dimension(20, 2147483647));
                                editorPane14.setMinimumSize(new Dimension(40, 20));
                                scrollPane22.setViewportView(editorPane14);
                            }
                            panel4.add(scrollPane22);

                            //---- label22 ----
                            label22.setText(" . ");
                            panel4.add(label22);

                            //======== scrollPane23 ========
                            {
                                scrollPane23.setMaximumSize(new Dimension(40, 32767));
                                scrollPane23.setPreferredSize(new Dimension(40, 22));
                                scrollPane23.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                                scrollPane23.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

                                //---- editorPane15 ----
                                editorPane15.setMaximumSize(new Dimension(20, 2147483647));
                                editorPane15.setMinimumSize(new Dimension(40, 20));
                                scrollPane23.setViewportView(editorPane15);
                            }
                            panel4.add(scrollPane23);
                        }
                        scrollPane20.setViewportView(panel4);
                    }
                    panel3.add(scrollPane20, new GridBagConstraints(2, 7, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 0, 0), 0, 0));
                }
                contentPanel2.add(panel3);
            }
            UserRegistration.add(contentPanel2, BorderLayout.CENTER);

            //======== buttonBar2 ========
            {
                buttonBar2.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar2.setLayout(new GridBagLayout());
                ((GridBagLayout) buttonBar2.getLayout()).columnWidths = new int[]{0, 85, 80};
                ((GridBagLayout) buttonBar2.getLayout()).columnWeights = new double[]{1.0, 0.0, 0.0};

                //---- cancelButton2 ----
                cancelButton2.setText("\u041e\u0422\u041c\u0415\u041d\u0410");
                cancelButton2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        canceButton2ActionPerformed(e);
                    }
                });
                buttonBar2.add(cancelButton2, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 5), 0, 0));

                //---- oklButton2 ----
                oklButton2.setText("\u0417\u0410\u0420\u0415\u0413\u0418\u0421\u0422\u0420\u0418\u0420\u041e\u0412\u0410\u0422\u0421\u042f");
                oklButton2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        oklButton2ActionPerformed(e);
                    }
                });
                buttonBar2.add(oklButton2, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
            }
            UserRegistration.add(buttonBar2, BorderLayout.PAGE_END);
        }
        contentPane.add(UserRegistration, BorderLayout.NORTH);
        pack();
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel Login;
    private JPanel panel5;
    private JLabel label14;
    private JTextField textField3;
    private JPanel panel6;
    private JLabel label23;
    private JPasswordField passwordField4;
    private JPanel panel7;
    private JLabel label24;
    private JButton button3;
    private JCheckBox checkBox1;
    private JPanel panel8;
    private JLabel label25;
    private JPanel panel9;
    private JLabel label26;
    private JLabel label27;
    private JLabel label28;
    private JLabel label32;
    private JPanel UserRegistration;
    private JPanel contentPanel2;
    private JPanel panel3;
    private JButton button2;
    private JLabel label2;
    private JLabel label13;
    private JScrollPane scrollPane10;
    private JEditorPane editorPane6;
    private JScrollPane scrollPane14;
    private JLabel label15;
    private JScrollPane scrollPane15;
    private JPasswordField passwordField5;
    private JLabel label16;
    private JScrollPane scrollPane16;
    private JPasswordField passwordField6;
    private JLabel label17;
    private JScrollPane scrollPane17;
    private JEditorPane editorPane10;
    private JLabel label18;
    private JScrollPane scrollPane18;
    private JEditorPane editorPane11;
    private JLabel label19;
    private JScrollPane scrollPane19;
    private JEditorPane editorPane12;
    private JLabel label20;
    private JScrollPane scrollPane20;
    private JPanel panel4;
    private JScrollPane scrollPane21;
    private JEditorPane editorPane13;
    private JLabel label21;
    private JScrollPane scrollPane22;
    private JEditorPane editorPane14;
    private JLabel label22;
    private JScrollPane scrollPane23;
    private JEditorPane editorPane15;
    private JPanel buttonBar2;
    private JButton cancelButton2;
    private JButton oklButton2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
