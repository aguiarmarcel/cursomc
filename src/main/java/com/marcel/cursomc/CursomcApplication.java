package com.marcel.cursomc;

import java.util.ArrayList;
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
import com.marcel.cursomc.domain.Produto;
import com.marcel.cursomc.domain.enums.TipoCliente;
import com.marcel.cursomc.repositories.CategoriaRepository;
import com.marcel.cursomc.repositories.CidadeRepository;
import com.marcel.cursomc.repositories.ClienteRepository;
import com.marcel.cursomc.repositories.EnderecoRepository;
import com.marcel.cursomc.repositories.EstadoRepository;
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
	}
}
