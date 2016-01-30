package br.com.fk.service;

import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.fk.model.Pessoa;
import br.com.fk.persistence.PessoaDAO;
import br.com.fk.util.TenantUtil;

@Path("/")
public class MultiTenancyService {

	@Inject
	private PessoaDAO pessoaDAO;

	/*
	 * A anotação @Transactional indica que esse método será gerenciado pelo
	 * servidor As transações começam quando o método é criado e terminam quando
	 * o método é destruído
	 * 
	 * Teste deverá salvar no schema1
	 */
	@GET
	@Path("schema1teste")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response teste1() {

		TenantUtil.setTenant("schema1");

		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Paul McCarteney");

		pessoaDAO.salvar(pessoa, TenantUtil.getTenant());

		return Response.status(200).entity(pessoa)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_TYPE.withCharset("utf-8")).build();

	}

	/*
	 * A anotação @Transactional indica que esse método será gerenciado pelo
	 * servidor As transações começam quando o método é criado e terminam quando
	 * o método é destruído
	 * 
	 * Teste deverá salvar no schema2
	 */
	@GET
	@Path("schema2teste")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response teste2() {

		TenantUtil.setTenant("schema2");

		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Ringo Starr");

		pessoaDAO.salvar(pessoa, TenantUtil.getTenant());

		return Response.status(200).entity(pessoa)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_TYPE.withCharset("utf-8")).build();

	}

	/*
	 * Nesse método o controle de transação está sendo feito manualmente No
	 * final desse teste, deverá ter uma pessoa cadastrada no schema1 e outra no
	 * schema2 Não pode utilizar a anotação @Transaction nessa abordagem
	 */
	@GET
	@Path("transacaoteste")
	@Produces(MediaType.APPLICATION_JSON)
	public Response teste3() throws NamingException, NotSupportedException, SystemException, SecurityException,
			IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {

		UserTransaction transaction = (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");

		transaction.begin();

		TenantUtil.setTenant("schema1");

		Pessoa pessoa = new Pessoa();
		pessoa.setNome("John Lennon");

		pessoaDAO.salvar(pessoa, TenantUtil.getTenant());

		transaction.commit();

		transaction.begin();

		TenantUtil.setTenant("schema2");

		Pessoa pessoa2 = new Pessoa();
		pessoa2.setNome("George Harrison");

		pessoaDAO.salvar(pessoa2, TenantUtil.getTenant());

		transaction.commit();

		return Response.status(200).entity(pessoa)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_TYPE.withCharset("utf-8")).build();

	}

}
