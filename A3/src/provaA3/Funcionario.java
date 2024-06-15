package provaA3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Funcionario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Funcionario frame = new Funcionario();
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
	public Funcionario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenuItem mntmNewMenuItem = new JMenuItem("Voltar");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();

			}
		});
		menuBar.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Filmes");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String sql = "SELECT * FROM filmes";
				ConectaBanco factory = new ConectaBanco();
				try (Connection c = factory.obtemConexao()) {
					PreparedStatement ps = c.prepareStatement(sql);

					ResultSet rs = ps.executeQuery();
					while (rs.next()) {

						int codigo = rs.getInt("idFilme");
						String nome = rs.getString("nomeFilme");
						int anoFilme = rs.getInt("anofilme");
						int disponivel = rs.getInt("disponivel");
						float preço = rs.getFloat("preço");
						int faixaEtaria = rs.getInt("faixaEtaria");
						String categoria = rs.getString("categoria");

						String aux = String.format(
								"Código: %d\n Nome: %s\n Ano do Filme: %s\n Disponibilidade: %s\n "
										+ "preço: %s\n Faixa Etaria: %s\n categoria: %s\n",
								codigo, nome, anoFilme, disponivel, preço, faixaEtaria, categoria);
						JOptionPane.showMessageDialog(null, aux);

					}
				} catch (Exception w) {
					w.printStackTrace();
				}

			}
		});
		menuBar.add(mntmNewMenuItem_1);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 191, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("ID Funcionário");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 74, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("CPF");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 36, 74, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Nome");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 61, 74, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("E_Mail");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(10, 86, 74, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Telefone");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(10, 111, 74, 14);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Idade");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(10, 136, 74, 14);
		contentPane.add(lblNewLabel_5);

		textField = new JTextField();
		textField.setBounds(94, 8, 236, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(94, 33, 236, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(94, 58, 236, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(94, 83, 236, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setBounds(94, 108, 236, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);

		textField_5 = new JTextField();
		textField_5.setBounds(94, 133, 236, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);

		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String sql = "INSERT INTO funcionario(cpf,nome,e_mail,telefone,idade) VALUES(?,?,?,?,?)";
				ConectaBanco factory = new ConectaBanco();
				try (Connection c = factory.obtemConexao()) {
					PreparedStatement ps = c.prepareStatement(sql);
					ps.setInt(1, Integer.parseInt(textField_1.getText()));
					ps.setString(2, textField_2.getText());
					ps.setString(3, textField_3.getText());
					ps.setString(4, textField_4.getText());
					ps.setInt(5, Integer.parseInt(textField_5.getText()));
					ps.execute();
					JOptionPane.showMessageDialog(null, "Salvo");
				} catch (Exception w) {
					w.printStackTrace();
				}

				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");

			}
		});
		btnNewButton.setBounds(10, 164, 89, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Excluir");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String sql = "delete from funcionario Where idFunc = ? ";
				ConectaBanco factory = new ConectaBanco();
				try (Connection c = factory.obtemConexao()) {
					PreparedStatement ps = c.prepareStatement(sql);
					ps.setInt(1, Integer.parseInt(textField.getText()));
					ps.execute();
					JOptionPane.showMessageDialog(null, "Funcionario " + textField.getText() + " Excluido com sucesso");
				} catch (Exception w) {
					w.printStackTrace();
				}
				textField.setText("");

			}
		});
		btnNewButton_1.setBounds(104, 164, 89, 23);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Consultar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String sql = "SELECT * FROM funcionario";
				ConectaBanco factory = new ConectaBanco();
				try (Connection c = factory.obtemConexao()) {
					PreparedStatement ps = c.prepareStatement(sql);

					ResultSet rs = ps.executeQuery();
					while (rs.next()) {

						int ID = rs.getInt("idFunc");
						int cpf = rs.getInt("cpf");
						String nome = rs.getString("nome");
						String e_mail = rs.getString("E_Mail");
						String Telefone = rs.getString("Telefone");
						int idade = rs.getInt("idade");

						String aux = String.format(
								"ID: %d\n CPF: %s\n Nome: %s\n E_Mail: %s\n Telefone: %s\n Idade: %s\n",

								ID, cpf, nome, e_mail, Telefone, idade);
						JOptionPane.showMessageDialog(null, aux);

					}
				} catch (Exception w) {
					w.printStackTrace();
				}

			}
		});
		btnNewButton_2.setBounds(203, 164, 89, 23);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Alterar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String sql = "UPDATE funcionario SET idFunc = ?, cpf = ?, nome = ?, e_mail = ?, telefone = ?, idade = ? WHERE cpf = ?";
				ConectaBanco factory = new ConectaBanco();
				try (Connection c = factory.obtemConexao()) {
					PreparedStatement ps = c.prepareStatement(sql);

					ps.setString(2, textField_2.getText());
					ps.setString(3, textField_3.getText());
					ps.setString(4, textField_4.getText());
					ps.setInt(5, Integer.parseInt(textField_5.getText()));
					ps.setInt(1, Integer.parseInt(textField_1.getText()));
					ps.setInt(6, Integer.parseInt(textField.getText()));

					ps.execute();
					JOptionPane.showMessageDialog(null,
							"Filme " + textField.getText() + " " + textField_1.getText() + " Alterado com sucesso!!!");
				} catch (Exception w) {
					w.printStackTrace();
				}

			}
		});
		btnNewButton_3.setBounds(302, 164, 89, 23);
		contentPane.add(btnNewButton_3);
	}

}
