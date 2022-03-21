package br.com.inottec.cdv.infra;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.apache.log4j.Logger;
import br.com.inottec.cdv.modelo.RestricaoEntidade;
import javafx.scene.control.Alert;

//clase generica e criando uma restrição com entidade
public class DAO<E extends RestricaoEntidade> {

	// criando variavel
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Class<E> classe;

	// criando um logger
	private static Logger logger = Logger.getLogger(DAO.class);
	
	// crinado uma mensagem de informação do tipo alerta
	//private static Alert alertInf = new Alert(Alert.AlertType.INFORMATION);

	// crinado uma mensagem de erro do tipo alerta
	private static Alert alertErro = new Alert(Alert.AlertType.ERROR);

	// criando um conexao com o banco
	static {
		try {
			emf = Persistence.createEntityManagerFactory("inottec_CDV");
		} catch (Exception e) {
			logger.info("Erro ao criar uma conexão com o banco");
		}
	}

	// crindo contruto
	public DAO() {
		this(null);
	}

	public DAO(Class<E> classe) {
		this.classe = classe;
		em = emf.createEntityManager();
	}

	// metodo abri conexão
	public DAO<E> abrirTransacao() {
		em.getTransaction().begin();
		return this;
	}

	// meto de fechar uma conexão
	public DAO<E> fecharTransacao() {
		em.getTransaction().commit();
		return this;
	}

	// metodo que faz update
	public DAO<E> fazerUpdate(E entidade) {
		em.merge(entidade);
		return this;
	}

	// metodo de remover entidade
	public DAO<E> remover(E entidade) {
		em.remove(entidade);
		return this;
	}

	// metodo que salvar uma entidade
	public DAO<E> incluir(E entidade) {
		em.persist(entidade);
		return this;
	}

	// metodo que adiciona entidade completo
	public DAO<E> incluirAtomico(E entidade) {
		return this.abrirTransacao().incluir(entidade).fecharTransacao();

	}

	// metodo que obter entidade por id
	public E obterPorID(Long id) {
		return em.find(classe, id);
	}

	// metodo que 10 entidade
	public List<E> obterTodos() {
		return this.obterTodos(10);
	}

	// metodo que pode escolhe o tanto de entidade para obter
	public List<E> obterTodos(int qtde) {// , int deslocamento) {

		if (classe == null) {
			throw new UnsupportedOperationException("Classe nula.");
		} else {
			String jpql = "select e from " + classe.getName() + " e";
			TypedQuery<E> query = em.createQuery(jpql, classe);
			query.setMaxResults(qtde);
			// query.setFirstResult(deslocamento);
			return query.getResultList();
		}
	}

	// metodo que obter entidade por like que e um filtro do jpql
	public List<E> obterTodosComLike(String coluna, Object parametro) {// , int deslocamento) {

		if (classe == null) {
			throw new UnsupportedOperationException("Classe nula.");
		} else {
			String jpql = "select e from " + classe.getName() + " e where e." + coluna + " LIKE :parametro";
			TypedQuery<E> query = em.createQuery(jpql, classe).setParameter("parametro", parametro);
			query.setMaxResults(10);
			// query.setFirstResult(deslocamento);
			return query.getResultList();
		}
	}

	// metodo que consulta uma entidade usando coluna e um condição no parametro
	public E consultarUm(String coluna, Object condicao) {

		// tratando um erro se a classe for nula
		if (classe == null) {
			throw new UnsupportedOperationException("Classe nula.");
		} else {
			// criando uma jpql
			String jpql = "select u from " + classe.getName() + " u where u." + coluna + " = :condicao";
			// criando o resultado
			return this.em.createQuery(jpql, classe).setParameter("condicao", condicao).getSingleResult();

		}
	}

	// metodo que consulta uma entidade usando duas coluna e duas condição no
	// parametro para valida a tela de login
	public E validaUsuario(String coluna1, String coluna2, Object condicao1, Object condicao2) {

		try {

			// tratando um erro se a classe for nula
			if (classe == null) {
				throw new UnsupportedOperationException("Classe nula.");
				
			//se a classe não for nula 	
			} else {
				// criando uma jpql
				String jpql = "select u from " + classe.getName() + " u where u." 
				+ coluna1 + " = :condicao1 and "+ coluna2 + " = :condicao2";
				// criando o resultado
				return this.em.createQuery(jpql, classe).setParameter("condicao1", condicao1)
						.setParameter("condicao2", condicao2).getSingleResult();
			}
		//tratando um erro 	
		} catch (Exception e) {
			// criando um alerta de confirmação
			// criando titulo do alerta
			alertErro.setTitle("Erro");
			// criando cabeçario do alerta
			alertErro.setHeaderText("Dados incorreto!");
			// chamando o alerta
			alertErro.show();
		}
		return null;
	}

	// metodo que fecha conexão
	public void fechar() {
		logger.info("Conexão finalizada com o banco!");
		em.close();
	}

}
