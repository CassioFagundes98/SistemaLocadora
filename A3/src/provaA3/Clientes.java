package provaA3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Clientes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clientes frame = new Clientes();
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
	public Clientes() {
		setBackground(new Color(255, 255, 255));
		setTitle("Clientes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenuItem mntmNewMenuItem = new JMenuItem("Voltar");
		mntmNewMenuItem.setHorizontalAlignment(SwingConstants.RIGHT);
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();

			}
		});
		menuBar.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Filmes");
		mntmNewMenuItem_1.setHorizontalAlignment(SwingConstants.RIGHT);
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
										+ "preço: %s\n Faixa Etaria: %s\n categoria: %s\n ",
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

		JLabel lblNewLabel = new JLabel("CPF");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 66, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 36, 66, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("E_Mail");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 61, 66, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Telefone");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(10, 86, 66, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Idade");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(10, 111, 66, 14);
		contentPane.add(lblNewLabel_4);

		textField = new JTextField();
		textField.setBounds(86, 8, 201, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(86, 33, 201, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(86, 58, 201, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(86, 83, 201, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setBounds(86, 108, 201, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);

		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String sql = "INSERT INTO clientes(cpf,nome,e_mail,telefone,idade) VALUES(?,?,?,?,?)";
				ConectaBanco factory = new ConectaBanco();
				try (Connection c = factory.obtemConexao()) {
					PreparedStatement ps = c.prepareStatement(sql);
					ps.setInt(1, Integer.parseInt(textField.getText()));
					ps.setString(2, textField_1.getText());
					ps.setString(3, textField_2.getText());
					ps.setString(4, textField_3.getText());
					ps.setInt(5, Integer.parseInt(textField_4.getText()));
					ps.execute();
					JOptionPane.showMessageDialog(null, "Salvo");
				} catch (Exception w) {
					w.printStackTrace();
				}

				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");

			}
		});
		btnNewButton.setBounds(10, 139, 110, 39);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Alterar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String sql = "UPDATE clientes SET cpf = ?, nome = ?, e_mail = ?, telefone = ?, idade = ? WHERE cpf = ?";
				ConectaBanco factory = new ConectaBanco();
				try (Connection c = factory.obtemConexao()) {
					PreparedStatement ps = c.prepareStatement(sql);

					ps.setString(1, textField_1.getText());
					ps.setString(2, textField_2.getText());
					ps.setString(3, textField_3.getText());
					ps.setInt(4, Integer.parseInt(textField_4.getText()));
					ps.setInt(7, Integer.parseInt(textField.getText()));

					ps.execute();
					JOptionPane.showMessageDialog(null,
							"Filme " + textField.getText() + " " + textField_1.getText() + " Alterado com sucesso!!!");
				} catch (Exception w) {
					w.printStackTrace();
				}

			}
		});
		btnNewButton_1.setBounds(155, 183, 110, 39);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Excluir");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String sql = "delete from clientes Where cpf = ? ";
				ConectaBanco factory = new ConectaBanco();
				try (Connection c = factory.obtemConexao()) {
					PreparedStatement ps = c.prepareStatement(sql);
					ps.setInt(1, Integer.parseInt(textField.getText()));
					ps.execute();
					JOptionPane.showMessageDialog(null, "Cliente " + textField.getText() + " Excluido com sucesso");
				} catch (Exception w) {
					w.printStackTrace();
				}
				textField.setText("");

			}
		});
		btnNewButton_2.setBounds(155, 139, 110, 39);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Consultar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String sql = "SELECT * FROM clientes";
				ConectaBanco factory = new ConectaBanco();
				try (Connection c = factory.obtemConexao()) {
					PreparedStatement ps = c.prepareStatement(sql);

					ResultSet rs = ps.executeQuery();
					while (rs.next()) {

						int cpf = rs.getInt("cpf");
						String nome = rs.getString("nome");
						String e_mail = rs.getString("E_Mail");
						String Telefone = rs.getString("Telefone");
						int idade = rs.getInt("idade");

						String aux = String.format("CPF: %d\n Nome: %s\n E_Mail: %s\n Telefone: %s\n Idade: %s\n",

								cpf, nome, e_mail, Telefone, idade);
						JOptionPane.showMessageDialog(null, aux);

					}
				} catch (Exception w) {
					w.printStackTrace();
				}

			}
		});
		btnNewButton_3.setBounds(301, 139, 110, 39);
		contentPane.add(btnNewButton_3);
	}
}
