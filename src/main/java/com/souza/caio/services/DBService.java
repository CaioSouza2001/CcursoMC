package com.souza.caio.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.souza.caio.domain.Categoria;
import com.souza.caio.domain.Cidade;
import com.souza.caio.domain.Cliente;
import com.souza.caio.domain.Endereco;
import com.souza.caio.domain.Estado;
import com.souza.caio.domain.ItemPedido;
import com.souza.caio.domain.Pagamento;
import com.souza.caio.domain.PagamentoComBoleto;
import com.souza.caio.domain.PagamentoComCartao;
import com.souza.caio.domain.Pedido;
import com.souza.caio.domain.Produto;
import com.souza.caio.domain.enums.EstadoPagamento;
import com.souza.caio.domain.enums.Perfil;
import com.souza.caio.domain.enums.TipoCliente;
import com.souza.caio.repositories.CategoriaRepository;
import com.souza.caio.repositories.CidadeRepository;
import com.souza.caio.repositories.ClienteRepository;
import com.souza.caio.repositories.EnderecoRepository;
import com.souza.caio.repositories.EstadoRepository;
import com.souza.caio.repositories.ItemPedidoRepository;
import com.souza.caio.repositories.PagamentoRepository;
import com.souza.caio.repositories.PedidoRepository;
import com.souza.caio.repositories.ProdutoRepository;

@Service
public class DBService {

	@Autowired // TUTORIAL: Instanciada automaticamente
	private CategoriaRepository categoriaRepository;

	@Autowired // TUTORIAL: Instanciada automaticamente
	private ProdutoRepository produtoRepository;

	@Autowired // TUTORIAL: Instanciada automaticamente
	private EstadoRepository estadoRepository;

	@Autowired // TUTORIAL: Instanciada automaticamente
	private CidadeRepository cidadeRepository;

	@Autowired // TUTORIAL: Instanciada automaticamente
	private ClienteRepository clienteRepository;

	@Autowired // TUTORIAL: Instanciada automaticamente
	private EnderecoRepository enderecoRepository;

	@Autowired // TUTORIAL: Instanciada automaticamente
	private PedidoRepository pedidoRepository;

	@Autowired // TUTORIAL: Instanciada automaticamente
	private PagamentoRepository pagamentoRepository;

	@Autowired // TUTORIAL: Instanciada automaticamente
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public void instantiateTestDatabase() throws ParseException {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Categoria cat3 = new Categoria(null, "Cama, mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		Produto p4 = new Produto(null, "Mesa de escritório", 300.00);
		Produto p5 = new Produto(null, "Toalha", 50.00);
		Produto p6 = new Produto(null, "Colcha", 200.00);
		Produto p7 = new Produto(null, "TV true color", 1200.00);
		Produto p8 = new Produto(null, "Roçadeira", 800.00);
		Produto p9 = new Produto(null, "Abajour", 100.00);
		Produto p10 = new Produto(null, "Pendente", 180.00);
		Produto p11 = new Produto(null, "Shampoo", 90.00);

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

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "Maria Silva", "caio.souza.lima.2001.08@gmail.com", "36375912377", TipoCliente.PESSOA_FISICA, passwordEncoder.encode("123"));
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		Cliente cli2 = new Cliente(null, "Caio de Souza", "caio.lima@wises.com.br", "23632433464", TipoCliente.PESSOA_FISICA, passwordEncoder.encode("123"));
		cli2.getTelefones().addAll(Arrays.asList("912345678", "940028922"));
		cli2.addPerfil(Perfil.ADMIN);	

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "387770112", cli1, c2);
		Endereco e3 = new Endereco(null, "Rua Bosque Verde", "294", "Casa B", "Favela", "29375021", cli2, c1);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		cli2.getEnderecos().addAll(Arrays.asList(e3));

		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/01/2021 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/01/2021 19:35"), cli1, e2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/03/2021 00:00"),
				null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}
}
