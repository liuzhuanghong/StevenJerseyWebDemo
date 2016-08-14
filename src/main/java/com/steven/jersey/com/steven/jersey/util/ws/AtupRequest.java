package com.steven.jersey.com.steven.jersey.util.ws;

import org.apache.commons.collections4.CollectionUtils;
import org.glassfish.jersey.client.ClientConfig;

import java.util.Set;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;


/**
 * Rest客房端封装工具类
 * Created by liuzhuanghong on 16/8/15.
 */
public class AtupRequest<R, E>{

    public static final String GET = "GET";
    public static final String DELETE = "DELETE";
    public static final String PUT = "PUT";
    public static final String POST = "POST";
    private ClientConfig clientConfig;
    // TODO:暂不考虑连接池
    public AtupRequest() {
    }

    public E rest(final String method, final String requestUrl, final Class<E> returnType) {
        return rest(method, requestUrl, null, null, null, null, returnType);
    }

    public E rest(final String method, final String requestUrl, final Set<AtupRequestParam> headParams, final Set<AtupRequestParam> queryParams,
                  final MediaType requestDataType, final Class<E> returnType) {
        return rest(method, requestUrl, headParams, queryParams, requestDataType, null, returnType);
    }

    public E rest(final String method, final String requestUrl, final MediaType requestDataType, final R requestData, final Class<E> returnType) {
        return rest(method, requestUrl, null, null, requestDataType, requestData, returnType);
    }

    public E rest(final String method, final String requestUrl, final Set<AtupRequestParam> headParams, final Set<AtupRequestParam> queryParams,
                  final MediaType requestDataType, final R requestData, final Class<E> returnType){
        if (clientConfig == null) {
            clientConfig = new ClientConfig();
        }
        final Client client = ClientBuilder.newClient(clientConfig);

        WebTarget webTarget = client.target(requestUrl);
        if (!CollectionUtils.isEmpty(queryParams)) {
            for (final AtupRequestParam atupRequestParam : queryParams) {
                webTarget = webTarget.queryParam(atupRequestParam.getKey(), atupRequestParam.getValue());
            }
        }

        final Invocation.Builder invocationBuilder = webTarget.request();
        if (!CollectionUtils.isEmpty(headParams)) {
            for (final AtupRequestParam atupRequestParam : headParams) {
                invocationBuilder.header(atupRequestParam.getKey(), atupRequestParam.getValue());
            }
        }

        javax.ws.rs.core.Response response = null;
        Entity<R> entity;
        switch (method) {
            case GET:
                response = invocationBuilder.get();
                break;
            case DELETE:
                response = invocationBuilder.delete();
                break;
            case PUT:
                entity = Entity.entity(requestData, requestDataType);
                response = invocationBuilder.put(entity);
                break;
            case POST:
                entity = Entity.entity(requestData, requestDataType);
                response = invocationBuilder.post(entity);
                break;
            default:
                response = invocationBuilder.get();
        }
        if (response != null) {
            return response.readEntity(returnType);
        } else {
            client.close();
            return null;
        }
    }


}
