package Jogo_Dos_Animais;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class Animal {

	static JFrame Janela_Principal = new JFrame("Animais");
	static JButton Botao_OK = new JButton("OK");
	static JLabel LAbel_Pesquisa = new JLabel("Pense em um animal");

	public static void main(String[] args) {
		Janela_Principal();
		Comportamento_Botao();
	}

	static void Janela_Principal() {

		Janela_Principal.setLayout(null);
		Janela_Principal.setSize(230, 100);
		Janela_Principal.setResizable(false);
		Janela_Principal.setLocationRelativeTo(null);
		Janela_Principal.setVisible(true);
		Janela_Principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Janela_Principal.add(Botao_OK);
		Botao_OK.setToolTipText("Clique para jogar");
		Botao_OK.setBounds(85, 30, 60, 22);
		Botao_OK.setBackground(Color.LIGHT_GRAY);
		Botao_OK.setForeground(Color.BLACK);

		Janela_Principal.add(LAbel_Pesquisa);
		LAbel_Pesquisa.setBounds(55, 10, 120, 10);
		LAbel_Pesquisa.setForeground(Color.BLACK);
	}

	static void Comportamento_Botao() {

		Botao_OK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Perguntas();
			}
		});
	}
	
	public static void Perguntas() {
		int Seleciona = 0;

		No Tubarao = new No();
		No Macaco = new No(); 
		Minha_Arvore MinhaArvore = new Minha_Arvore(new No());

		MinhaArvore.Cabeca.GuardaTexto = "vive na água?";
		MinhaArvore.Cabeca.NoEsquerdo = Tubarao;
		MinhaArvore.Cabeca.NoDireito = Macaco;
		Tubarao.GuardaTexto = "Tubarão";
		Macaco.GuardaTexto = "Macaco";

		while(Seleciona != -1) {
			No NoAtual = MinhaArvore.Cabeca;
			boolean JogoCorrendo = true;

			while(JogoCorrendo && Seleciona != -1) {
				if(NoAtual.NoDireito == null) {
					Seleciona = JOptionPane.showConfirmDialog(null, "O animal que você pensou é " + NoAtual.GuardaTexto, "", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

					if(Seleciona == 0) {
						JOptionPane.showMessageDialog(null, "Acertei!");
						JogoCorrendo = false;
						
					} else {
						if(Seleciona == 1) {
							String Nome = JOptionPane.showInputDialog("Qual o animal que você pensou?");
							String Qualidade = JOptionPane.showInputDialog("Um(a) " + Nome + " _____ , mas um(a) " + NoAtual.GuardaTexto + " não.");
							if(Nome != null && Nome.equals("") == false && Qualidade != null && Qualidade.equals("") == false) {
								MinhaArvore.Adiciona(NoAtual, Nome, Qualidade);
							}
						}
					}
					JogoCorrendo = false;

				} else {
					Seleciona = JOptionPane.showConfirmDialog(null, "O animal que você pensou " + NoAtual.GuardaTexto, "", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
					if(Seleciona == 0) {
						NoAtual = NoAtual.NoEsquerdo;
					} else {
						if(Seleciona == 1) {
							NoAtual = NoAtual.NoDireito;
						}
					}
				}
			}
		}
	}
}


class No {
	public String GuardaTexto;

	public No NoEsquerdo;
	public No NoDireito;
}

class Minha_Arvore {
	No Cabeca;
	
	public Minha_Arvore(No PrimeiroNo) {
		Cabeca = PrimeiroNo;
	}
	
	public void Adiciona(No NoPai, String Name, String Particularidades) {
		No NovoDireito = new No();
		NovoDireito.GuardaTexto = NoPai.GuardaTexto;
		
		No NovoEsquerdo = new No();
		NovoEsquerdo.GuardaTexto = Name;
		
		NoPai.GuardaTexto = Particularidades;
		NoPai.NoDireito = NovoDireito;
		NoPai.NoEsquerdo = NovoEsquerdo;
	}
}