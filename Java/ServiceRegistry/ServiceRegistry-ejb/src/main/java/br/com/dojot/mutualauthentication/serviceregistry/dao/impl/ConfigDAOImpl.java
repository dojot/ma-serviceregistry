package br.com.dojot.mutualauthentication.serviceregistry.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;

import br.com.dojot.mutualauthentication.communication.constants.CommunicationKeysConstants;
import br.com.dojot.mutualauthentication.communication.facade.api.CommunicationFacade;
import br.com.dojot.mutualauthentication.communication.facade.impl.CommunicationFacadeBean;
import br.com.dojot.mutualauthentication.serviceregistry.dao.api.ConfigDAO;
import br.com.dojot.mutualauthentication.serviceregistry.utils.ServiceRegistryConstants;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

@Singleton
public class ConfigDAOImpl implements ConfigDAO {
	
	private JedisSentinelPool pool = null;

	private Jedis jedis = null;

	private String version;


    private String CASSANDRA_KEYS = "CASSANDRA_CONTACT_POINTS";

    @PostConstruct
    public void init() {
    	CommunicationFacade communicationFacade = new CommunicationFacadeBean();
		Set<String> sentinels = new HashSet<String>();
    	for (String sentinel : communicationFacade.requestRedisSentinelsHosts()) {
            sentinels.add(sentinel);
    	}
    	version = (String) communicationFacade.requestNodeConfigs().get(CommunicationKeysConstants.KEY_VERSION);
		JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(1);
        pool = new JedisSentinelPool(ServiceRegistryConstants.REDIS_CLUSTER_MASTER_NAME, sentinels, poolConfig);
        jedis = pool.getResource();
    }
        
    @PreDestroy
    public void destroy() {
		if (jedis != null) {
			jedis.close();
		}
		pool.close();
		pool.destroy();
    }

	@Override
	public List<String> searchCassandraContactPoints() {
		List<String> values = null;
		try {
			values = jedis.lrange(CASSANDRA_KEYS + "." + version, 0, -1);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return values;
	}

	@Override
	public String get(String param) {
		String content = null;
		try {
            content = jedis.get(param + "." + version);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return content;
	}
	
}
