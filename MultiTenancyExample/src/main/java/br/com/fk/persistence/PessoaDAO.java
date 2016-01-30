package br.com.fk.persistence;

import br.com.fk.model.Pessoa;

public class PessoaDAO extends DAO<Pessoa, Integer> {

	public PessoaDAO() {
		super(Pessoa.class);
	}

}
