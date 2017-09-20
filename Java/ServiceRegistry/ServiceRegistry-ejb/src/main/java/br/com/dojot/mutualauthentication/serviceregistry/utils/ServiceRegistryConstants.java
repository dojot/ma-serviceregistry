package br.com.dojot.mutualauthentication.serviceregistry.utils;

public interface ServiceRegistryConstants {
	
	String CASSANDRA_KEYSPACE_SERVICE_REGISTRY = "serviceregistry";
	
	String REDIS_CLUSTER_MASTER_NAME = "redis-cs-cluster";

	String TOPIC_LOGGING_PROCESSING = "logprocessingtopic";

	String PARAM_KAFKA_BOOTSTRAP_SERVERS = "PARAM_KAFKA_BOOTSTRAP_SERVERS";
}
