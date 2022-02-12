import myutils.XxxxUtils;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DecryptoXxxx {
    private XxxxUtils utils;
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTextField textField_beyound_key;
    private JTextArea textArea_beyound_secret;
    private JButton button_beyound_decrypto;
    private JTextArea textArea_beyound_text;
    private JButton button_beyound_clean;
    private JTextField textField_beyound_notice;
    private JTextField textField_godzilla_key;
    private JTextField textField_godzilla_pwd;
    private JTextArea textArea_godzilla_secret;
    private JButton button_godzilladecrypto;
    private JButton button_godzilla_clean;
    private JTextArea textArea_godzilla_text;
    private JTextField textField_godzilla_notice;

    public DecryptoXxxx() {
        button_beyound_decrypto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textField_beyound_notice.setText("");
                utils = new XxxxUtils();
                String key = textField_beyound_key.getText().trim();
                String secret = textArea_beyound_secret.getText().trim();
                String result = "";
                try {
                    result = utils.deCrytoforbehinderClienttoServer(key, secret);
                    System.out.println("当成请求流量处理");
                    textField_beyound_notice.setText("此流量为客户端请求连接流量");

                } catch (Exception ex) {
                    System.out.println("当成响应流量处理");
                    try {
                        result = utils.deCrytoforbehinderServertoClient(key, secret);
                        textField_beyound_notice.setText("此流量为服务端执行命令后的响应流量");
                    } catch (Exception exc) {
                        System.out.println("既不是请求流量也不是响应流量");
                        result = "既不是请求流量也不是响应流量，流量错误或密钥错误";
                        textField_beyound_notice.setText("请确认流量正确，密码密钥存在切正确");
                        exc.printStackTrace();
                    }
                }
                textArea_beyound_text.setText(result);
                super.mouseClicked(e);
            }
        });
        button_beyound_clean.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textField_beyound_notice.setText("");
                textArea_beyound_text.setText("");
                textArea_beyound_secret.setText("");
                super.mouseClicked(e);
            }
        });
        button_godzilladecrypto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textField_godzilla_notice.setText("");
                utils = new XxxxUtils();
                String key = textField_godzilla_key.getText().trim();
                String pwd = textField_godzilla_pwd.getText().trim();
                String secret = textArea_godzilla_secret.getText().trim();
                String result = "";

                try {
                    result = utils.deCryptoforGodzillaClienttoServer(secret, key);
                    textField_godzilla_notice.setText("此流量为哥斯拉请求流量");
                } catch (Exception ex) {
                    try {
                        result = utils.deCryptoforGodzillaServertoclient(secret, key, pwd);
                        textField_godzilla_notice.setText("此流量为哥斯拉响应流量");
                        if (result.equals("请输入正确的密码和密钥参数")) {
                            result = "既不是请求流量也不是响应流量，流量错误或密钥错误";
                        }
//                        ex.printStackTrace();
                    } catch (Exception exc) {
                        System.out.println("既不是请求流量也不是响应流量");
                        result = "既不是请求流量也不是响应流量，流量错误或密钥错误";
                        textField_godzilla_notice.setText("请确认流量正确，密码密钥存在切正确");
//                        exc.printStackTrace();
                    }

                }
                textArea_godzilla_text.setText(result);
                super.mouseClicked(e);
            }
        });
        button_godzilla_clean.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textField_godzilla_notice.setText("");
                textArea_godzilla_text.setText("");
                textArea_godzilla_secret.setText("");
                super.mouseClicked(e);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("JWebshellDecoder by ga0weI");
        frame.setContentPane(new DecryptoXxxx().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800, 1000);
        frame.setResizable(false);
    }


}
