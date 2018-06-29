package com.marcel.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.marcel.cursomc.domain.Categoria;
import com.marcel.cursomc.domain.Cidade;
import com.marcel.cursomc.domain.Cliente;
import com.marcel.cursomc.domain.Endereco;
import com.marcel.cursomc.domain.Estado;
import com.marcel.cursomc.domain.ItemPedido;
import com.marcel.cursomc.domain.Pagamento;
import com.marcel.cursomc.domain.PagamentoComBoleto;
import com.marcel.cursomc.domain.PagamentoComCartao;
import com.marcel.cursomc.domain.Pedido;
import com.marcel.cursomc.domain.Produto;
import com.marcel.cursomc.domain.enums.EstadoPagamento;
import com.marcel.cursomc.domain.enums.TipoCliente;
import com.marcel.cursomc.repositories.CategoriaRepository;
import com.marcel.cursomc.repositories.CidadeRepository;
import com.marcel.cursomc.repositories.ClienteRepository;
import com.marcel.cursomc.repositories.EnderecoRepository;
import com.marcel.cursomc.repositories.EstadoRepository;
import com.marcel.cursomc.repositories.ItemPedidoRepository;
import com.marcel.cursomc.repositories.PagamentoRepository;
import com.marcel.cursomc.repositories.PedidoRepository;
import com.marcel.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoReporitory;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.save(Arrays.asList(cat1,cat2));
		produtoReporitory.save(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Rio Grande do Norte");
		Estado est2 = new Estado(null, "Paraíba");
		
		Cidade ci1 = new Cidade(null, "Natal", est1);
		Cidade ci2 = new Cidade(null, "Currais Novos", est1);
		Cidade ci3 = new Cidade(null, "João Pessoa", est2);
		
		
		est1.getCidades().addAll(Arrays.asList(ci1, ci2));
		est2.getCidades().addAll(Arrays.asList(ci3));
		
		estadoRepository.save(Arrays.asList(est1, est2));
		cidadeRepository.save(Arrays.asList(ci1, ci2, ci3));
		
		Cliente cli1 = new Cliente(null, "Marcel Aguiar", "aguir.marcel.bezerra@gmail.com", "12332112323", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("996156891", "33029828292"));
		
		Endereco end1 = new Endereco(null, "Rua Dionísio Filgueira", "764", "Ap 201", "Petrópolis", "59014-020",cli1, ci1);
		Endereco end2 = new Endereco(null, "Av. Professora Maria Sales", "212", "Ap 201", "Tambaú", "12321-500",cli1, ci3);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		
		clienteRepository.save(Arrays.asList(cli1));
		enderecoRepository.save(Arrays.asList(end1, end2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
		
		Pedido pe1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, end1);
		Pedido pe2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, end2);
		
		Pagamento pa1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, pe1, 6);
		pe1.setPagamento(pa1);
		
		Pagamento pa2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, pe2, sdf2.parse("20/10/2017"), null);
		pe2.setPagamento(pa2);
		
		cli1.getPedidos().addAll(Arrays.asList(pe1, pe2));
		
		pedidoRepository.save(Arrays.asList(pe1, pe2));
		pagamentoRepository.save(Arrays.asList(pa1, pa2));
		
		ItemPedido ip1 = new ItemPedido(pe1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(pe1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(pe2, p2, 100.00, 1, 800.00);
		
		pe1.getItens().addAll(Arrays.asList(ip1, ip2));
		pe2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.save(Arrays.asList(ip1, ip2, ip3));
		
	}
}
