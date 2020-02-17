package br.com.caelum.financas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.TipoMovimentacao;

public class MovimentacaoDao {

	EntityManager em;
	
	public MovimentacaoDao(EntityManager em) {
		this.em = em;
	}

	public List<Double> getMediasPorDiaETipo(TipoMovimentacao saida, Conta conta) {
		String jpql = "select avg(m.valor)from Movimentacao m where m.conta = :pConta"
				+ " and m.tipo = :pTipo"
				+ " group by day(m.data), month(m.data), year(m.data)";
//				+ " order by m.valor desc";
		TypedQuery query = em.createQuery(jpql, Double.class);
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", TipoMovimentacao.SAIDA);
//		List<Movimentacao> resultList = query.getResultList();
//		
//		resultList.forEach(m -> {
//			System.out.println(m.getDescricao());
//			System.out.println(m.getConta().getId());
//		});	
		
//		BigDecimal soma = (BigDecimal) query.getSingleResult();
		return query.getResultList();
	}
	

}
