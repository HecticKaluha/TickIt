package JMS.developer;

import JMS.broker.BrokerDeveloperGateway;
import domain.Ticket;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JavaFrame extends JFrame implements IDeveloperFrame {

    private Dimension windowSize = getSize();
    private JPanel contentPane;
    private JTextField tfReply;
    private DefaultListModel<Ticket> listModel = new DefaultListModel<Ticket>();
    private DeveloperBrokerGateway developerBrokerGateway;

    public static void main(String[] args) {
        EventQueue.invokeLater((new Runnable() {
            public void run() {
                try {
                    JavaFrame frame = new JavaFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }));
    }

    public JavaFrame() {
        developerBrokerGateway = new DeveloperBrokerGateway("Java");

        setTitle("Developer - Java");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(1300, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{46, 31, 86, 30, 89, 0};
        gbl_contentPane.rowHeights = new int[]{233, 23, 0};
        gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);

        JScrollPane scrollPane = new JScrollPane();
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.gridwidth = 5;
        gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 0;
        contentPane.add(scrollPane, gbc_scrollPane);

        JList<Ticket> list = new JList<Ticket>(listModel);
        scrollPane.setViewportView(list);

        developerBrokerGateway.setDeveloperFrame(this);

        JButton btnSendReply = new JButton("Finish ticket");
        btnSendReply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Ticket ticket = list.getSelectedValue();
                ticket.setFinished(true);
                developerBrokerGateway.sendReply(ticket);
                list.repaint();
            }
        });
        GridBagConstraints gbc_btnSendReply = new GridBagConstraints();
        gbc_btnSendReply.anchor = GridBagConstraints.NORTHWEST;
        gbc_btnSendReply.gridx = 4;
        gbc_btnSendReply.gridy = 1;
        contentPane.add(btnSendReply, gbc_btnSendReply);
    }

    @Override
    public void add(Ticket ticket) {
        listModel.addElement(ticket);
    }
}
