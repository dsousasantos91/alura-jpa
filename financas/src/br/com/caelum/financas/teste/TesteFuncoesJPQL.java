package br.com.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteFuncoesJPQL {
	
	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		Conta conta = new Conta();
		conta.setId(2);
		
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
		List<Double> medias = (List<Double>) query.getResultList();
		
		medias.forEach(m -> {
			System.out.println("A média do dia 29 é: " + m);			
		});
		
		em.getTransaction().commit();
		em.close();
		
		
	}
}
