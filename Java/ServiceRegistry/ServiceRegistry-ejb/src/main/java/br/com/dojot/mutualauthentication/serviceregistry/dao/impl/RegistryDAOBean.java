package br.com.dojot.mutualauthentication.serviceregistry.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.policies.DCAwareRoundRobinPolicy;
import com.datastax.driver.core.policies.DefaultRetryPolicy;
import com.datastax.driver.core.policies.TokenAwarePolicy;

import br.com.dojot.mutualauthentication.serviceregistry.beans.vo.RegistryVO;
import br.com.dojot.mutualauthentication.serviceregistry.dao.api.RegistryDAO;
import br.com.dojot.mutualauthentication.serviceregistry.service.api.ConfigService;
import br.com.dojot.mutualauthentication.serviceregistry.utils.ServiceRegistryConstants;

@Singleton
public class RegistryDAOBean implements RegistryDAO { 

	private Session session;
	
	private Cluster cluster;
	
	private Map<String, PreparedStatement> queries;
	
	@EJB
	private ConfigService configService;
	
	@PostConstruct
	public void init() {
		cluster = Cluster
	            .builder()
	            .addContactPoints(configService.findCassandraContactPoints())
	            .withRetryPolicy(DefaultRetryPolicy.INSTANCE)
	            .withLoadBalancingPolicy(new TokenAwarePolicy(new DCAwareRoundRobinPolicy.Builder().build()))
	            .build();
		session = cluster.connect(ServiceRegistryConstants.CASSANDRA_KEYSPACE_SERVICE_REGISTRY);
		
		queries = new HashMap<>();
		queries.put("save", session.prepare("INSERT INTO registry (hostname, port, microservice, version, context, date) "
							+ "VALUES (:hostname, :port, :microservice, :version, :context, :date)"));
		queries.put("remove", session.prepare("DELETE FROM registry WHERE microservice = :microservice AND version = :version AND hostname = :hostname"));
		queries.put("search", session.prepare("SELECT * FROM registry WHERE microservice = :microservice AND version = :version"));
		queries.put("searchall", session.prepare("SELECT * FROM registry"));
	}
		
	@PreDestroy
	public void close() {
		session.close();
		cluster.close();
	}

	@Override
	public void add(RegistryVO vo) {
		try {
			session.execute(queries.get("save").bind()
					.setString("hostname", vo.getHostname())
					.setInt("port", vo.getPort())
					.setString("microservice", vo.getMicroservice())
					.setString("version", vo.getVersion())
					.setString("context", vo.getContext())
					.setTimestamp("date", vo.getDate()));
		} catch (Exception ex) {
			System.out.println("Erro ao persisir um registry: " + ex.getMessage());
		}		
	}
	
	@Override
	public void remove(String microservice, String version, String hostname) {
		try {
			session.execute(queries.get("remove").bind()
					.setString("microservice", microservice)
					.setString("version", version)
					.setString("hostname", hostname));
		} catch (Exception ex) {
			System.out.println("Erro ao excluir um registry: " + ex.getMessage());
		}		
	}
	
	@Override
	public List<RegistryVO> search(String microservice, String version) {
		List<RegistryVO> registers = new ArrayList<RegistryVO>();
		try {
			ResultSet results = session.execute(queries.get("search").bind()
					.setString("microservice", microservice)
					.setString("version", version));
			for (Row row : results) {
				RegistryVO vo = new RegistryVO();
				vo.setContext(row.getString("context"));
				vo.setMicroservice(row.getString("microservice"));
				vo.setVersion(row.getString("version"));
				vo.setHostname(row.getString("hostname"));
				vo.setPort(row.getInt("port"));
				vo.setDate(row.getTimestamp("date"));
				registers.add(vo);
			}
		} catch (Exception ex) {
			System.out.println("Erro ao processar a query: " + ex.getMessage());
		}
		return registers;
	}

	@Override
	public List<RegistryVO> search() {
		List<RegistryVO> registers = new ArrayList<RegistryVO>();
		try {
			ResultSet results = session.execute(queries.get("searchall").bind());
			for (Row row : results) {
				RegistryVO vo = new RegistryVO();
				vo.setContext(row.getString("context"));
				vo.setMicroservice(row.getString("microservice"));
				vo.setVersion(row.getString("version"));
				vo.setHostname(row.getString("hostname"));
				vo.setPort(row.getInt("port"));
				vo.setDate(row.getTimestamp("date"));
				registers.add(vo);
			}
		} catch (Exception ex) {
			System.out.println("Erro ao processar a query: " + ex.getMessage());
		}
		return registers;
	}

}
