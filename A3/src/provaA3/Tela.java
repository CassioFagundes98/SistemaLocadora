package provaA3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Panel;
import java.awt.Color;
import java.awt.Label;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.border.MatteBorder;

import provaA3.ConectaBanco;

import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class Tela extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPromoes;
	private ConectaBanco banco;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela frame = new Tela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public Tela() {
		banco = new ConectaBanco();

		setTitle("Thenny video");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 556, 419);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 191, 255));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Cliente");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Clientes cad_clientes = new Clientes();
				cad_clientes.setVisible(true);

			}
		});
		btnNewButton.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		btnNewButton.setBounds(276, 328, 123, 41);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setIcon(new ImageIcon(
				"C:\\Users\\Usuario\\Downloads\\Figura-9-Diagrama-de-Classes-do-comercio-eletronico-Considerando-a-caracteristica.png"));
		btnNewButton_1.setBounds(10, 11, 58, 41);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Filmes");
		btnNewButton_2.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Filmes cad_filmes = new Filmes();
				cad_filmes.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(10, 328, 123, 41);
		contentPane.add(btnNewButton_2);

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBounds(10, 63, 520, 254);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnNewButton_5 = new JButton("New button");
		btnNewButton_5.setBounds(10, 11, 114, 232);
		panel.add(btnNewButton_5);

		JButton btnNewButton_6 = new JButton("New button");
		btnNewButton_6.setBounds(134, 11, 119, 232);
		panel.add(btnNewButton_6);

		JButton btnNewButton_7 = new JButton("New button");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_7.setBounds(267, 11, 119, 232);
		panel.add(btnNewButton_7);

		JButton btnNewButton_8 = new JButton("New button");
		btnNewButton_8.setBounds(396, 11, 114, 232);
		panel.add(btnNewButton_8);

		JButton btnNewButton_3 = new JButton("Funcionarios");
		btnNewButton_3.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Funcionario cad_funcionarios = new Funcionario();
				cad_funcionarios.setVisible(true);

			}
		});
		btnNewButton_3.setBounds(143, 328, 123, 41);
		contentPane.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("Carrinho");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Carrinho cad_carrinho = new Carrinho();
				cad_carrinho.setVisible(true);

			}
		});
		btnNewButton_4.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		btnNewButton_4.setBounds(409, 328, 115, 41);
		contentPane.add(btnNewButton_4);

		txtPromoes = new JTextField();
		txtPromoes.setHorizontalAlignment(SwingConstants.CENTER);
		txtPromoes.setBackground(new Color(52, 131, 173));
		txtPromoes.setForeground(Color.BLACK);
		txtPromoes.setFont(new Font("Stencil", Font.ITALIC, 29));
		txtPromoes.setText("Promoções");
		txtPromoes.setBounds(78, 8, 390, 41);
		contentPane.add(txtPromoes);
		txtPromoes.setColumns(10);
	}
}
