package com.marcel.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

@Service
public class DBService {

	@Autowired
	private BCryptPasswordEncoder pe;
	
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
	
	public void instantiateTestDatabase() throws ParseException {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Mesa de Escritório", 300.00);
		Produto p5 = new Produto(null, "Toalha", 50.00);
		Produto p6 = new Produto(null, "Coucha", 200.00);
		Produto p7 = new Produto(null, "TV True COllor", 1200.00);
		Produto p8 = new Produto(null, "Roçadeira", 800.00);
		Produto p9 = new Produto(null, "Abajour", 100.00);
		Produto p10 = new Produto(null, "Pendente", 180.00);
		Produto p11 = new Produto(null, "Skampoo", 90.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));
		
		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));
		
		categoriaRepository.save(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoReporitory.save(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
		Estado est1 = new Estado(null, "Rio Grande do Norte");
		Estado est2 = new Estado(null, "Paraíba");
		
		Cidade ci1 = new Cidade(null, "Natal", est1);
		Cidade ci2 = new Cidade(null, "Currais Novos", est1);
		Cidade ci3 = new Cidade(null, "João Pessoa", est2);
		
		
		est1.getCidades().addAll(Arrays.asList(ci1, ci2));
		est2.getCidades().addAll(Arrays.asList(ci3));
		
		estadoRepository.save(Arrays.asList(est1, est2));
		cidadeRepository.save(Arrays.asList(ci1, ci2, ci3));
		
		Cliente cli1 = new Cliente(null, "Marcel Aguiar", "aguiar.marcel.bezerra@gmail.com", "12332112323", TipoCliente.PESSOAFISICA, pe.encode("123"));
		
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
