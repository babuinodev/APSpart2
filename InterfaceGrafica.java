import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class InterfaceGrafica extends JFrame {
    private DaoFuncionario funcionarioDAO;
    private DaoCarro carroDAO;

    public InterfaceGrafica() {
        funcionarioDAO = new DaoFuncionario();
        carroDAO = new DaoCarro();
        
        setTitle("Sistema de Vendas de Carros");
        setSize(400, 400); // Aumentado para acomodar mais botões
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Botões da interface
        JButton btnListarFuncionarios = new JButton("Listar Funcionários");
        JButton btnAdicionarFuncionario = new JButton("Adicionar Funcionário");
        JButton btnListarCarros = new JButton("Listar Carros");
        JButton btnAdicionarCarro = new JButton("Registrar Venda de Carro");

        // Adicionando os botões ao JFrame
        add(btnListarFuncionarios);
        add(btnAdicionarFuncionario);
        add(btnListarCarros);
        add(btnAdicionarCarro);

        // Ação para listar todos os funcionários com todas as informações
        btnListarFuncionarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Funcionario> funcionarios = funcionarioDAO.loadAll();
                StringBuilder sb = new StringBuilder();
                for (Funcionario f : funcionarios) {
                    sb.append("ID: ").append(f.getid())
                      .append(", Nome: ").append(f.getnome())
                      .append(", Salário: ").append(f.getsalario())
                      .append("\n");
                }
                JOptionPane.showMessageDialog(null, sb.toString(), "Funcionários", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Ação para adicionar um novo funcionário
        btnAdicionarFuncionario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = JOptionPane.showInputDialog("Digite o nome do funcionário:");
                String idStr = JOptionPane.showInputDialog("Digite o ID do funcionário:");
                String salarioStr = JOptionPane.showInputDialog("Digite o salário do funcionário:");
                int id = Integer.parseInt(idStr);
                double salario = Double.parseDouble(salarioStr);
                Funcionario funcionario = new Funcionario(id, nome, salario);
                funcionarioDAO.save(funcionario);
                JOptionPane.showMessageDialog(null, "Funcionário adicionado com sucesso!");
            }
        });

        // Ação para listar todos os carros com todas as informações
        btnListarCarros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Carro> carros = carroDAO.loadAll();
                StringBuilder sb = new StringBuilder();
                for (Carro c : carros) {
                    sb.append("ID: ").append(c.getid())
                      .append(", Nome: ").append(c.getNomedocarro())
                      .append(", Modelo: ").append(c.getModelo())
                      .append(", Ano: ").append(c.getAno())
                      .append(", Preço: ").append(c.getPreco())
                      .append("\n");
                }
                JOptionPane.showMessageDialog(null, sb.toString(), "Carros", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Ação para adicionar um novo carro vendido
        btnAdicionarCarro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeCarro = JOptionPane.showInputDialog("Digite o nome do carro:");
                String modelo = JOptionPane.showInputDialog("Digite o modelo do carro:");
                String idStr = JOptionPane.showInputDialog("Digite o ID do carro:");
                String anoStr = JOptionPane.showInputDialog("Digite o ano do carro:");
                String precoStr = JOptionPane.showInputDialog("Digite o preço do carro:");
                
                int id = Integer.parseInt(idStr);
                int ano = Integer.parseInt(anoStr);
                double preco = Double.parseDouble(precoStr);
                
                Carro carro = new Carro(id, nomeCarro, modelo, ano, preco);
                carroDAO.save(carro); 
                JOptionPane.showMessageDialog(null, "Carro vendido registrado com sucesso!");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InterfaceGrafica ui = new InterfaceGrafica();
            ui.setVisible(true);
        });
    }
}