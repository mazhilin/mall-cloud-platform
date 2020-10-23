package com.mall.cloud.common.container;

import io.undertow.server.DefaultByteBufferPool;
import io.undertow.websockets.jsr.WebSocketDeploymentInfo;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * <p>封装Qicloud项目UndertowContainerServer类.<br></p> 
 * <p>//TODO...<br></p> 
 * @author Powered by marklin 2020-10-23 22:14
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p> 
 */
@Component
public class UndertowContainerServer implements WebServerFactoryCustomizer<UndertowServletWebServerFactory> {
	@Override
	public void customize(UndertowServletWebServerFactory undertow) {
		undertow.addDeploymentInfoCustomizers(deployment -> {
			String webSockets = "io.undertow.websockets.jsr.WebSocketDeploymentInfo";
			try {
				deployment.addServletContextAttribute(webSockets, initWebSockets());
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			deployment.getAsyncExecutor();
		});
	}
	
	/**
	 * 初始化WebSockets部署配置
	 *
	 * @return 返回结果
	 */
	private WebSocketDeploymentInfo initWebSockets() throws UnknownHostException {
		WebSocketDeploymentInfo webSockets = new WebSocketDeploymentInfo();
		webSockets.setBuffers(new DefaultByteBufferPool(Boolean.FALSE, 2048));
		webSockets.setDispatchToWorkerThread(Boolean.TRUE);
		webSockets.setClientBindAddress(InetAddress.getLocalHost().toString());
		return webSockets;
	}
}
